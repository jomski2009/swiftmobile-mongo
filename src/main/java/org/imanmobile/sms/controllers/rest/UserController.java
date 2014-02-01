package org.imanmobile.sms.controllers.rest;

import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.User;
import org.imanmobile.sms.services.GroupService;
import org.imanmobile.sms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by jome on 2014/02/01.
 */

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @RequestMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * The json format for creating a user must contain the following fields at minimum.
     * <p/>
     * {"username": "jome" (must be unique),
     * "firstname":"Jome", "lastname":"Akpoduado", "email":"jome@example.com",
     * "cellnumber":27123456789 (ZA format),
     * "role": 1,
     * "password":userpassword}
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user = userService.addUser(user);
        //TODO: Add validation code and checks for successful creation.

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Minimum required format for group creation
     * {name:groupname, description:groupdescription}
     *
     * @param group
     * @return
     */
    @RequestMapping("{username}/groups/create")
    public ResponseEntity<Group> createGroupForUser(@RequestBody Group group, @PathVariable("username") String username) {
        group = groupService.addGroupToUser(group, username);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }
}
