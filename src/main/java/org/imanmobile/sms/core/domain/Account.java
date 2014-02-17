package org.imanmobile.sms.core.domain;

import org.mongodb.morphia.annotations.Embedded;


@Embedded
public class Account {

    private double balance = 0.00;
    private double smsvalue;
    private boolean active;
    private String accountname;
    private String sendername;
    private String smspassword;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getSmsvalue() {
        return smsvalue;
    }

    public void setSmsvalue(double smsvalue) {
        this.smsvalue = smsvalue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getSmspassword() {
        return smspassword;
    }

    public void setSmspassword(String smspassword) {
        this.smspassword = smspassword;
    }

    @Override
    public String toString() {
        return "Account [balance=" + balance + ", smsvalue=" + smsvalue + "]";
    }


}
