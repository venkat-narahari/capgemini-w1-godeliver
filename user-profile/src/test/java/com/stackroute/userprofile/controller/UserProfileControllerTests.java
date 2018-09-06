//package com.stackroute.userprofile.controller;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.userprofile.domain.UserProfile;
//import com.stackroute.userprofile.service.UserProfileServicesImpl;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserProfileController.class)
//public class UserProfileControllerTests {
//
//	private MockMvc userProfileMockMvc;
//	private UserProfile userProfile;
//
//	@MockBean
//	private UserProfileServicesImpl userProfileServices;
//	
//	@Mock
//	private KafkaTemplate<String,UserProfile> kafkaTemplate;
//
//	@InjectMocks
//	private UserProfileController userProfileController;
//
//	@Before
//	public void setup() throws Exception {
//
//		MockitoAnnotations.initMocks(this);
//		userProfileMockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
//		String[] userPreferences = new String[] { "action", "adventure", "romance" };
//		String[] userAddress = new String[] { "171,stackroute"};
//
//		userProfile = new UserProfile("sumanth", "sumanth@gmail.com", "24-04-1996", "qwerty12", userPreferences, userAddress,  "Male",
//				9876543210L);
//	}
//
//	@Test
//	public void saveUser() throws Exception {
//		when(userProfileServices.saveUser(userProfile)).thenReturn(userProfile);
//		when(kafkaTemplate.send("userprofile",userProfile)).thenReturn(null);
//		this.userProfileMockMvc
//				.perform(post("/api/v1/user").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userProfile)))
//				.andExpect(status().isConflict())
//				.andDo(print());
//		verify(userProfileServices, times(1)).saveUser(Mockito.any(UserProfile.class));
//	}
//
//	@Test
//	public void viewUser() throws Exception {
//		when(userProfileServices.viewUser("sumanth@gmail.com")).thenReturn(userProfile);
//		this.userProfileMockMvc
//				.perform(get("/api/v1/user").param("userEmail", "sumanth@gmail.com"))
//				.andExpect(status().isFound())
//				.andDo(print());
//		verify(userProfileServices, times(1)).viewUser(Mockito.any(String.class));
//	}
//
//	public static String asJsonString(final Object obj) {
//		try {
//			return new ObjectMapper().writeValueAsString(obj);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//}
