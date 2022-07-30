package com.eva.sessionManagement.controller;

import com.eva.sessionManagement.Timer.ISessionChecker;
import com.eva.sessionManagement.Timer.SessionChecker;
import com.eva.sessionManagement.model.Login;
import com.eva.sessionManagement.service.UserSessionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log
public class LoginWithSessionController {

    @Autowired
    private UserSessionService userSessionService;
    @Autowired
    private ISessionChecker iSessionChecker;

    @Value("${spring.login.username}")
    private String username;
    @Value("${spring.login.password}")
    private String password;


    @GetMapping(value={"/login","/"})
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name="loginForm") Login login, Model m,
                        HttpServletRequest request) {
        String username = login.getUsername();
        String password = login.getPassword();

        if(username.equals(this.username) && password.equals(this.password)) {
            HttpSession session = request.getSession(false);

            if(session!=null){
               session.invalidate();// this just for testing delete it after finished
                log.warning("this user use this session on other device "+ session.getId());
            }

            if(session== null){
                session = request.getSession(true);
                m.addAttribute("username", username);
                m.addAttribute("password", password);
                userSessionService.addUserSession(session, this.username);


//                ISessionChecker iSessionChecker= new SessionChecker();
//                Thread  t= new Thread(iSessionChecker);
//                t.start();





                return "welcome";
            }

            return "login";
        }
        m.addAttribute("error", "Incorrect Username & Password");
        return "login";
    }

}
