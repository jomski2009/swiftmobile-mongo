package org.imanmobile.sms.core.domain;

/**
 * Created by jome on 2014/01/31.
 */
public class BaseRecipient {

    protected long gsm;
    private String messageId;


    public BaseRecipient() {
    }

    public long getGsm() {
        return gsm;
    }

    public void setGsm(long gsm) {
        this.gsm = gsm;
    }


    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageid) {
        this.messageId = messageid;
    }

    @Override
    public String toString() {
        return "BaseRecipient{" +
                "gsm=" + gsm +
                ", messageId='" + messageId + '\'' +
                '}';
    }

}
