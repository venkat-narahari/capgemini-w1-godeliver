//package com.stackroute.userlogin.controllertest;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.stackroute.userlogin.controller.UserController;
//import com.stackroute.userlogin.domain.User;
//import com.stackroute.userlogin.services.UserService;
//
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//public class UserControllerTest {
//	
//	private MockMvc mockMvc;
//	
//	@InjectMocks
//	private UserController userController;
//
//	@MockBean
//	private UserService userService;
//	
//	private User user;
//	
//	
//	/*@Before
//	public void setUp() throws Exception {
//
//		MockitoAnnotations.initMocks(this);
//
//		user = new User((long) 1,"teja","kathari","tejakathari007@gmail.com","teja007",null);
//		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//	}*/
//	
//	
//	/*
//	@Test
//	public void findByEmail() throws Exception {
//		when(userService.findByEmail("tejakathari007@gmail.com")).thenReturn(user);
//		mockMvc.perform(post("/user/login")
//				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());
//		verify(userService, times(1)).findByEmail("tejakathari007@gmail.com");
//		verifyNoMoreInteractions(userService);	
//	}
//	*/
//	
//
//}
