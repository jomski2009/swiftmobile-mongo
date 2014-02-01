package org.imanmobile.sms.services;

import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.Recipient;

import java.util.List;

/**
 * Created by jome on 2014/01/30.
 */
public interface GroupService {
    List<Group> getGroupsFor(String username);

    Group addGroupToUser(Group group, String username);

    void addRecipientsToGroup(String username, String groupname, List<Recipient> recipients);

    void deleteRecipientFromGroup(String username, String groupname, Recipient recipient);

    Group getGroup(String groupname, String username);

    List<Recipient> getRecipientsInGroup(String groupname, String username);
}
