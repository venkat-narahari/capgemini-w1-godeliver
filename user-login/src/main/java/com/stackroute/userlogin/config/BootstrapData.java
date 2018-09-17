package com.stackroute.userlogin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.stackroute.userlogin.domain.User;
import com.stackroute.userlogin.repository.UserRepository;

@Configuration
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

	UserRepository userRepository;

	@Autowired
	public BootstrapData(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		User user = new User();
		user.setUserEmail("stackroute@gmail.com");
		user.setUserPassword("qwerty12");
		userRepository.save(user);
		
		User admin = new User();
		admin.setUserEmail("admin@gmail.com");
		admin.setUserPassword("admin123");
		userRepository.save(admin);
	}

}
