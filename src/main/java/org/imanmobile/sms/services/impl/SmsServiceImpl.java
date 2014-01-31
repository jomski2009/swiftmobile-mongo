package org.imanmobile.sms.services.impl;

import org.imanmobile.sms.core.domain.*;
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
    public List<SmsResponse> sendSms(String username, String groupname, Sms sms) {
        sms.setSender(username);
        Group group = groupService.getGroup(groupname, username);
        if (group != null) {
            sms.setRecipients(group.getRecipients());
            SmsWrapper wrapper = new SmsWrapper();
            SenderAuthentication authentication = new SenderAuthentication();
            List<BaseSms> messages = new ArrayList<BaseSms>();
            List<BaseRecipient> baseRecipients = new ArrayList<>();

            BaseSms baseSms = new BaseSms();
            BeanUtils.copyProperties(sms, baseSms);

            for (Recipient recipient : sms.getRecipients()) {
                BaseRecipient baseRecipient = new BaseRecipient();
                BeanUtils.copyProperties(recipient, baseRecipient);
                baseRecipient.setMessageId(sms.getMessageid());
                baseRecipients.add(baseRecipient);
            }
            baseSms.setBaseRecipients(baseRecipients);
            messages.add(baseSms);

            authentication.setUsername("ImanAfrica");
            authentication.setPassword("Afri2013");

            wrapper.setAuthentication(authentication);
            wrapper.setMessages(messages);
            System.out.println(wrapper);

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

                if (responses.size() == 1) {
                    //Most likey the transaction failed...
                    if (responses.get(0).getStatus() == 0) {
                        //Do a debit on the users balance

                    } else {
                        //The assumption is that the sending failed
                    }
                } else {
                    //Iterate through the result and if any status was not zero, dont debit the user
                    //Otherwise add to a count and debit the user...
                }
                return smsResponseWrapper.getResults();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
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
