package org.imanmobile.sms.core.domain;

import java.util.List;

/**
 * Created by jome on 2014/01/29.
 */
public class SmsWrapper {

    private SenderAuthentication authentication;
    private List<BaseSms> messages;

    public SenderAuthentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(SenderAuthentication authentication) {
        this.authentication = authentication;
    }

    public List<BaseSms> getMessages() {
        return messages;
    }

    public void setMessages(List<BaseSms> messages) {
        this.messages = messages;
    }
}
