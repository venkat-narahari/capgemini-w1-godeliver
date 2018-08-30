package com.stackroute.userlogin.services;

import com.stackroute.userlogin.domain.User;

public interface UserService {
	User findByEmail(String userEmail);
}
