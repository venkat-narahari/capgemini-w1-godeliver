package com.stackroute.userprofile.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.stackroute.userprofile.domain.UserProfile;
import com.stackroute.userprofile.repository.UserProfileRepository;

@Configuration
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

	UserProfileRepository userProfileRepository;

	@Autowired
	public BootstrapData(UserProfileRepository userProfileRepository) {
		super();
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		String[] userPreferences=new String[] {"comic","adventure","action"};
		String[] userAddress=new String[] {"171,above sbi,opposite raheja arcade"};
		UserProfile userProfile = new UserProfile();
		userProfile.setUserName("stackroute");
		userProfile.setUserEmail("stackroute@gmail.com");
		userProfile.setUserDob("18-06-2018");
		userProfile.setUserPassword("qwerty12");
		userProfile.setUserPreferences(userPreferences);
		userProfile.setUserAddress(userAddress);
		userProfile.setUserGender("male");
		userProfile.setUserMobile(9807654321L);
		userProfileRepository.save(userProfile);
	}

}