package com.eva.sessionManagement.controller;

import com.eva.sessionManagement.model.UserSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;

@Controller
@Log4j2
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcomePage() {
        return "welcome";
    }


    @GetMapping("/logOff")
    public String logOff(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
//        if(session==null){
//            log.info("the session already finished");
//            return "redirect:/login";
//        }
        log.info("We  will  destroy the session with id : "+session.getId());
        log.info("and time creation"+session.getCreationTime());
        log.info("and time finished"+ LocalTime.now());
        session.invalidate();
        return "redirect:/login";
    }



}
