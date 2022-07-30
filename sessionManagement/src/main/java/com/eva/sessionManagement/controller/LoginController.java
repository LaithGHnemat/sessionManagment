/*
package com.eva.sessionManagement.controller;


import com.eva.sessionManagement.model.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Value("${spring.login.username}")
    private String username;
    @Value("${spring.login.password}")
    private String password;


    @GetMapping(value={"/login","/"})
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name="loginForm") Login login, Model m) {
        String uname = login.getUsername();
        String pass = login.getPassword();
        if(uname.equals(username) && pass.equals(password)) {
            m.addAttribute("uname", uname);
            m.addAttribute("pass", pass);
            return "welcome";
        }
        m.addAttribute("error", "Incorrect Username & Password");
        return "login";
    }
}
*/
