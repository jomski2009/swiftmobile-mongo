package org.imanmobile.sms.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by jome on 2014/01/30.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
