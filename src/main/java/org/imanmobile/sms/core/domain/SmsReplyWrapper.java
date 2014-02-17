package org.imanmobile.sms.core.domain;

import java.util.List;

/**
 * Created by jome on 2014/02/06.
 */
public class SmsReplyWrapper {
    private List<SmsReply> inboxMessages;

    public List<SmsReply> getInboxMessages() {
        return inboxMessages;
    }

    public void setInboxMessages(List<SmsReply> inboxMessages) {
        this.inboxMessages = inboxMessages;
    }
}
