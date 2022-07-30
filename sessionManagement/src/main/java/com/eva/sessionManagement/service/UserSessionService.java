package com.eva.sessionManagement.service;

import com.eva.sessionManagement.exptions.NotFoundSessionException;
import com.eva.sessionManagement.model.UserSession;
import com.eva.sessionManagement.repository.UserSessionsRepository;
import com.eva.sessionManagement.utilty.ITimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;

@Service
public class UserSessionService {

    @Autowired
    private UserSessionsRepository userSessionRepository;
    @Autowired
    private ITimeConverter timeConverter;

    public List<UserSession> getAllSessions() {
        return this.userSessionRepository.findAll();
    }

    public void addUserSession(HttpSession session, String username) {
        UserSession userSession= new UserSession();
        userSession.setUsername(username);
        userSession.setSessionId(String.valueOf(session.getId()));
        userSession.setCreationTime(timeConverter.convertToLocalTime(session.getCreationTime()));
        userSession.setLastAccessedTime(timeConverter.convertToLocalTime(session.getLastAccessedTime()));
        userSession.setTimeout(session.getMaxInactiveInterval());
        this.userSessionRepository.save(userSession);
    }

   /* private LocalTime convertToLocalTime(long creationTime) {
        Date date1 = new Date(creationTime);//Sun Jul 10 22:21:36 EEST 2022
        Instant current = date1.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(current, ZoneId.systemDefault());
        LocalTime localTime = ldt.toLocalTime();
        return localTime;
    }*/

    public void addSession(UserSession userSession){
        this.userSessionRepository.save(userSession);

    }

    private UserSession getSessionId(long sessionId) {
        UserSession userSession = userSessionRepository.findById(sessionId)
                .orElseThrow(() -> new NotFoundSessionException("Session not found for this id :: " + sessionId));
        return userSession;
    }

    public LocalTime getSessionAge(long sessionId) {
        UserSession session = getSessionId(sessionId);
        LocalTime age = LocalTime.now().minusHours(session.getCreationTime().getHour());
        age.minusMinutes(session.getCreationTime().getMinute());
        return age;
    }

//    public long getRemainingTime (long sessionId) {
//        UserSession session = getSessionId(sessionId);
//        final long now = System.currentTimeMillis();
//        long lastAccessed = session.getLastAccessedTime();
//        long timeoutPeriod = session.getTimeout();
//        return  ((timeoutPeriod * 1000) - (now - lastAccessed))/1000;
//    }
}
