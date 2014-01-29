package org.imanmobile.sms;

import java.util.Date;
import java.util.List;

import org.imanmobile.sms.core.domain.Account;
import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PlayClass implements CommandLineRunner {


	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	Datastore datastore;

	@Override
	public void run(String... args) throws Exception {
		scenario2();
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
		account.setSmsvalue(0.18);

		UpdateOperations<User> update = datastore
				.createUpdateOperations(User.class);
		update.set("account", account);
		
		List<Group> groups = query.get().getUsergroups();

		Group group1 = new Group();
		group1.setCreationdate(new Date().getTime());
		group1.setDescription("test group for first user");
		group1.setName("Group One");
		group1.setUser(query.get());
		datastore.save(group1);
		groups.add(group1);
		update.add("usergroups", group1);
		datastore.update(query, update);

		Group group2 = new Group();
		group2.setCreationdate(new Date().getTime());
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
