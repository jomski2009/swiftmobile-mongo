package org.imanmobile.sms.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jome on 2014/02/10.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsPushReply {
    private long sender;
    private String receiver;
    private String text;
    private String messageid;
    private String datetime;

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "SmsPushReply{" +
                "sender=" + sender +
                ", text='" + text + '\'' +
                ", messageid='" + messageid + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
