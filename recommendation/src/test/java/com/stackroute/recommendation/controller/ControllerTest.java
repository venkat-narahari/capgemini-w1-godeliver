//package com.stackroute.recommendation.controller;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import java.util.List;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.bookservice.domain.Book;
//import com.stackroute.recommendation.domain.BookListener;
//import com.stackroute.recommendation.service.BookService;
//import com.stackroute.recommendation.service.UserService;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(Controller.class)
//public class ControllerTest {
//	private MockMvc mockMvc;
//
//	@MockBean
//	private BookService bookService;
//	private UserService userService;
//	@InjectMocks
//	private Controller controller;
//	String name;
//	String userMail;
//	private BookListener bookListener;
//	static List<Book> books;
//	static List<BookListener> bookList;
//
//	@Before
//	public void setup() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		bookListener = new BookListener("1250153700", "The Adventure Zone: Here There Be Gerblins",
//				"https://image.ibb.co/hoaGAz/adventure.jpg", 4.0f, "1500ccm",
//				"Clint McElroy,Griffin McElroy, Justin McElroy, Travis McElroy", "Carey Pietsch", "fantasy", "250Rs",
//				"2015", "112",
//				"If you told me three-and-a-half years ago that I would soon become fairly obsessed with a hybrid storytelling-comedy podcast in which three brothers played a long form game of Dungeons & Dragons with their dad, I would’ve said… well, yeah, that sounds like me.",
//				"english");
//		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//		// bookList.add(bookListener);
//
//	}
//
//	@Test
//	public void testGetAllBooksFromDb() throws Exception {
//		when(bookService.getAllBooksFromDb()).thenReturn(books);
//		mockMvc.perform(get("/api/v1/books").contentType(MediaType.APPLICATION_JSON).content(asJsonString(books)))
//				.andExpect(status().isOk()).andDo(print());
//		verify(bookService, times(1)).getAllBooksFromDb();
//
//	}
//
//	@Test
//	public void testGetAllBooksByRating() throws Exception {
//		when(bookService.getAllBooksByRating()).thenReturn(bookList);
//		mockMvc.perform(
//				get("/api/v1/rating").contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookListener)))
//				.andExpect(status().isOk()).andDo(print());
//		verify(bookService, times(1)).getAllBooksByRating();
//
//	}
//
//	@Test
//	public void testGetBooksByGenre() throws Exception {
//		when(bookService.getBooksByGenre(name)).thenReturn(bookList);
//		mockMvc.perform(
//				get("/api/v1/genre").contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookListener)))
//				.andExpect(status().isOk()).andDo(print());
//		verify(bookService, times(1)).getBooksByGenre(name);
//
//	}
//
//	@Test
//	public void testGetBooksByAuthor() throws Exception {
//		when(bookService.getBooksByAuthor(name)).thenReturn(bookList);
//		mockMvc.perform(
//				get("/api/v1/author").contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookListener)))
//				.andExpect(status().isOk()).andDo(print());
//		verify(bookService, times(1)).getBooksByGenre(name);
//
//	}
//
//	@Test
//	public void testGetBooksByPreferences() throws Exception {
//		when(userService.getBooksByPreferences(userMail)).thenReturn(bookList);
//		mockMvc.perform(
//				get("/api/v1/preferences").contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookListener)))
//				.andExpect(status().isOk()).andDo(print());
//		verify(userService, times(1)).getBooksByPreferences(userMail);
//
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
