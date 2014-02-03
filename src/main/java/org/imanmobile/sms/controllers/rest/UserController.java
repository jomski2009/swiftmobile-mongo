package org.imanmobile.sms.controllers.rest;

import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.Recipient;
import org.imanmobile.sms.core.domain.User;
import org.imanmobile.sms.services.GroupService;
import org.imanmobile.sms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    @RequestMapping("{username}/groups/{groupname}/deletenumbers")
    public ResponseEntity<Void> deleteNumbersFromGroup(@PathVariable("username") String username, @PathVariable("groupname") String groupname, @RequestBody List<Recipient> recipients) {
        System.out.println(recipients);
        groupService.deleteRecipientsFromGroup(username, groupname, recipients);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping("{username}/groups/{groupname}/addnumbers")
    public ResponseEntity<Void> addNumberToGroup(@PathVariable("username") String username, @PathVariable("groupname") String groupname, @RequestBody List<Recipient> recipients) {
        System.out.println(recipients);
        System.out.println(groupname);
        groupService.addRecipientsToGroup(username, groupname, recipients);

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping("{username}/groups/{groupname}/csv/addnumbers")
    public ResponseEntity<Void> addNumbersToGroupViaCsv(@RequestParam("csvfile") MultipartFile csvfile, @PathVariable("username") String username, @PathVariable("groupname") String groupname) {
        System.out.println(groupname);
        try {
            System.out.println("Starting csv processing...");
            List<String> rows = new ArrayList<String>();

            if (!csvfile.isEmpty()) {
                String row = "";
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        csvfile.getInputStream()));
                while ((row = br.readLine()) != null) {
                    rows.add(row);
                }
                br.close();
                // System.out.println(rows.get(0));
                // rows.remove(0); // Just to get csv working... removing the
                // // header row...
                // System.out.println(rows.get(0));

                //userService.createRelationshipData(rows);
                groupService.addRecipientsToGroupViaCsv(username, groupname, rows);

                return new ResponseEntity<Void>(HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
