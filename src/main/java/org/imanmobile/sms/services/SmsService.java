package org.imanmobile.sms.services;


import org.imanmobile.sms.core.domain.BaseSms;
import org.imanmobile.sms.core.domain.Sms;
import org.imanmobile.sms.core.domain.SmsResponse;
import org.imanmobile.sms.core.domain.SmsResponseWrapper;
import org.imanmobile.sms.exceptions.InsufficientCreditException;

import java.util.List;

public interface SmsService {

    public SmsResponseWrapper sendSms(String username, String groupname, BaseSms sms);

    public List<SmsResponse> sendSms(String username, String groupname, Sms sms) throws InsufficientCreditException;


    public double getBalance(String username);

}
