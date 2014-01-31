package org.imanmobile.sms.core.domain;

import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by jome on 2014/01/29.
 */

@Embedded(value = "response")
public class SmsResponse {

    private int status;
    private String messageid;
    private long destination;

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDestination() {
        return destination;
    }

    public void setDestination(long destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "SmsResponse{" +
                "status=" + status +
                ", messageid='" + messageid + '\'' +
                ", destination=" + destination +
                '}';
    }
}
