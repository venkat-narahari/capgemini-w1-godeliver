//package com.stackroute.bookservice.servicestest;
//
//import static org.junit.Assert.assertNotNull;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.stackroute.bookservice.domain.Book;
//import com.stackroute.bookservice.repository.BookRepository;
//import com.stackroute.bookservice.services.BookServicesImpl;
//
//public class BookServiceImplTest {
//
//	@Mock
//	private BookRepository bookRepo;
//
//	@InjectMocks
//	private BookServicesImpl bookServiceImpl;
//
//	private Book book;
//
//	@Before
//	public void setupMock() {
//		MockitoAnnotations.initMocks(this);
//		bookServiceImpl = new BookServicesImpl(bookRepo, null);
//		book = new Book("12345", "baahubali", "baahubali poster", 10.5f, "4000ccm", "shiri", "xxxx", "romance", "500rs",
//				"2018", "123", "hjasdgfd hbdj", "english");
//
//	}
//
//	@Test
//	public void testMockCreation() {
//		assertNotNull(book);
//		assertNotNull("jpaRepository creation fails: use @injectMocks on bookServicempl", bookRepo);
//	}
//
//}
