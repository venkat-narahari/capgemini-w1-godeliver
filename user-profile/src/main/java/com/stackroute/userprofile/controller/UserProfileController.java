package com.stackroute.userprofile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userprofile.domain.UserProfile;
import com.stackroute.userprofile.exceptions.UserAlreadyExistsException;
import com.stackroute.userprofile.exceptions.UserProfileNotFoundException;
import com.stackroute.userprofile.service.UserProfileServices;

@RestController
@RequestMapping("api/v1")
public class UserProfileController {
	
	private UserProfileServices userProfileServiceImpl;
	
	private UserProfileController(UserProfileServices userProfileServiceImpl) {
		this.userProfileServiceImpl=userProfileServiceImpl;
	}
	
	
	@RequestMapping(value="save/user" , method=RequestMethod.POST , produces="application/json")
	public ResponseEntity<?> saveUser(@RequestBody UserProfile userProfile) throws UserAlreadyExistsException{
		try {
			UserProfile savedUser;
			if((savedUser = userProfileServiceImpl.saveUser(userProfile))!=null) {
				return new ResponseEntity<UserProfile>(savedUser,HttpStatus.OK);
			}
			else {
				throw new UserAlreadyExistsException(userProfile);
			}
		}
		catch(UserAlreadyExistsException e) {
			return new ResponseEntity<String>(e.toString(),HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="update/user/{userId}" , method=RequestMethod.PUT , produces="application/json")
	public ResponseEntity<?> updateUser(@PathVariable int userId ,@RequestBody UserProfile userProfile) throws UserProfileNotFoundException{
		try {	
			UserProfile updatedUser;
			if((updatedUser = userProfileServiceImpl.updateUser(userId,userProfile))!=null) {
				return new ResponseEntity<UserProfile>(updatedUser,HttpStatus.OK);
			}
			else {
				throw new UserProfileNotFoundException(userId);
			}
		}
		catch(UserProfileNotFoundException e) {
			return new ResponseEntity<String>(e.toString(),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="user/{userId}" , method=RequestMethod.GET , produces="application/json")
	public ResponseEntity<?> getMovieById(@PathVariable int userId)  throws UserProfileNotFoundException{
		try {	
			UserProfile userProfile;
			if((userProfile= userProfileServiceImpl.getUserById(userId))!=null) {
				return new ResponseEntity<UserProfile>(userProfile,HttpStatus.OK);
			}
			else {
				throw new UserProfileNotFoundException(userId);
			}
		}
		catch(UserProfileNotFoundException e) {
			return new ResponseEntity<String>(e.toString(),HttpStatus.OK);
		}
	}

}
