package com.stackroute.recommendation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.domain.User;
import com.stackroute.recommendation.repository.BookRepository;
import com.stackroute.recommendation.repository.UserRepository;

@Configuration
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {
	private BookRepository bookRepository;
	private UserRepository userRepository;

	@Autowired
	public BootstrapData(BookRepository bookRepository, UserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.userRepository=userRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		BookListener book1 = new BookListener();
		book1.setBookISBN_10("123456");
		book1.setTitle("The Rise of Sivagami : Book 1 of Baahubali - Before the Beginning ");
		book1.setRating(7f);
		book1.setPoster("https://images-eu.ssl-images-amazon.com/images/I/518kK%2BoIdIL.jpg");
		book1.setVolume("3000ccm");
		book1.setAuthor("Anand Neelakantan");
		book1.setPublisher("Sumanth");
		book1.setGenre("History");
		book1.setCost("218.00");
		book1.setPublishedYear("2018");
		book1.setPages("494 pages");
		book1.setDescription(
				"Blessed by the sacred Gauriparvat, Mahishmathi is an empire of abundance. The powerful kingdom is flourishing under its king, who enjoys the support and loyalty of his subjects, down to his lowly slaves. But is everything really as it appears, or is the empire hiding its own dirty secret?\n"
						+ "Orphaned at a young age and wrenched away from her foster family, Sivagami is waiting for the day she can avenge the death of her beloved father, cruelly branded a traitor. ");
		book1.setLanguage("English");
		bookRepository.save(book1);

		BookListener book2 = new BookListener();
		book2.setBookISBN_10("987654");
		book2.setTitle("The Girl in Room 105");
		book2.setRating(5f);
		book2.setPoster("https://images-eu.ssl-images-amazon.com/images/I/413Ge3dy3qL.jpg");
		book2.setVolume("3000ccm");
		book2.setAuthor("Chetan Bhagat");
		book2.setPublisher("Sai teja");
		book2.setGenre("Romance");
		book2.setCost("159.00");
		book2.setPublishedYear("2017");
		book2.setPages("312 pages");
		book2.setDescription(
				"Hi, I’m Keshav, and my life is screwed. I hate my job and my girlfriend left me. Ah, the beautiful Zara. Zara is from Kashmir. She is a Muslim. And did I tell you my family is a bit, well, traditional? Anyway, leave that.\n"
						+ "\n"
						+ "Zara and I broke up four years ago. She moved on in life. I didn’t. I drank every night to forget her. I called, messaged, and stalked her on social media. She just ignored me.\n"
						+ "\n" + "However, that night, on the eve of h");
		book2.setLanguage("English");
		bookRepository.save(book2);

		BookListener book3 = new BookListener();
		book3.setBookISBN_10("456378");
		book3.setTitle("Thirteen Reasons Why (TV Tie-in)");
		book3.setRating(9f);
		book3.setPoster("https://images-na.ssl-images-amazon.com/images/I/41YOGfaGsEL._SX324_BO1,204,203,200_.jpg");
		book3.setVolume("4000ccm");
		book3.setAuthor("Jay Asher");
		book3.setPublisher("Mass Market Paperback");
		book3.setGenre("Thriller");
		book3.setCost("216.00");
		book3.setPublishedYear("2017");
		book3.setPages("211 pages");
		book3.setDescription(
				"Read this sensational mystery bestseller before you watch the 13-part Netflix series, executive produced by Selena Gomez. This special edition is complete with exclusive behind-the-scenes content including a 16-page full-colour photo insert featuring scenes from the show and interviews with the cast and crew. You can't stop the future. You can't rewind the past. The only way to learn the secret . . . is to press play. ");
		book3.setLanguage("English");
		bookRepository.save(book3);

		BookListener book4 = new BookListener();
		book4.setBookISBN_10("438763");
		book4.setTitle("Will You Still Love Me?");
		book4.setRating(8f);
		book4.setPoster("https://images-na.ssl-images-amazon.com/images/I/51%2Bg-0Yqk2L._SX324_BO1,204,203,200_.jpg");
		book4.setVolume("4000ccm");
		book4.setAuthor("RAVINDER SINGH ");
		book4.setPublisher("Leela");
		book4.setGenre("Romance");
		book4.setCost("109.00");
		book4.setPublishedYear("2018");
		book4.setPages("312 pages");
		book4.setDescription(
				"Lavanya Gogoi is from the scenic hills of Shillong while Rajveer Saini belongs to the shahi city of Patiala. Worlds apart from one another, the two land up next to each other on a flight from Mumbai to Chandigarh. It's love at first flight, at least for one of them. For the other . . . well, it's going to take more than a plane ride!");
		book4.setLanguage("English");
		bookRepository.save(book4);

		BookListener book5 = new BookListener();
		book5.setBookISBN_10("634577");
		book5.setTitle("The Complete Novels of Sherlock Holmes");
		book5.setRating(6f);
		book5.setPoster("https://images-na.ssl-images-amazon.com/images/I/51kLfe5y17L._SX321_BO1,204,203,200_.jpg");
		book5.setVolume("3500ccm");
		book5.setAuthor("Arthur Conan Doyle");
		book5.setPublisher("Anmisha");
		book5.setGenre("Thriller");
		book5.setCost("149.00");
		book5.setPublishedYear("2016");
		book5.setPages("350 pages");
		book5.setDescription(
				"There’s the scarlet thread of murder running through the colourless skein of life and our duty is to unravel it and isolate it and expose every inch of it.”Sherlock Holmes Consulting Detective 221B Baker Street London.\n"
						+ "This is where begins a historical partnership between Dr. Watson—the archetypal gentleman from the Victorian era—and the eccentric, legendary sleuth, Sherlock Holmes. Join them as they gather clues, ranging from bloodstains and footprints to cigarette ash and wedding rings and arrive at unusual and surprising conclusions. This book is a collection of the four novels written by Sir Arthur Conan Doyle: A Study in Scarlet (1887), The Sign of the Four (1890), The Hound of the Baskervilles (1902) and The Valley of Fear (1915). Featuring the timeless detective Sherlock Holmes, these novels have been successfully engrossing readers for more than a century now.");
		book5.setLanguage("English");
		bookRepository.save(book5);
		
		String[] userPreferences=new String[] {"comic","adventure","action"};
		User user=new User();
		user.setUserName("stackroute");
		user.setUserEmail("stackroute@gmail.com");
		user.setUserDob("18-06-2018");
		user.setUserPassword("qwerty12");
		user.setUserPreferences(userPreferences);
		user.setUserGender("male");
		user.setUserMobile(9807654321L);
		userRepository.save(user);
	}
}