package com.stackroute.userlogin.services;

import com.stackroute.userlogin.domain.User;

public interface UserService {
	User save(User user);

	User findByEmail(String email);

}
