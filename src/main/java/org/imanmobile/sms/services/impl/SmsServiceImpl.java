package org.imanmobile.sms.services.impl;

import org.imanmobile.sms.core.domain.Group;
import org.imanmobile.sms.core.domain.Sms;
import org.imanmobile.sms.core.domain.SmsResponse;
import org.imanmobile.sms.services.SmsService;

import java.util.List;

/**
 * Created by jome on 2014/01/30.
 */
public class SmsServiceImpl implements SmsService {

    @Override
    public List<SmsResponse> sendSms(String username, Sms sms, Group group) {
        return null;
    }

    @Override
    public List<SmsResponse> sendSms(String username, Sms sms) {
        return null;
    }

    @Override
    public double getBalance(String username) {
        return 0;
    }
}
