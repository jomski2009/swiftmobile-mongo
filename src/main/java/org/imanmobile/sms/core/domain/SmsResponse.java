package org.imanmobile.sms.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Created by jome on 2014/01/29.
 */
public class SmsResponse {

    private long id;
    private int status;
    private String messageid;
    private long destination;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
