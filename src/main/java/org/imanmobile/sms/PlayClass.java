package org.imanmobile.sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.imanmobile.sms.core.domain.*;
import org.imanmobile.sms.exceptions.UserNotFoundException;
import org.imanmobile.sms.providers.InfobipSmsProvider;
import org.imanmobile.sms.services.UserService;
import org.json.simple.parser.ParseException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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

    @Override
	public void run(String... args) throws Exception {
//        createUser();
//        activateAccount();
        getBalanceAndSmsValue();

    }

    private void activateAccount() {
        String username = "jomski2009";
        userService.activateAccount(username);
    }

    private void setBalanceAndSmsValue() {
        String username = "jomski2009";
        double v = 0;
        double v1 = 0;
        try {
            v = userService.updateAccountBalanceForUser(username, -1);
            v1 = userService.updateSmsValueForUser(username, 0.18);
            System.out.println("Account Balance: " + v + "\nSms Value: " + v1);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getBalanceAndSmsValue() {
        String username = "jomski2009";
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

    private void createUser() {
        try {
            User user = new User();
        Account account = new Account();
        account.setActive(true);
        account.setBalance(0);
            account.setSmsvalue(0.25);

            user.setAccount(account);
        user.setActive(true);
            user.setCellnumber(27836173018L);
            user.setEmail("juliet@example.com");
            user.setFirstname("Juliet");
            user.setLastname("Akpoduado");
        user.setPassword("wordpass15");
            user.setUsername("juliet");


            System.out.println(userService.addUser(user));
        } catch (Exception me) {

            System.out.println(me);
        }


    }


    private void sendSms(){
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

	private void scenario2() {
		
		User user = datastore.find(User.class).filter("username", "jomski2013").get();
	}

	private void scenario1() {

		User newUser = setupUser(new User());
		datastore.save(newUser);
		Query<User> query = datastore.createQuery(User.class)
				.field("firstname").equal("Jome");
		User user = query.get();

		Account account = new Account();
		account.setBalance(0.0);
        account.setSmsvalue(0.0);

        UpdateOperations<User> update = datastore
				.createUpdateOperations(User.class);
		update.set("account", account);
		
		List<Group> groups = query.get().getUsergroups();

		Group group1 = new Group();
        group1.setCreationdate(new Date());
        group1.setDescription("test group for first user");
		group1.setName("Group One");
		group1.setUser(query.get());
		datastore.save(group1);
		groups.add(group1);
		update.add("usergroups", group1);
		datastore.update(query, update);

		Group group2 = new Group();
        group2.setCreationdate(new Date());
        group2.setDescription("Another test group for first user");
		group2.setName("Group Two");
		group2.setUser(query.get());
		datastore.save(group2);
		groups.add(group2);

		update.add("usergroups", group2);

		datastore.update(query, update);

		System.out.println(user);
		List<Group> savedgroups = user.getUsergroups();
		System.out.println(savedgroups);
	}

	private User setupUser(User newUser) {
		newUser.setActive(true);
		newUser.setCellnumber(27719166815L);
		newUser.setUsername("jomski2013");
		newUser.setEmail("jomea@yookos.com");
		newUser.setFirstname("Jome");
		newUser.setLastname("Akpoduado");
		newUser.setPassword(passwordEncoder.encode("password"));
		newUser.setDatejoined(new Date());

		return newUser;
	}

}
