//package com.stackroute.orderdetails.repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import com.stackroute.orderdetails.domain.Location;
//import com.stackroute.orderdetails.domain.Orderdetails;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//public class OrderdetailsRepositoryTest {
//
//	@Autowired
//	private transient OrderdetailsRepository orderdetailsRepository;
//	public void setMovieRepositary(final OrderdetailsRepository orderdetailsRepository) {
//		this.orderdetailsRepository = orderdetailsRepository;
//	}
//
//	/**
//	 * Test to check if user is saved in repository
//	 * @throws Exception
//	 */
//	@Test
//	public void testSaveUser() throws Exception {
//	
//		Location location = new Location("a", "b");
//		orderdetailsRepository.save("haria@gmail.com", "76866666", "bAG", "abc", "9573678154", "8", "16-07-2018",
//			"5:30-6", "4-5", "8", "899", location);
//	final Orderdetails orderdetails= orderdetailsRepository.getByEmailId("haria@gmail.com");
//		assertThat(orderdetails.getEmailId()).isEqualTo("sumanth");
//	}
//
//}