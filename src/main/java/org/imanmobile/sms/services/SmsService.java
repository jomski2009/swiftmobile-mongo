package org.imanmobile.sms.services;


import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.Sms;
import org.imanmobile.sms.core.domain.SmsResponse;

import java.util.List;

public interface SmsService {

    public List<SmsResponse> sendSms(String username, Sms sms, Group group);

    public List<SmsResponse> sendSms(String username, Sms sms);

    public double getBalance(String username);

}
