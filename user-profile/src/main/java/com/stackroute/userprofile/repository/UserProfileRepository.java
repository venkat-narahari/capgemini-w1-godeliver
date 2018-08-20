package com.stackroute.userprofile.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userprofile.domain.UserProfile;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer>{
	

}
