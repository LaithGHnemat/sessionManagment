
package com.eva.sessionManagement.Listener;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

//@WebListener
@Component
@Log
public class CustomSessionListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        log.info(" create a new session , we are inside the CustomSessionListener");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        log.info("destroyed a new session , we are inside the CustomSessionListener");
    }

}

