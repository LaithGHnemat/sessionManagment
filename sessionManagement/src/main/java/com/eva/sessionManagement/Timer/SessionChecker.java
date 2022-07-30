package com.eva.sessionManagement.Timer;


import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Component
public class SessionChecker implements ISessionChecker {

//    private HttpServletRequest request;
//
//    public SessionChecker(HttpServletRequest request) {
//        this.request = request;
//    }

    @Override
    public void run() {
       // checkSession(request);
        System.out.println("+++++++++++++++++++++++++++++");
    }

    @Override
    public void checkSession(HttpServletRequest request) {
//        while (true) {
//            HttpSession session = request.getSession(false);
//            if (session != null) {
//                System.out.println(session.getId()+"kkkkkkkkkkkkkkk");
//            }
//        }
        System.out.println("vvvvvvvvvvvvvvvvvv");
    }



}

