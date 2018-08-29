package com.stackroute.userprofile.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userprofile.domain.UserProfile;
import com.stackroute.userprofile.service.UserProfileServices;

@RunWith(SpringRunner.class)
@WebMvcTest(UserProfileController.class)
public class UserProfileControllerTests {
	
	private MockMvc userProfileMockMvc;
	
	String[] userPreferences= new String[] {"action","adventure","romance"};
	
	@MockBean
	private UserProfileServices userProfileServices;
	
	@InjectMocks
	private UserProfileController userProfileController;
	
	private UserProfile userProfile;
	static List<UserProfile> userProfiles;
	
	@Before
	public void setup() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		userProfile = new UserProfile("sumanth","sumanthreddy@gmail.com","24-04-1996","qwerty12",userPreferences,"Male",1234567890);
		
		userProfileMockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
		
		userProfiles=new ArrayList<>();

		userProfiles.add(userProfile);
	}

	@Test
	public void userSave() throws Exception {
		when(userProfileServices.saveUser(userProfile)).thenReturn(userProfile);
		
		userProfileMockMvc.perform(post("/api/v1/user").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userProfile)))
		.andExpect(status().isCreated()).andDo(print());
		verify(userProfileServices, times(1)).saveUser(Mockito.any(UserProfile.class));
	}
	
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
