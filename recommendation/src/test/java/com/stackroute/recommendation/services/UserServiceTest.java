//package com.stackroute.recommendation.services;
//
//import java.util.List;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import com.stackroute.bookservice.domain.Book;
//import com.stackroute.recommendation.controller.Controller;
//import com.stackroute.recommendation.domain.BookListener;
//import com.stackroute.recommendation.domain.User;
//import com.stackroute.recommendation.service.UserService;
//@RunWith(SpringRunner.class)
//@WebMvcTest(Controller.class)
//public class UserServiceTest {
//	
//	
//		private MockMvc mockMvc;
//
//		@MockBean
//		private UserService userService;
//
//		@InjectMocks
//		private Controller controller;
//		String name;
//		String userMail;
//		private BookListener bookListener;
//		private User user;
//		static List<Book> books;
//		static List<BookListener> bookList;
//		
//		
////		 String userName;
////			private String userEmail;
////			private String userDob;
////			private String userPassword;
////			private String[] userPreferences;
////			private String userGender;
////			private double userMobile;
//
//		@Before
//		public void setup() throws Exception{
//			MockitoAnnotations.initMocks(this);
//			//user=new User("haritha@gmail.com","27/07/1996","root123",["comics","fiction"],"Female",9573654) {;
//			//bookListener=new BookListener("1250153700","The Adventure Zone: Here There Be Gerblins","https://image.ibb.co/hoaGAz/adventure.jpg",4.0f,"1500ccm","Clint McElroy,Griffin McElroy, Justin McElroy, Travis McElroy","Carey Pietsch","fantasy","250Rs","2015","112","If you told me three-and-a-half years ago that I would soon become fairly obsessed with a hybrid storytelling-comedy podcast in which three brothers played a long form game of Dungeons & Dragons with their dad, I would’ve said… well, yeah, that sounds like me.","english");
//			mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//		}
//		
//		
//	
//	
//}
