package org.imanmobile.sms.services.impl;

import com.mongodb.MongoException;
import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.Recipient;
import org.imanmobile.sms.core.domain.User;
import org.imanmobile.sms.services.GroupService;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void deleteRecipientsFromGroup(String username, String groupname, List<Recipient> recipients) {
        Group group = getGroup(groupname, username);
        group.getRecipients().removeAll(recipients);
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

    @Override
    public void addRecipientsToGroupViaCsv(String username, String groupname, List<String> rows) {
        Group group = getGroup(groupname, username);


        List<Recipient> recipients = new ArrayList<>();
        rows.remove(0);

        for (String r : rows) {
            String[] recipientRow = r.split(";");
            Recipient recipient = new Recipient();
            System.out.println(recipientRow.length);
            recipient.setGsm(Long.parseLong(recipientRow[0]));
            if (recipientRow.length >= 2 && recipientRow[1] != null && recipientRow[1].trim().length() > 0) {
                recipient.setFirstvalue(recipientRow[1]);
            }
            if (recipientRow.length >= 3 && recipientRow[2] != null && recipientRow[2].trim().length() > 0) {
                recipient.setSecondvalue(recipientRow[2]);
            }
            if (recipientRow.length >= 4 && recipientRow[3] != null && recipientRow[3].trim().length() > 0) {
                recipient.setThirdvalue(recipientRow[3]);
            }
            if (recipientRow.length >= 5 && recipientRow[4] != null && recipientRow[4].trim().length() > 0) {
                recipient.setFourthvalue(recipientRow[4]);
            }
            if (recipientRow.length >= 6 && recipientRow[5] != null && recipientRow[5].trim().length() > 0) {
                recipient.setFifthvalue(recipientRow[5]);
            }

            recipients.add(recipient);
        }

        for (Recipient r : recipients) {
            if (!group.getRecipients().contains(r))
                group.getRecipients().add(r);
        }

        datastore.save(group);
        System.out.println("Number of cellnumbers after save(): " + group.getRecipients().size());


    }
}
