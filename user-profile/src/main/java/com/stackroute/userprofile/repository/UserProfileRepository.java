package com.stackroute.userprofile.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userprofile.domain.UserProfile;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, String> {

	public List<UserProfile> getByUserEmail(String userEmail);
}
