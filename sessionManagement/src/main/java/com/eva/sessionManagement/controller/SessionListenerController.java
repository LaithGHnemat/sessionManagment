package com.eva.sessionManagement.controller;


import com.eva.sessionManagement.model.UserSession;
import com.eva.sessionManagement.service.UserSessionService;
import com.eva.sessionManagement.utilty.ITimeConverter;
import com.eva.sessionManagement.utilty.TimeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Controller
public class SessionListenerController {

    @Autowired
    private UserSessionService userSessionService;
    @Autowired
    private ITimeConverter timeConverter;

    private static final Logger LOG = LoggerFactory.getLogger(SessionListenerController.class);

    @GetMapping(value = "/sessions")
    public String getSessions(Model model) {
        List<UserSession> allSessions = userSessionService.getAllSessions();
        model.addAttribute("sessions", allSessions);
        return "sessions";
    }
    @GetMapping(value = "/sessionsDecor")
    public String getSessionsTowV(Model model) {
        List<UserSession> allSessions = userSessionService.getAllSessions();
        model.addAttribute("sessionss", allSessions);
        LOG.info("the sessionsDecor must Back ");
        return "sessionsDecor";
    }
// I Stopped here
  /*  @GetMapping("/check")//localhost:8080/create-new-session
    public String checkTheSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        }
        System.out.println("HHHHHHHHHHHHHHHHHHHH");
        return "";
    }*/


    @GetMapping("/addNew")
    public String addNewSession(Model model) {
        UserSession userSession = new UserSession();
        model.addAttribute("userSession", userSession);
        return "newUserSession";
    }

    @PostMapping("/save")
    public String saveSession(@ModelAttribute("userSession") UserSession userSession, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        userSession.setSessionId(session.getId());
        userSession.setTimeout(session.getMaxInactiveInterval());
        userSession.setLastAccessedTime(timeConverter.convertToLocalTime(session.getLastAccessedTime()));
        userSession.setCreationTime(timeConverter.convertToLocalTime(session.getCreationTime()));
        userSessionService.addSession(userSession);
        return "redirect:/sessions";
    }



    @GetMapping("/allSessions")
    @ResponseBody
    public List<UserSession> getAllSessionss() {
        return userSessionService.getAllSessions();
    }

    @GetMapping("/create-new-session")//localhost:8080/create-new-session
    @ResponseBody
    public String createNewSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
            return "the session is not exist and here new one with id : " + session.getId();
        } else {
            userSessionService.addUserSession(session, "laith");
            return "the session exist with id ="+session.getId()+" and creation date is = " +session.getCreationTime();
        }
    }
    //localhost:8080/sessionAge/1
    @GetMapping(value = "/sessionAge/{id}")
    @ResponseBody
    public LocalTime getSessionAge(@PathVariable long id) {
        return userSessionService.getSessionAge(id);
    }


   /* @GetMapping(value = "/remainingTime/{id}")//localhost:8080/remainingTime/1
    @ResponseBody
    public String getRemainingTime(@PathVariable long id) {
        return userSessionService.getRemainingTime(id) + " seconds";
    }*/

    @GetMapping("/destroy-active-session")
    @ResponseBody
    public String removeSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return "Session destroyed, now there are no active sessions.";
        }
        return "Session not available to destroy.";
    }


  /*  @GetMapping(value = "/test")//localhost:8080/remainingTime/1
    @ResponseBody
    public String test(HttpServletRequest request) {
       *//* HttpSession session = request.getSession(false);
        if(session==null)*//*
        HttpSession session = request.getSession(true);
         long now = System.currentTimeMillis();//Sun Jul 10 21:57:43 EEST 2022
        Date date = new Date(now - session.getLastAccessedTime());

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S", Locale.US);




        Date date1 = new Date(session.getCreationTime());
        Instant current = date1.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(current, ZoneId.systemDefault());
        LocalTime localTime = ldt.toLocalTime();

        return  localTime.toString() ;
    }

*/
  /*private LocalTime convertToLoaclTime(Long time) {
      Date date1 = new Date(time);//Sun Jul 10 22:21:36 EEST 2022
      Instant current = date1.toInstant();
      LocalDateTime ldt = LocalDateTime.ofInstant(current, ZoneId.systemDefault());
      return ldt.toLocalTime();
  }*/


   /* @GetMapping("/check-current-session")//localhost:8080/create-new-session
    @ResponseBody
    public String checkTheSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "The is expired";
        } else {
            return "the session exist with id ="+session.getId()+
                    " and creation date is = " +
                    timeConverter.convertToLocalTime(session.getCreationTime());
        }
    }*/


    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String checkSession(HttpServletRequest request ) {

        HttpSession session= request.getSession(false);
        if(session==null)
            return "redirect:login";
        else
        {
            System.out.println("The session is with id "+session.getId()+"is working");

        }


        try {
            request.logout();
        } catch (ServletException e) {

        }
        System.out.println("Laith ....");
        return "redirect:login";

    }




}
