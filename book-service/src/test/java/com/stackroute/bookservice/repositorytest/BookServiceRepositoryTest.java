//package com.stackroute.bookservice.repositorytest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.stackroute.bookservice.domain.Book;
//import com.stackroute.bookservice.repository.BookRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//public class BookServiceRepositoryTest {
//	
//	List<Book> books;
//	Book book;
//	@Autowired
//	private transient BookRepository bookRepository;
//	public void setBookServiceRepository(final BookRepository bookRepository) {
//		this.bookRepository = bookRepository;
//	}
//
//	@Test
//	public void testFindBookByRegexpTitle() throws Exception {
//		
//		bookRepository.save(new Book("12345","baahubali","baahubali poster",10.5f,"4000ccm","shiri","xxxx","romance","500rs","2018","123","hjasdgfd hbdj","english"));
//		books = bookRepository.findBookByRegexpTitle("baahubali");
//		assertThat(books.get(0).getTitle()).isEqualTo("baahubali");
//	}
//	
//	@Test
//	public void testGetBookByTitle() throws Exception {
//		
//		bookRepository.save(new Book("12345","baahubali","baahubali poster",10.5f,"4000ccm","shiri","xxxx","romance","500rs","2018","123","hjasdgfd hbdj","english"));
//		books = bookRepository.getByBookTitle("baahubali");
//		assertThat(books.get(0).getTitle()).isEqualTo("baahubali");
//	}
//	
//
//}
