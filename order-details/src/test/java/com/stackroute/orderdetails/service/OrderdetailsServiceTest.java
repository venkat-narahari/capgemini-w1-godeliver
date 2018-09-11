//package com.stackroute.orderdetails.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//import com.stackroute.orderdetails.domain.Location;
//import com.stackroute.orderdetails.domain.Orderdetails;
//import com.stackroute.orderdetails.exceptions.DetailsAlreadyExistsException;
//import com.stackroute.orderdetails.exceptions.MongoConnectionException;
//import com.stackroute.orderdetails.repository.OrderdetailsRepository;
//
//@RunWith(SpringRunner.class)
//public class OrderdetailsServiceTest {
//
//		//private MockMvc orderdetailsMockMvc;
//		
//		@Mock
//		private OrderdetailsRepository orderdetailsRepository;
//
//		@Mock
//		private KafkaTemplate<String, Orderdetails> kafkaTemplate;
//
//		@InjectMocks
//		private OrderdetailsService orderdetailsService;
//		
//		private Orderdetails orderdetails;
//
//		@Before
//		public void setup() throws Exception {
//
//			MockitoAnnotations.initMocks(this);
//			//orderdetailsMockMvc = MockMvcBuilders.standaloneSetup(orderdetailsController).build();
//			Location location = new Location("a", "b");
//			orderdetails = new Orderdetails("haria@gmail.com", "76866666", "bAG", "abc", "9573678154", "8", "16-07-2018",
//					"5:30-6", "4-5", "8", "899", location);
//		}
//		
//		
//
//		/**
//		 * Test for Mock object creation
//		 */
//		@Test
//		public void testMockCreation() {
//			assertNotNull(orderdetails);
//			assertNotNull("jpaRepository creation fails: use @injectMocks on userProfileServicempl", orderdetailsRepository);
//		}
//	
//		/**
//		 * Test for successfully saving order details
//		 * 
//		 * @throws Exception
//		 */
//		@Test
//		public void testSaveDetailsSuccess() throws Exception {
//			when(orderdetailsRepository.save(orderdetails)).thenReturn(orderdetails);
//			when(kafkaTemplate.send("orderdetails", orderdetails)).thenReturn(null);
//			Orderdetails flag = orderdetailsService.saveDetails(orderdetails);
//			assertEquals("Saving  failed , the call to DAOImpl is returning false ,check this method", this.orderdetails,
//					flag);
//			verify(orderdetailsRepository, times(1)).save(orderdetails);
//		}
//	
//		/**
//		 * Test for failing to save order details
//		 * 
//		 * @throws DetailsAlreadyExistsException
//		 * @throws MongoConnectionException 
//		 */
//		@Test(expected = DetailsAlreadyExistsException.class)
//		public void testSaveUserFailure() throws DetailsAlreadyExistsException, MongoConnectionException {
//			when(orderdetailsRepository.getByEmailId("haria@gmail.com")).thenReturn(orderdetails);
//			when(orderdetailsRepository.save(orderdetails)).thenReturn(orderdetails);
//			final Orderdetails flag = orderdetailsService.saveDetails(orderdetails);
//			if (flag == null) {
//				throw new DetailsAlreadyExistsException("Details Already Exists");
//			}
//	
//		}
//	
//
//		/**
//		 * Test to display user details of specific email
//		 * 
//		 * @throws Exception
//		 */
//		@Test
//		public void testViewUser() throws Exception {
//			when(orderdetailsRepository.getByEmailId("haria@gmail.com")).thenReturn(orderdetails);
//			Orderdetails orderdetails = orderdetailsService.getByEmailId("haria@gmail.com");
//			assertEquals("fetching  by id failed", orderdetails, orderdetails);
//			verify(orderdetailsRepository, times(2)).getByEmailId(orderdetails.getEmailId());
//		}
//
//}