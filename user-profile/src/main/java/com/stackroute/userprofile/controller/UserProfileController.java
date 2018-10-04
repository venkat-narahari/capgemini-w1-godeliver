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
				return new ResponseEntity<UserProfile>(user, HttpStatus.ACCEPTED);
			} else {
				throw new UserNotExistsException("User doesn't exists with this email!");
			}
		} catch (UserNotExistsException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.NOT_FOUND);
		}

	}
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> changePassword(@RequestBody UserProfile userDetails) throws ServletException {

		try {
			if (userDetails.getUserEmail() == null || userDetails.getUserDob() == null) {
				throw new UserNameOrDobEmptyException("Please fill in useremail and date of birth");
			}
		} catch (UserNameOrDobEmptyException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}

		String email = userDetails.getUserEmail();
		String dateOfBirth = userDetails.getUserDob();
		UserProfile user = userProfileServicesImpl.viewUser(email);

		try {
			if (user == null) {
				throw new UserNotExistsException("User email not found.");
			}
		} catch (UserNotExistsException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}

		String dob = user.getUserDob();

		try {
			if (!dateOfBirth.equals(dob)) {
				throw new DobNotMatchException("Invalid login. Please check your name and date of birth.");
			}
		} catch (DobNotMatchException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}
		String password = userDeatils.getUserPassword();
		UserProfile userProfile = new Userprofile();
		userProfile.setUserEmail(email);
		userProfile.setUserPassword(password);
		return new ResponseEntity<>(userProfile, HttpStatus.CREATED);


	
}
