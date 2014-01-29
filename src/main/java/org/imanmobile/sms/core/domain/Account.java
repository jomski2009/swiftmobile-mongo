package org.imanmobile.sms.core.domain;

import org.mongodb.morphia.annotations.Embedded;


@Embedded
public class Account {
	
	private double balance = 0.00;
	private double smsvalue;

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

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", smsvalue=" + smsvalue + "]";
	}
	
	
}