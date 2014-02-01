package org.imanmobile.sms.controllers.rest;

import org.imanmobile.sms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jome on 2014/02/01.
 * <p/>
 * Controller to handle all administrative functions
 * such as enabling/disabling users and accounts,
 * adding credits to user accounts (will be handled automatically later)
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserService userService;

    @RequestMapping("users/{username}/enable")
    public ResponseEntity<String> activateUser(@PathVariable("username") String username) {
        boolean result = userService.activateUser(username);

        if (result) return new ResponseEntity<>(username + " successfully activated. ", HttpStatus.OK);
        return new ResponseEntity<>("unable to activate user: " + username, HttpStatus.OK);
    }

}
