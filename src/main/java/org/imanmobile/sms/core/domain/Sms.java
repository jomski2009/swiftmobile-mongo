package org.imanmobile.sms.core.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;

/**
 * Created by jome on 2014/01/29.
 */
public class Sms {
    private long id;


    private String text;

    private String messageid;
    private String type;
    private Date datesent;


    private List<Recipient> recipients = new ArrayList<Recipient>();
    private List<SmsResponse> responses = new ArrayList<SmsResponse>();

    private User sender;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDatesent() {
        return datesent;
    }

    public void setDatesent(Date datesent) {
        this.datesent = datesent;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    public List<SmsResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<SmsResponse> responses) {
        this.responses = responses;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
