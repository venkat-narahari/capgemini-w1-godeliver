package com.stackroute.userlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userlogin.domain.User;
import com.stackroute.userlogin.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class SecureController {

	/*allowed only autunticated users*/
	@Autowired
	private UserService userService;

	@RequestMapping("secue/user")
	public String loginSuccess() {
		return "Login Successful!";
	}

	@RequestMapping(value = "secure/email", method = RequestMethod.POST)
	public User findByEmail(@RequestBody String email) {
		return userService.findByEmail(email);
	}

}
