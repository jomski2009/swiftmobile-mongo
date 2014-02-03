package org.imanmobile.sms.services.impl;

import com.mongodb.MongoException;
import org.imanmobile.sms.core.domain.Account;
import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.User;
import org.imanmobile.sms.exceptions.UserNotFoundException;
import org.imanmobile.sms.services.UserService;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by jome on 2014/01/29.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    Environment env;


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    Datastore datastore;

    @Override
    public User addUser(User user, Account newAccount) {
        if (validated(user)) {
            //Setup and save the user
            user.setAccount(newAccount);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setDatejoined(new Date());

            datastore.save(user);
            user = setUserAndDefaultGroup(user);

            return user;

        } else {
            return null;
        }
    }

    @Override
    public User addUser(User user) throws MongoException {
        if (validated(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setDatejoined(new Date());
            Account account = new Account();
            account.setBalance(0);
            account.setSmsvalue(0.25);
            user.setAccount(account);

            datastore.save(user);

            user = setUserAndDefaultGroup(user);

            return user;

        } else {
            return null;
        }
    }

    @Override
    public void activateAccount(String username) {
        Query<User> query = datastore.find(User.class, "username", username);
        User user = query.get();
        if (user != null) {
            user.getAccount().setActive(true);
            datastore.save(user);
        }
    }

    @Override
    public double updateAccountBalanceForUser(String username, double addAmount) throws UserNotFoundException {
        User user = getUser(username);
        if (user != null) {
            user.getAccount().setBalance(user.getAccount().getBalance() + addAmount);
            datastore.save(user);
            return user.getAccount().getBalance();
        } else {
            throw new UserNotFoundException("The requested user was not found");
        }

    }

    @Override
    public double updateSmsValueForUser(String username, double smsValue) throws UserNotFoundException {
        User user = getUser(username);
        if (user != null) {
            //Should add some audting here...
            user.getAccount().setSmsvalue(smsValue);
            datastore.save(user);
            return user.getAccount().getSmsvalue();
        } else {
            throw new UserNotFoundException("The requested user was not found");

        }

    }

    @Override
    public double getBalanceFor(String username) throws UserNotFoundException {
        User user = getUser(username);
        if (user != null) {
            return user.getAccount().getBalance();
        } else {
            throw new UserNotFoundException("The requested user was not found");
        }
    }

    @Override
    public double getSmsValueFor(String username) throws UserNotFoundException {
        User user = getUser(username);
        if (user != null) {
            return user.getAccount().getSmsvalue();
        } else {
            throw new UserNotFoundException("The requested user was not found");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return datastore.createQuery(User.class).asList();
    }

    @Override
    public User findByUsername(String username) {
        return datastore.find(User.class, "username", username).get();
    }

    @Override
    public boolean activateUser(String username) {
        User user = datastore.find(User.class, "username", username).get();
        user.setActive(true);
        datastore.save(user);

        if (user.isActive()) return true;

        return false;
    }

    private boolean validated(User user) {
        //Check for valid and non empty fields
        if (user.getUsername().trim().isEmpty()) return false;

        if (datastore.find(User.class, "username", user.getUsername()).get() != null) return false;

        return true; //for now...
    }

    private User getUser(String username) {
        Query<User> query = datastore.find(User.class, "username", username);
        User user = query.get();
        return user;
    }

    private User setUserAndDefaultGroup(User user) {
        Query<User> query = datastore.createQuery(User.class)
                .field("username").equal(user.getUsername());
        user = query.get();

        Group group = new Group();
        group.setName("default");
        group.setDescription("Default sms group for " + user.getUsername());
        group.setUser_id(user.getUsername());
        group.setUser_id(user.getUsername());
        group.setCreationdate(new Date());
        datastore.save(group);

        user.getUsergroups().add(group);
        datastore.save(user);
        return user;
    }
}
