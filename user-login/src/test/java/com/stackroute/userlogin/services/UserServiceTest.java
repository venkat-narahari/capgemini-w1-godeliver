package com.stackroute.userlogin.services;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.userlogin.domain.User;
import com.stackroute.userlogin.repository.UserRepository;

public class UserServiceTest {
	
	@Mock
	private UserRepository userRepo;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private User user;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		user = new User((long) 1, "tejakathari007", "teja007", null);

	}
	
	@Test
	public void testUpdateMovie() throws Exception {
		when(userRepo.findByUserEmail("tejakathari007")).thenReturn(user);
		User user = userServiceImpl.findByEmail("tejakathari007");
		assertEquals("teja007",user.getUserPassword());
	}

}
