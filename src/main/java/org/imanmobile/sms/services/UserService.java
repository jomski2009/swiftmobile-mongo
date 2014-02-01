package org.imanmobile.sms.services;

import org.imanmobile.sms.core.domain.Account;
import org.imanmobile.sms.core.domain.User;
import org.imanmobile.sms.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Created by jome on 2014/01/29.
 */
public interface UserService {

    User addUser(User user, Account newAccount);

    User addUser(User user);

    void activateAccount(String username);

    double updateAccountBalanceForUser(String username, double addAmount) throws UserNotFoundException;

    double updateSmsValueForUser(String username, double smsValue) throws UserNotFoundException;

    double getBalanceFor(String username) throws UserNotFoundException;

    double getSmsValueFor(String username) throws UserNotFoundException;

    List<User> getAllUsers();

    User findByUsername(String username);
}
