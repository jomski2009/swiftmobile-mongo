package org.imanmobile.sms.services;



import org.imanmobile.sms.core.domain.Sms;
import org.imanmobile.sms.core.domain.SmsResponse;

import java.util.List;

public interface SmsService {
	
	public List<SmsResponse> sendSms(Sms sms, String username);
	
	public double getCredits();

}
