package com.eva.sessionManagement;

import com.eva.sessionManagement.Timer.ISessionChecker;
import com.eva.sessionManagement.Timer.SessionChecker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import javax.servlet.http.HttpServletRequest;


@ServletComponentScan
@SpringBootApplication

public class SessionManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(SessionManagementApplication.class, args);
	}




}
