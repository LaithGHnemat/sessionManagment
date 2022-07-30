package com.eva.sessionManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Login {
	private String username;
	private String password;
	 public Login() {}
		
}
