package org.imanmobile.sms.providers;


import org.imanmobile.sms.core.domain.Sms;
import org.imanmobile.sms.core.domain.SmsResponse;

import java.util.List;

public interface SmsProvider {

	public List<SmsResponse> sendSMS(Sms sms);

	public double getCredits();

	public List<SmsResponse> sendJsonSMS(Sms sms);

}

