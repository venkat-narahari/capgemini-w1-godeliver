package com.stackroute.userlogin.services;

import com.stackroute.userlogin.domain.User;

public interface UserService {
	/*find the user email*/
	User findByEmail(String userEmail);
}
