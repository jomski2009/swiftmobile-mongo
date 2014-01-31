package org.imanmobile.sms.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jome on 2014/01/29.
 */
public class BaseSms {
    private String text;

    private String messageid;
    private String type;


    private List<BaseRecipient> baseRecipients = new ArrayList<BaseRecipient>();

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

    public List<BaseRecipient> getBaseRecipients() {
        return baseRecipients;
    }

    public void setBaseRecipients(List<BaseRecipient> recipients) {
        this.baseRecipients = recipients;
    }

    @Override
    public String toString() {
        return "BaseSms{" +
                "text='" + text + '\'' +
                ", messageid='" + messageid + '\'' +
                ", type='" + type + '\'' +
                ", recipients=" + baseRecipients +
                '}';
    }
}
