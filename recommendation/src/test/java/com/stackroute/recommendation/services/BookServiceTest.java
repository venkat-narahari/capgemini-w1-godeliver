//package com.stackroute.recommendation.services;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import com.stackroute.recommendation.domain.BookListener;
//import com.stackroute.recommendation.repository.BookRepository;
//import com.stackroute.recommendation.service.BookService;
//
//public class BookServiceTest {
//
//
//	@Mock
//	private BookRepository bookRepo;
//
//	@InjectMocks
//	private BookService bookService;
//
//	private BookListener bookListener;
//
//	@Before
//	public void setupMock() {
//		MockitoAnnotations.initMocks(this);
//		bookListener=new BookListener("1250153700","The Adventure Zone: Here There Be Gerblins","https://image.ibb.co/hoaGAz/adventure.jpg",(float)4.0,"1500ccm","Clint McElroy,Griffin McElroy, Justin McElroy, Travis McElroy","Carey Pietsch","fantasy","250Rs","2015","112","If you told me three-and-a-half years ago that I would soon become fairly obsessed with a hybrid storytelling-comedy podcast in which three brothers played a long form game of Dungeons & Dragons with their dad, I would’ve said… well, yeah, that sounds like me.","english");
//	}
//	
//	@Test
//	public void testGetAllBooksByRating() {
////		when(bookRepo.getBooksByGenre("fantasy")).thenReturn((List<BookListener>) bookListener);
//		
//	}
//}
