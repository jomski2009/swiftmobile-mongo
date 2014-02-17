package org.imanmobile.sms.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jome on 2014/02/05.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsReply {
    private String externalMessageId;
    private String networkName;
    private long from;
    private String messageText;
    private String receivedDateTime;

    public String getExternalMessageId() {
        return externalMessageId;
    }

    public void setExternalMessageId(String externalMessageId) {
        this.externalMessageId = externalMessageId;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setReceivedDateTime(String receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    @Override
    public String toString() {
        return "SmsReply{" +
                "externalMessageId='" + externalMessageId + '\'' +
                ", networkName='" + networkName + '\'' +
                ", from=" + from +
                ", messageText='" + messageText + '\'' +
                ", receivedDateTime='" + receivedDateTime + '\'' +
                '}';
    }
}
