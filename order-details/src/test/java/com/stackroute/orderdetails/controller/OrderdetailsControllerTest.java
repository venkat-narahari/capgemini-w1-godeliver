//package com.stackroute.orderdetails.controller;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.orderdetails.domain.Location;
//import com.stackroute.orderdetails.domain.Orderdetails;
//import com.stackroute.orderdetails.service.OrderdetailsServiceImpl;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(OrderdetailsController.class)
//public class OrderdetailsControllerTest {
//	private MockMvc orderdetailsMockMvc;
//	private Orderdetails orderdetails;
//	@MockBean
//	private OrderdetailsServiceImpl orderdetailsServiceImpl;
//
//	@Mock
//	private KafkaTemplate<String, Orderdetails> kafkaTemplate;
//
//	@InjectMocks
//	private OrderdetailsController orderdetailsController;
//
//	@Before
//	public void setup() throws Exception {
//
//		MockitoAnnotations.initMocks(this);
//		orderdetailsMockMvc = MockMvcBuilders.standaloneSetup(orderdetailsController).build();
//		Location location = new Location("a", "b");
//		orderdetails = new Orderdetails("haria@gmail.com", "76866666", "bAG", "abc", "9573678154", "8", "16-07-2018",
//				"5:30-6", "4-5", "8", "899", location);
//	}
//	
//	@Test
//	public void saveDetails() throws Exception {
//		when(orderdetailsServiceImpl.saveDetails(orderdetails)).thenReturn(orderdetails);
//		when(kafkaTemplate.send("orderdetails",orderdetails)).thenReturn(null);
//		this.orderdetailsMockMvc
//				.perform(post("/api/v1/detail").contentType(MediaType.APPLICATION_JSON).content(asJsonString(orderdetails)))
//				.andExpect(status().isOk())
//				.andDo(print());
//		verify(orderdetailsServiceImpl, times(1)).saveDetails(Mockito.any(Orderdetails.class));
//	}
//
//	@Test
//	public void getdetailsByEmail() throws Exception {
//		when(orderdetailsServiceImpl.getByEmailId("haria@gmail.com")).thenReturn(orderdetails);
//		this.orderdetailsMockMvc
//				.perform(get("/api/v1/detail").param("emailId", "haria@gmail.com"))
//				.andExpect(status().isFound())
//				.andDo(print());
//		verify(orderdetailsServiceImpl, times(1)).getByEmailId(Mockito.any(String.class));
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
