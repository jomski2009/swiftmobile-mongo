package org.imanmobile.sms.services;

import org.imanmobile.sms.core.domain.Account;
import org.imanmobile.sms.core.domain.User;

/**
 * Created by jome on 2014/01/29.
 */
public interface UserService {

    User addUser(User user, Account newAccount);

    User addUser(User user);

    void updateUserAccount(User user);
}
