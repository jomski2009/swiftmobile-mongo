package org.imanmobile.sms;

import com.mongodb.MongoException;
import org.imanmobile.sms.core.domain.*;
import org.imanmobile.sms.exceptions.InsufficientCreditException;
import org.imanmobile.sms.exceptions.UserNotFoundException;
import org.imanmobile.sms.providers.InfobipSmsProvider;
import org.imanmobile.sms.services.GroupService;
import org.imanmobile.sms.services.SmsService;
import org.imanmobile.sms.services.UserService;
import org.json.simple.parser.ParseException;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PlayClass implements CommandLineRunner {
    @Autowired
    Environment env;


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    Datastore datastore;

    @Autowired
    InfobipSmsProvider infobipSmsProvider;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    SmsService smsService;

    @Override
    public void run(String... args) throws Exception {
        String username = "jomski2009";
        String groupname = "Default";
        //createUser(username);
        //ctivateAccount(username);
        //setBalanceAndSmsValue(username);


        //addGroupToUser(username);
        //getGroups(username);
        //getGroup(username, "CE Centurion Members");
        //addRecipientsToGroup(username);
        sendSmsWithGroup(username, groupname);
        //deleteRecipientFromGroup(username, groupname);
        getBalanceAndSmsValue(username);
    }

    private void addGroupToUser(String username) {
        try {
            Group group = new Group();
            group.setCreationdate(new Date());
            group.setDescription("second group for: " + username);
            group.setName("Group 2");
            groupService.addGroupToUser(group, username);

        } catch (MongoException me) {
            System.out.println(me.toString());
        }
    }

    private void getGroups(String username) {
        System.out.println(groupService.getGroupsFor(username));

    }

    private void getGroup(String username, String groupname) {
        Group group = groupService.getGroup(groupname, username);
        System.out.println(group);

    }

    private void activateAccount(String username) {
        userService.activateAccount(username);
    }

    private void setBalanceAndSmsValue(String username) {
        double v = 0;
        double v1 = 0;
        try {
            v = userService.updateAccountBalanceForUser(username, 44.0);
            v1 = userService.updateSmsValueForUser(username, 0.25);
            System.out.println("Account Balance: " + v + "\nSms Value: " + v1);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getBalanceAndSmsValue(String username) {
        double v = 0;
        double v1 = 0;
        try {
            v1 = userService.getSmsValueFor(username);
            v = userService.getBalanceFor(username);
            System.out.println("Account Balance: " + v + "\nSms Value: " + v1);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createUser(String username) {
        try {
            User user = new User();
            Account account = new Account();
            account.setActive(true);
            account.setBalance(0);
            account.setSmsvalue(0.25);

            user.setAccount(account);
            user.setActive(true);
            user.setCellnumber(27719166815L);
            user.setEmail("jome@example.com");
            user.setFirstname("Jome");
            user.setLastname("Akpoduado");
            user.setPassword("wordpass15");
            user.setUsername(username);
            user.setRole(1);


            System.out.println(userService.addUser(user));
        } catch (Exception me) {

            System.out.println(me);
        }


    }

    private void addRecipientsToGroup(String username) {
        String groupname = "Default";
        List<Recipient> recipients = new ArrayList<>();
        Recipient r1 = new Recipient(27719166815L, "Jome", "Akpoduado", "Pastor");
        Recipient r2 = new Recipient(27837930939L, "Jome", "Akpoduado", "CTO");
        Recipient r3 = new Recipient(27836173018L, "Juliet", "Akpoduado", "WifeC", "July 7th");
        Recipient r4 = new Recipient(27837930950L, "Emile", "Senga", "Awesome developer");
        Recipient r5 = new Recipient(27123456789L, "Joe", "Blog", "Unknown user");

        recipients.add(r1);
        recipients.add(r2);
        recipients.add(r3);
        recipients.add(r4);
        recipients.add(r5);
        groupService.addRecipientsToGroup(username, groupname, recipients);

        groupService.getRecipientsInGroup(groupname, username);

        //Get the group
    }

    private void deleteRecipientFromGroup(String username, String groupname) {
        Recipient recipient = new Recipient();
        recipient.setGsm(27837930950L);
        groupService.deleteRecipientFromGroup(username, groupname, recipient);
    }


    private void sendSmsWithGroup(String username, String groupname) {
        try {
            Sms sms = new Sms();
            sms.setMessageid("message1");
            sms.setText("Last year the Government of Kenya was getting ready to dance..");
            if (sms.getText().trim().length() > 160)
                sms.setType("longSMS");
            List<SmsResponse> responses = null;

            responses = smsService.sendSms(username, groupname, sms);
            for (SmsResponse response : responses) {
                System.out.println(response);
            }

        } catch (InsufficientCreditException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendSms() {
        SmsWrapper wrapper = new SmsWrapper();
        SenderAuthentication authentication = new SenderAuthentication();
        Sms sms = new Sms();
        Sms sms1 = new Sms();

        List<Recipient> recipients = new ArrayList<Recipient>();
        List<BaseSms> messages = new ArrayList<BaseSms>();


        Recipient r1 = new Recipient(27719166815L);
        Recipient r2 = new Recipient(27837930939L);
        recipients.add(r1);
        recipients.add(r2);

        sms.setText("ImanMobile... Wazzup!!!");
        sms.setRecipients(recipients);
        sms.setType("longSMS");
        sms.setDatesent(new Date());
        sms.setMessageid("234567");


        BaseSms baseSms = new BaseSms();
        BeanUtils.copyProperties(sms, baseSms);
        messages.add(baseSms);

        recipients.clear();
        recipients.add(r1);
        sms1.setText("A miracle on the way for you... Wazzup!!!");
        sms1.setRecipients(recipients);
        sms1.setType("longSMS");
        sms1.setDatesent(new Date());
        sms1.setMessageid("8972323");


        BaseSms baseSms1 = new BaseSms();
        BeanUtils.copyProperties(sms1, baseSms1);
        messages.add(baseSms1);


        authentication.setUsername("ImanAfrica");
        authentication.setPassword("Afri2013");

        wrapper.setAuthentication(authentication);
        wrapper.setMessages(messages);

        try {
            System.out.println(infobipSmsProvider.sendSms(wrapper).getResults());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Credit remaining: " + infobipSmsProvider.getCredits());

    }

}
