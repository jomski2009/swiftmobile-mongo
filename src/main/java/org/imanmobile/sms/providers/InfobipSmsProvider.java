package org.imanmobile.sms.providers;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.imanmobile.sms.core.domain.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class InfobipSmsProvider {
    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    Environment env;

    private static final String API_URL_JSON = "http://api.infobip.com/api/v3/sendsms/json";
    private static final String API_URL_COMMAND = "http://api.infobip.com/api/command?";


    public SmsResponseWrapper sendSms(SmsWrapper smsWrapper) throws ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate template = new RestTemplate();
        Gson gson = new Gson();
        String request = gson.toJson(smsWrapper);
        HttpEntity entity = new HttpEntity(request, headers);
        ResponseEntity<SmsResponseWrapper> response = template.exchange(API_URL_JSON, HttpMethod.POST, entity, SmsResponseWrapper.class);
        return response.getBody();
    }

    public double getCredits() {
        String credits = "0";
        String commandQuery = API_URL_COMMAND + "username="
                + env.getProperty("infobip.user.name") + "&password="
                + env.getProperty("infobip.user.password") + "&cmd=CREDITS";
        RestTemplate template = new RestTemplate();

        credits = template.getForObject(commandQuery, String.class);
        try {
            return Double.parseDouble(credits);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
