package org.imanmobile.sms.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jome on 2014/02/17.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping()
    public String home(Model model) {
        model.addAttribute("message", "Welcome to ImanMobile. We will be coming live soon...");
        return "home";
    }

    @RequestMapping("/boot")
    public String boot(Model model) {
        model.addAttribute("message", "This is the bootstrap view");
        return "boot";
    }

    @RequestMapping("/smslogin")
    public String login(Model model) {
        model.addAttribute("message", "This is the bootstrap view");
        return "smslogin";
    }


}
