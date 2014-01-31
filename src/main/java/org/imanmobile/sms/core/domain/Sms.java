package org.imanmobile.sms.core.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jome on 2014/01/29.
 */

@Entity(value = "smses")
public class Sms {
    @Id
    private ObjectId id;


    private String text;

    private String messageid;
    private String type;
    private Date datesent;


    private List<Recipient> recipients = new ArrayList<Recipient>();
    private List<SmsResponse> responses = new ArrayList<SmsResponse>();

    private String sender_id;

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

    public String getSender_id() {
        return sender_id;
    }

    public void setSender(String sender_id) {
        this.sender_id = sender_id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
