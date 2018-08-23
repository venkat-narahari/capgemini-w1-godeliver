package com.stackroute.userprofile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userprofile.domain.UserProfile;
import com.stackroute.userprofile.exceptions.EmailAlreadyExistsException;
import com.stackroute.userprofile.service.UserProfileServices;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class UserProfileController {
	
	private UserProfileServices userProfileServicesImpl;
	
	@Autowired
	public UserProfileController(UserProfileServices userProfileServicesImpl) {
		this.userProfileServicesImpl=userProfileServicesImpl;
	}
	
	@Autowired
	private KafkaTemplate<String, UserProfile> kafkaTemplate;

	private static final String TOPIC = "user_profile";

	
	@RequestMapping(value="user/save" , method=RequestMethod.POST , produces="application/json")
	public ResponseEntity<?> saveUser(@RequestBody UserProfile user){
		kafkaTemplate.send(TOPIC, user);
		try {
			if((userProfileServicesImpl.saveUser(user))!=null) {
				return new ResponseEntity<String>("User added",HttpStatus.OK);
			}
			else {
				throw new EmailAlreadyExistsException("User already exists with this email!");
			}
		}
		catch (EmailAlreadyExistsException e) {
			return new ResponseEntity<String>(e.toString(),HttpStatus.OK);
		}
					
	}	
	
	@RequestMapping(value="user/update/{userEmail}" , method=RequestMethod.PUT, produces="application/json")
	public ResponseEntity<?> updateUser(@RequestBody UserProfile user, @PathVariable String userEmail){
			userProfileServicesImpl.updateUser(user,userEmail) ;
			return new ResponseEntity<String>("User updated",HttpStatus.OK);
				
		}
	
	@RequestMapping(value="user/view/{userEmail}" , method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> viewUser(@PathVariable String userEmail){
			List<UserProfile> user=userProfileServicesImpl.viewUser(userEmail) ;
			return new ResponseEntity<List<UserProfile>>(user,HttpStatus.OK);
				
		}
					
	}	
	
