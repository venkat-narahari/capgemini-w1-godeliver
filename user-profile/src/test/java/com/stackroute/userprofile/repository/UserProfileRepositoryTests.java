package com.stackroute.userprofile.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userprofile.domain.UserProfile;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserProfileRepositoryTests {
	
	@Autowired
	private transient UserProfileRepository userProfileRepository;
	
	public void setMovieRepositary(final UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	/**
	 * Test to check if user is saved in repository
	 * @throws Exception
	 */
	@Test
	public void testSaveUser() throws Exception {
		String[] userPreferences = new String[] { "action", "adventure", "romance" };
		userProfileRepository.save(new UserProfile("sumanth", "sumanth@gmail.com", "24-04-1996", "qwerty12", userPreferences, "Male",98766543210L));
		final UserProfile userProfile = userProfileRepository.getByUserEmail("sumanth@gmail.com");
		assertThat(userProfile.getUserName()).isEqualTo("sumanth");
	}

}

