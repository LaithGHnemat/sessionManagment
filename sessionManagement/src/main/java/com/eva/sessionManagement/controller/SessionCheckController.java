package com.eva.sessionManagement.controller;

import com.eva.sessionManagement.utilty.ITimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class SessionCheckController {

    @Autowired
    private ITimeConverter timeConverter;

    @GetMapping("/check-current-session")//localhost:8080/create-new-session
    public String checkTheSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "The is expired";
        } else {
            return "the session exist with id ="+session.getId()+
                    " and creation date is = " +
                    timeConverter.convertToLocalTime(session.getCreationTime());
        }
    }



}
