package com.stackroute.userprofile.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userprofile.domain.UserProfile;
import com.stackroute.userprofile.exceptions.EmailAlreadyExistsException;
import com.stackroute.userprofile.exceptions.MongoConnectionException;
import com.stackroute.userprofile.exceptions.UserNotExistsException;
import com.stackroute.userprofile.repository.UserProfileRepository;

@RunWith(SpringRunner.class)
public class UserProfileServicesImplTests {

	@Mock
	private UserProfileRepository userProfileRepo;

	@Mock
	private KafkaTemplate<String, UserProfile> kafkaTemplate;

	@InjectMocks
	private UserProfileServicesImpl userProfileServicesImpl;

	private UserProfile userProfile;

	@Before
	public void setup() throws Exception {

		MockitoAnnotations.initMocks(this);
		String[] userPreferences = new String[] { "action", "adventure", "romance" };

		userProfile = new UserProfile("sumanth", "sumanth@gmail.com", "24-04-1996", "qwerty12", userPreferences, "Male",
				9876543210L,null);

	}

	/**
	 * Test for Mock object creation
	 */
	@Test
	public void testMockCreation() {
		assertNotNull(userProfile);
		assertNotNull("jpaRepository creation fails: use @injectMocks on userProfileServicempl", userProfileRepo);
	}

	/**
	 * Test for successfully saving user details
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveUserSuccess() throws Exception {
		when(userProfileRepo.save(userProfile)).thenReturn(userProfile);
		when(kafkaTemplate.send("userprofile", userProfile)).thenReturn(null);
		UserProfile flag = userProfileServicesImpl.saveUser(userProfile);
		assertEquals("Saving  failed , the call to DAOImpl is returning false ,check this method", this.userProfile,
				flag);
		verify(userProfileRepo, times(1)).save(userProfile);
	}

	/**
	 * Test for failing to save user details
	 * 
	 * @throws EmailAlreadyExistsException
	 * @throws MongoConnectionException 
	 */
	@Test(expected = EmailAlreadyExistsException.class)
	public void testSaveUserFailure() throws EmailAlreadyExistsException, MongoConnectionException {
		when(userProfileRepo.getByUserEmail("sumanth@gmail.com")).thenReturn(userProfile);
		when(userProfileRepo.save(userProfile)).thenReturn(userProfile);
		final UserProfile flag = userProfileServicesImpl.saveUser(userProfile);
		if (flag == null) {
			throw new EmailAlreadyExistsException("User Already Exists");
		}

	}

	/**
	 * Test to check if user details are updated
	 * 
	 * @throws UserNotExistsException
	 * @throws MongoConnectionException 
	 */
	@Test
	public void testUpdateUser() throws UserNotExistsException, MongoConnectionException {
		when(userProfileRepo.getByUserEmail("sumanth@gmail.com")).thenReturn(userProfile);
		when(userProfileRepo.save(userProfile)).thenReturn(userProfile);
		userProfile.setUserName("Sum");
		UserProfile userProfile1 = userProfileServicesImpl.updateUser(userProfile);
		assertEquals("Updating  failed", "Sum", userProfile1.getUserName());
		verify(userProfileRepo, times(1)).save(userProfile);
		verify(userProfileRepo, times(1)).getByUserEmail(userProfile.getUserEmail());
	}

	/**
	 * Test to display user details of specific email
	 * 
	 * @throws Exception
	 */
	@Test
	public void testViewUser() throws Exception {
		when(userProfileRepo.getByUserEmail("sumanth@gmail.com")).thenReturn(userProfile);
		UserProfile userProfile2 = userProfileServicesImpl.viewUser("sumanth@gmail.com");
		assertEquals("fetching  by id failed", userProfile2, userProfile);
		verify(userProfileRepo, times(2)).getByUserEmail(userProfile.getUserEmail());
	}
}