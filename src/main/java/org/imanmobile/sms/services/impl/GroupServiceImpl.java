package org.imanmobile.sms.services.impl;

import com.mongodb.MongoException;
import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.Recipient;
import org.imanmobile.sms.core.domain.User;
import org.imanmobile.sms.services.GroupService;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by jome on 2014/01/30.
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    Datastore datastore;

    @Override
    public List<Group> getGroupsFor(String username) {
        List<Group> groups = datastore.createQuery(User.class).field("username").equal(username).get().getUsergroups();
        return groups;
    }

    @Override
    public Group addGroupToUser(Group group, String username) throws MongoException {
        User user = datastore.find(User.class, "username", username).get();
        group.setUser_id(user.getUsername());
        group.setCreationdate(new Date());
        datastore.save(group);
        user.getUsergroups().add(group);
        datastore.save(user);
        return group;
    }

    @Override
    public void addRecipientsToGroup(String username, String groupname, List<Recipient> recipients) {
        Group group = getGroup(groupname, username);
        //group.getRecipients().clear();
        System.out.println("Number of cellnumbers: " + group.getRecipients().size());

        for (Recipient r : recipients) {
            if (!group.getRecipients().contains(r))
                group.getRecipients().add(r);
        }

        datastore.save(group);
        System.out.println("Number of cellnumbers after save(): " + group.getRecipients().size());

    }

    @Override
    public void deleteRecipientFromGroup(String username, String groupname, Recipient recipient) {
        Group group = getGroup(groupname, username);
        group.getRecipients().remove(recipient);
        datastore.save(group);
    }

    @Override
    public Group getGroup(String groupname, String username) {
        Group group = datastore.createQuery(Group.class).field("name").equal(groupname).field("user_id").equal(username).get();
        return group;
    }

    @Override
    public List<Recipient> getRecipientsInGroup(String groupname, String username) {
        return getGroup(groupname, username).getRecipients();


    }
}
