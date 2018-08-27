package com.stackroute.userlogin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userlogin.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
//	 User save(User user);

	public User findByEmail(String email);
}
