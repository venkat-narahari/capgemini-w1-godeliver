package com.stackroute.bookservice.controllertest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.bookservice.controller.BookController;
import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.services.BookServices;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookServiceControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private BookServices bookService;

	@InjectMocks
	private BookController bookController;

	private Book book;
	
	static List<Book> books;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		book=new Book("1627157727",
				"Harry Potter and the Sorcerer's Stone", 
				"http://books.google.com/books/content?id=5MQFrgEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",
				10.5f, "4000ccm", "J.K. Rowling", 
				"Bloomsbury Childrens", "Juvenile Nonfiction", "300Rs", "2014", "303", 
				"Celebrate 20 years of Harry Potter magic with four special", "english");
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

	}

	@Test
	public void testSaveBook() throws Exception {

		when(bookService.saveBook(book)).thenReturn(book);
		mockMvc.perform(post("/api/v1/book").contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
				.andExpect(status().isConflict()).andDo(print());
		verify(bookService, times(1)).saveBook(Mockito.any(Book.class));

	}
	@Test
	public void testGetAllBooks() throws Exception {

		when(bookService.getAllBooks()).thenReturn(books);
		mockMvc.perform(get("/api/v1/books").contentType(MediaType.APPLICATION_JSON).content(asJsonString(books)))
				.andExpect(status().isAccepted()).andDo(print());
		verify(bookService, times(1)).getAllBooks();

	}
	
	@Test
	public void testDeleteBooks() throws Exception {

		when(bookService.deleteBook("Harry Potter and the Sorcerer's Stone")).thenReturn(books);
		mockMvc.perform(delete("/api/v1/{bookTitle}", book.getTitle()).contentType(MediaType.APPLICATION_JSON).content(asJsonString(books)))
				.andExpect(status().isAccepted()).andDo(print());
		verify(bookService, times(1)).deleteBook("Harry Potter and the Sorcerer's Stone");

	}
	
	@Test
	public void testGetByAlphabet() throws Exception {

		when(bookService.findBookByRegexpTitle("Harry")).thenReturn(books);
		mockMvc.perform(get("/api/v1/{term}", "Harry").contentType(MediaType.APPLICATION_JSON).content(asJsonString(books)))
				.andExpect(status().isAccepted()).andDo(print());
		verify(bookService, times(1)).findBookByRegexpTitle("Harry");

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
