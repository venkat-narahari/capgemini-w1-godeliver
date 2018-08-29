package com.stackroute.userlogin.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userlogin.domain.Token;
import com.stackroute.userlogin.domain.User;
import com.stackroute.userlogin.exceptions.PasswordNotMatchException;
import com.stackroute.userlogin.exceptions.UserNameNotFoundException;
import com.stackroute.userlogin.exceptions.UserNameOrPasswordEmpty;
import com.stackroute.userlogin.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody User login) throws ServletException {

		String jwtToken = "";

		try {
			if (login.getUserEmail() == null || login.getUserPassword() == null) {
				throw new UserNameOrPasswordEmpty("Please fill in username and password");
			}
		} catch (UserNameOrPasswordEmpty e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}

		String email = login.getUserEmail();
		String password = login.getUserPassword();
		User user = userService.findByEmail(email);
		
		try {
			if (user == null) {
				throw new UserNameNotFoundException("User email not found.");
			}
		} catch (UserNameNotFoundException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}
		
		String pwd = user.getUserPassword();
		
		try {
			if (!password.equals(pwd)) {
				throw new PasswordNotMatchException("Invalid login. Please check your name and password.");
			}
		} catch (PasswordNotMatchException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}

		jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		Token token = new Token();
		token.setEmail(email);
		token.setToken(jwtToken);
		return new ResponseEntity<>(token, HttpStatus.CREATED);

	}
}
