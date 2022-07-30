package com.eva.sessionManagement.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/newUserSession").setViewName("newUserSession");
        registry.addViewController("/sessionsDecor").setViewName("sessionsDecor");
        registry.addViewController("/sessions").setViewName("sessions");
        registry.addViewController("/welcome").setViewName("welcome");
    }

}
