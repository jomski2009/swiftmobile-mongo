package org.imanmobile.sms.controllers.rest;

import org.imanmobile.sms.core.domain.Sms;
import org.imanmobile.sms.core.domain.SmsResponse;
import org.imanmobile.sms.exceptions.InsufficientCreditException;
import org.imanmobile.sms.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jome on 2014/02/01.
 */
@RestController
@RequestMapping("sms")
public class SmsController {
    @Autowired
    SmsService smsService;


    /**
     * Minimum acceptable sms format for a group send should be
     * {"text":"message to be sent"}
     * The groupname should be part of the path variable. Url encoded values are preferable
     *
     * @return
     */
    @RequestMapping(value = "{username}/{groupname}/send", method = RequestMethod.POST)
    public ResponseEntity<List<SmsResponse>> sendGroupSms(@RequestBody Sms sms, @PathVariable("username") String username, @PathVariable("groupname") String groupname) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<SmsResponse> responses = smsService.sendSms(username, groupname, sms);
            headers.set("Transaction-Report", "Smses sent successfully");
            return new ResponseEntity<>(responses, headers, HttpStatus.OK);
        } catch (InsufficientCreditException e) {
            headers.set("Transaction-Report", e.getMessage());
        }

        return new ResponseEntity<>(headers, HttpStatus.PRECONDITION_FAILED);
    }


    @RequestMapping(value = "{username}/{groupname}/send", method = RequestMethod.GET)
    public ResponseEntity<String> sendGroupSms() {
        String message = " Minimum acceptable sms format for a group send should be\n"
                + "{\"text\":\"message to be sent\"}\n"
                + "The groupname should be part of the path variable. Url encoded values are preferable";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
