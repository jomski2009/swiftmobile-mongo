package org.imanmobile.sms.providers;


import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.imanmobile.sms.core.domain.SmsReply;
import org.imanmobile.sms.core.domain.SmsReplyWrapper;
import org.imanmobile.sms.core.domain.SmsResponseWrapper;
import org.imanmobile.sms.core.domain.SmsWrapper;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class InfobipSmsProvider implements SmsProvider {
    private static final String API_URL_JSON = "http://api.infobip.com/api/v3/sendsms/json";
    private static final String API_URL_COMMAND = "http://api.infobip.com/api/command?";
    private static final String API_URL_V2_INBOX_COMMAND = "http://api.infobip.com/api/v2/command/inbox?";
    Logger log = Logger.getLogger(this.getClass());
    @Autowired
    Environment env;

    RestTemplate template = new RestTemplate();

    @Override
    public SmsResponseWrapper sendSms(SmsWrapper smsWrapper) throws ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //RestTemplate template = new RestTemplate();
        Gson gson = new Gson();
        String request = gson.toJson(smsWrapper);
        String newRequest = request.replaceAll("baseRecipients", "recipients");
        System.out.println("Http Request: " + newRequest);
        HttpEntity entity = new HttpEntity(newRequest, headers);
        ResponseEntity<SmsResponseWrapper> response = template.exchange(API_URL_JSON, HttpMethod.POST, entity, SmsResponseWrapper.class);
        return response.getBody();
    }

    @Override
    public List<SmsReply> getSmsReplies() {
        String commandQuery = API_URL_V2_INBOX_COMMAND
                + "user=" + env.getProperty("infobip.user.name")
                + "&password=" + env.getProperty("infobip.user.password")
                + "&limit=0&output=json";
        System.out.println(commandQuery);
        SmsReplyWrapper replyList = template.getForObject(commandQuery, SmsReplyWrapper.class);
        System.out.println(replyList);
        if (replyList.getInboxMessages().size() > 0) {
            return replyList.getInboxMessages();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public double getCredits() {
        String credits = "0";
        String commandQuery = API_URL_COMMAND + "username="
                + env.getProperty("infobip.user.name") + "&password="
                + env.getProperty("infobip.user.password") + "&cmd=CREDITS";
        //RestTemplate template = new RestTemplate();

        credits = template.getForObject(commandQuery, String.class);
        try {
            return Double.parseDouble(credits);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
