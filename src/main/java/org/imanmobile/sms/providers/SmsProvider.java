package org.imanmobile.sms.providers;


import org.imanmobile.sms.core.domain.SmsResponseWrapper;
import org.imanmobile.sms.core.domain.SmsWrapper;
import org.json.simple.parser.ParseException;

public interface SmsProvider {

    public SmsResponseWrapper sendSms(SmsWrapper smsWrapper) throws ParseException;

    public double getCredits();


}

