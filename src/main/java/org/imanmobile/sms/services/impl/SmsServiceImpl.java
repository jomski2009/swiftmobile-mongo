package org.imanmobile.sms.services.impl;

import org.imanmobile.sms.core.domain.*;
import org.imanmobile.sms.exceptions.InsufficientCreditException;
import org.imanmobile.sms.exceptions.UserNotFoundException;
import org.imanmobile.sms.providers.SmsProvider;
import org.imanmobile.sms.services.GroupService;
import org.imanmobile.sms.services.SmsService;
import org.imanmobile.sms.services.UserService;
import org.json.simple.parser.ParseException;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jome on 2014/01/30.
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    SmsProvider smsProvider;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    Datastore datastore;


    @Override
    public SmsResponseWrapper sendSms(String username, String groupname, BaseSms sms) {
        return null;
    }

    @Override
    public List<SmsResponse> sendSms(String username, String groupname, Sms sms) throws InsufficientCreditException {

        sms.setSender_id(username);
        Group group = groupService.getGroup(groupname, username);
        if (group != null) {
            //Check if there is enough to send the sms
            try {
                double balance = userService.getBalanceFor(username);
                double smsValue = userService.getSmsValueFor(username);

                int smsesNeeded = 1;

                if (sms.getText().length() > 160 && sms.getText().length() <= 320) {
                    smsesNeeded = 2;
                }

                if (sms.getText().length() > 320 && sms.getText().length() <= 480) {
                    smsesNeeded = 3;
                }

                //Should I be setting a max limit on number of characters to send in a


                double amountRequired = group.getRecipients().size() * smsesNeeded * smsValue;
                System.out.println("Credits required: " + amountRequired);
                if (amountRequired > balance) {
                    throw new InsufficientCreditException("This operation cannot be successfully completed. You have insufficient credits.");
                }
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }

            sms.setRecipients(group.getRecipients());
            datastore.save(sms);
            sms.setMessageid(sms.getId().toString());

            SmsWrapper wrapper = new SmsWrapper();
            SenderAuthentication authentication = new SenderAuthentication();
            List<BaseSms> messages = new ArrayList<BaseSms>();
            List<BaseRecipient> baseRecipients = new ArrayList<>();

            BaseSms baseSms = new BaseSms();
            BeanUtils.copyProperties(sms, baseSms);

            for (Recipient recipient : sms.getRecipients()) {
                BaseRecipient baseRecipient = new BaseRecipient();
                BeanUtils.copyProperties(recipient, baseRecipient);
                baseRecipient.setMessageId(sms.getId().toString());
                baseRecipients.add(baseRecipient);
            }
            baseSms.setBaseRecipients(baseRecipients);
            messages.add(baseSms);

            authentication.setUsername("ImanAfrica");
            authentication.setPassword("Afri2013");

            wrapper.setAuthentication(authentication);
            wrapper.setMessages(messages);
            System.out.println(wrapper.getMessages());

            try {
                SmsResponseWrapper smsResponseWrapper = smsProvider.sendSms(wrapper);
                //How do I know if the smses were sent successfully so
                //we can deduct from the user balance.
                int successCount = 0;
                List<SmsResponse> responses = smsResponseWrapper.getResults();
                for (SmsResponse response : responses) {
                    if (response.getStatus() == 0) {
                        successCount++;
                    }
                }

                System.out.println("Total smses sent: " + successCount);
                double smsValue = userService.getSmsValueFor(username);

                int smsesPerRecipient = 1;
                if (sms.getText().length() > 160) {
                    smsesPerRecipient = Math.round(sms.getText().length() / 160);
                    System.out.println("smsesPerRecipient: " + smsesPerRecipient);
                }


                userService.updateAccountBalanceForUser(username, -(smsValue * successCount * smsesPerRecipient));

                sms.setResponses(smsResponseWrapper.getResults());
                sms.setSent(true);
                sms.setDatesent(new Date());
                sms.setCreditsused(successCount * smsesPerRecipient * smsValue);
                datastore.save(sms);
                return smsResponseWrapper.getResults();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public List<SmsReply> getReplies() {
        List<SmsReply> replies = smsProvider.getSmsReplies();
        for (SmsReply reply : replies) {
            System.out.println(reply);
            Sms sms = datastore.find(Sms.class, "messageid", reply.getExternalMessageId()).get();

            sms.getSmsReplies().add(reply);
            datastore.save(sms);
        }

        return smsProvider.getSmsReplies();
    }

    @Override
    public double getBalance(String username) {
        try {
            return userService.getBalanceFor(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
