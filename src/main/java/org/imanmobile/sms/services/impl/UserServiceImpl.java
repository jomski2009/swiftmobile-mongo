package org.imanmobile.sms.services.impl;

import org.imanmobile.sms.core.domain.Account;
import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.User;
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
            Query<User> query = datastore.createQuery(User.class)
                    .field("username").equal(user.getUsername());

            return query.get();

        } else {
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        if (validated(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setDatejoined(new Date());

            datastore.save(user);
            Query<User> query = datastore.createQuery(User.class)
                    .field("username").equal(user.getUsername());

            return query.get();

        } else {
            return null;
        }
    }

    @Override
    public void updateUserAccount(User user) {

    }

    private boolean validated(User user) {
        //Check for valid and non empty fields
        //such as email, firstname, lastname, username(unique), cellnumber (ZA for now)

        return true; //for now...
    }
}
