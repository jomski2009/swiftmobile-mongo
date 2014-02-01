package org.imanmobile.sms.controllers.rest;

import org.imanmobile.sms.core.domain.Sms;
import org.imanmobile.sms.core.domain.SmsResponse;
import org.imanmobile.sms.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "{username}/{groupname}/send", method = RequestMethod.POST)
    public ResponseEntity<List<SmsResponse>> sendGroupSms(@RequestBody Sms sms, @PathVariable("username") String username, @PathVariable("groupname") String groupname) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "{username}/{groupname}/send", method = RequestMethod.GET)
    public ResponseEntity<String> sendGroupSms() {
        return new ResponseEntity<>("Send sms using the follwing format...", HttpStatus.NOT_IMPLEMENTED);
    }

}
