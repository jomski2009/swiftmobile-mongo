package org.imanmobile.sms.controllers.rest;

import org.imanmobile.sms.core.domain.User;
import org.imanmobile.sms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jome on 2014/02/01.
 */

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
