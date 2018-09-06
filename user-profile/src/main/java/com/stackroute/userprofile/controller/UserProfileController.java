package com.stackroute.userprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userprofile.domain.UserProfile;
import com.stackroute.userprofile.exceptions.EmailAlreadyExistsException;
import com.stackroute.userprofile.exceptions.MongoConnectionException;
import com.stackroute.userprofile.exceptions.UserNotExistsException;
import com.stackroute.userprofile.service.UserProfileServices;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UserProfileController {

	private UserProfileServices userProfileServicesImpl;

	@Autowired
	public UserProfileController(UserProfileServices userProfileServicesImpl) {
		this.userProfileServicesImpl = userProfileServicesImpl;
	}

	/**
	 * controller for mapping to save the user
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> saveUser(@RequestBody UserProfile user) {

		try {
			UserProfile userProfile;
			try {
				userProfile = userProfileServicesImpl.saveUser(user);
			} catch (MongoConnectionException e) {
				return new ResponseEntity<String>("Connection Failed", HttpStatus.GATEWAY_TIMEOUT);
			}
			if (userProfile != null) {
				return new ResponseEntity<String>("User added", HttpStatus.CREATED);
			} else {
				throw new EmailAlreadyExistsException("User already exists with this email!");
			}
		} catch (EmailAlreadyExistsException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}

	}

	/**
	 * controller for mapping to update the user
	 */
	@RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody UserProfile user) {
		try {
			UserProfile updatedUser;
			try {
				updatedUser = userProfileServicesImpl.updateUser(user);
			} catch (MongoConnectionException e) {
				return new ResponseEntity<String>("Connection Failed", HttpStatus.GATEWAY_TIMEOUT);
			}
			if (updatedUser != null) {
				return new ResponseEntity<String>("User updated", HttpStatus.OK);
			} else {
				throw new UserNotExistsException("User doesn't exists with this email!");
			}
		} catch (UserNotExistsException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * controller for mapping to view the user
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> viewUser(@RequestParam("userEmail") String userEmail) {
		try {
			UserProfile user;
			try {
				user = userProfileServicesImpl.viewUser(userEmail);
			} catch (MongoConnectionException e) {
				return new ResponseEntity<String>("Connection Failed", HttpStatus.GATEWAY_TIMEOUT);
			}
			if (user != null) {
				return new ResponseEntity<UserProfile>(user, HttpStatus.FOUND);
			} else {
				throw new UserNotExistsException("User doesn't exists with this email!");
			}
		} catch (UserNotExistsException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.NOT_FOUND);
		}

	}
	
}
