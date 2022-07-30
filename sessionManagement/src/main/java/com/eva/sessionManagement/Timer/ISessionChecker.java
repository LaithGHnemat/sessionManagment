package com.eva.sessionManagement.Timer;

import javax.servlet.http.HttpServletRequest;

public interface ISessionChecker extends Runnable{

    void checkSession(HttpServletRequest request);
}
