package com.stackroute.recommendation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.stackroute.recommendation.domain.Author;
import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.domain.Genre;
import com.stackroute.recommendation.domain.User;
import com.stackroute.recommendation.relations.OfType;
import com.stackroute.recommendation.relations.WrittenBy;
import com.stackroute.recommendation.repository.AuthorRepository;
import com.stackroute.recommendation.repository.BookRepository;
import com.stackroute.recommendation.repository.GenreRepository;
import com.stackroute.recommendation.repository.OfTypeRepository;
import com.stackroute.recommendation.repository.UserRepository;
import com.stackroute.recommendation.repository.WrittenByRepository;

@Configuration
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {
	private BookRepository bookRepository;
	private UserRepository userRepository;
	private GenreRepository genreRepository;
	private OfTypeRepository ofTypeRepository;
	private AuthorRepository authorRepository;
	private WrittenByRepository writtenByRepository;

	@Autowired
	public BootstrapData(BookRepository bookRepository, UserRepository userRepository, GenreRepository genreRepository,AuthorRepository authorRepository,WrittenByRepository writtenByRepository) {
		this.bookRepository = bookRepository;
		this.userRepository=userRepository;
		this.genreRepository=genreRepository;
		this.authorRepository=authorRepository;
		this.writtenByRepository=writtenByRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		BookListener book1 = new BookListener();
		book1.setBookISBN_10("655484");
		book1.setTitle("The One from the Stars");
		book1.setRating(9f);
		book1.setPoster("https://images-na.ssl-images-amazon.com/images/I/51sn9qbfCbL._SX311_BO1,204,203,200_.jpg");
		book1.setVolume("2000ccm");
		book1.setAuthor("Keshav Aneel");
		book1.setPublisher("Kathari");
		book1.setGenre("Romance");
		book1.setCost("88.00");
		book1.setPublishedYear("2017");
		book1.setPages("112 pages");
		book1.setDescription(
				"“The cost of not following your heart is spending the rest of your life wishing you had.”\n" + "\n"
						+ "Vishesh is a dreamer, who is driven by his passion for writing and words. But like most Indian middle class families, his parents are impatient to see him settled in a government job. Despite all obstacles, making no complaints, he continues to follow the hard path, holding up the promise he had made to himself, trying to fulfil his father's wishes and failing over and over.\n"
						+ "\n"
						+ "Almost everyone – his parents, friends and the love of his life – leaves his side in the middle of his journey. To worsen things, he is diagnosed with Obsessive Compulsive Disorder, the seriousness of which nobody could decipher before it was too late.");
		book1.setLanguage("English");
		bookRepository.save(book1);
		
		Genre genre1 = new Genre(book1.getGenre());
		genreRepository.save(genre1);
		
		OfType ofType1 = new OfType(book1, genre1);
		ofTypeRepository.save(ofType1);
		
		Author author1 = new Author(book1.getAuthor());
		authorRepository.save(author1);
		
		WrittenBy writtenBy1 = new WrittenBy(book1, author1);
		writtenByRepository.save(writtenBy1);
		
		

		BookListener book2 = new BookListener();
		book2.setBookISBN_10("564438");
		book2.setTitle("ABCs of Horror: 26 Terrifying Tales to Keep You Awake at Night");
		book2.setRating(4f);
		book2.setPoster("https://images-eu.ssl-images-amazon.com/images/I/51cBe-VAiwL.jpg");
		book2.setVolume("4200ccm");
		book2.setAuthor("Anmol Rawat ");
		book2.setPublisher("Eswari");
		book2.setGenre("Horror");
		book2.setCost("99.00");
		book2.setPublishedYear("2015");
		book2.setPages("155 pages");
		book2.setDescription(
				"ABCs of Horror is a collection of ghastly tales that binds different forms of terrors together in a riveting narrative, which feels as real as the entity sitting right behind you when you are home alone, getting freaked out by even a gust of air.");
		book2.setLanguage("English");
		bookRepository.save(book2);
		
		Genre genre2 = new Genre(book2.getGenre());
		genreRepository.save(genre2);
		
		OfType ofType2 = new OfType(book2, genre2);
		ofTypeRepository.save(ofType2);
		
		Author author2 = new Author(book2.getAuthor());
		authorRepository.save(author2);
		
		WrittenBy writtenBy2 = new WrittenBy(book2, author2);
		writtenByRepository.save(writtenBy2);
		
		

		BookListener book3 = new BookListener();
		book3.setTitle("A Corner of a Foreign Field: The Indian History of a British Sport");
		book3.setRating(9f);
		book3.setPoster("https://images-na.ssl-images-amazon.com/images/I/51tWC3RE%2BEL._SX311_BO1,204,203,200_.jpg");
		book3.setVolume("3200ccm");
		book3.setAuthor("Ramachandra Guha");
		book3.setPublisher("Siddharth");
		book3.setGenre("Sports");
		book3.setCost("349.00");
		book3.setPublishedYear("2010");
		book3.setPages("234 pages");
		book3.setDescription(
				"A Corner of a Foreign Field seamlessly interweaves biography with history, the lives of famous or forgotten cricketers with wider processes of social change. C. K. Nayudu and Sachin Tendulkar naturally figure in this book but so, too, in unexpected ways, do B. R. Ambedkar, Mahatma Gandhi and M. A. Jinnah. The Indian careers of those great British cricketers, Lord Harris and D. R. Jardine, provide a window into the operations of Empire. The remarkable life of India’s first great slow bowler, Palwankar Baloo, provides an arresting new perspective on the struggle against caste discrimination. ");
		book3.setLanguage("English");
		bookRepository.save(book3);
		
		Genre genre3 = new Genre(book3.getGenre());
		genreRepository.save(genre3);
		
		OfType ofType3 = new OfType(book3, genre3);
		ofTypeRepository.save(ofType3);
		
		Author author3 = new Author(book3.getAuthor());
		authorRepository.save(author3);
		
		WrittenBy writtenBy3 = new WrittenBy(book3, author3);
		writtenByRepository.save(writtenBy3);
		
		

		BookListener book4 = new BookListener();
		book4.setBookISBN_10("25436");
		book4.setTitle("Disney Princess Comics Treasury");
		book4.setRating(8f);
		book4.setPoster("https://images-na.ssl-images-amazon.com/images/I/51O44lS7lML._SX331_BO1,204,203,200_.jpg");
		book4.setVolume("2000cm");
		book4.setAuthor("Disney");
		book4.setPublisher("Sai Sree");
		book4.setGenre("Comic");
		book4.setCost("1396.00");
		book4.setPublishedYear("2015");
		book4.setPages("345 pages");
		book4.setDescription(
				"For years, Disney's Princesses have charmed audiences the world over in their spellbinding animated films, winning the day with wit and pluck as the power of love keeps the darkness at bay. Return to these thrilling worlds of magic, danger and romance in this timeless graphic novel collection from Disney's own master storybook artists. Every Disney Princess film is retold in this 800-page storytime treasure that Princess fans will cherish for a lifetime. Stories Include: Snow White, Cinderella, Sle");
		book4.setLanguage("English");
		bookRepository.save(book4);
		
		Genre genre4 = new Genre(book4.getGenre());
		genreRepository.save(genre4);
		
		OfType ofType4 = new OfType(book4, genre4);
		ofTypeRepository.save(ofType4);
		
		Author author4 = new Author(book4.getAuthor());
		authorRepository.save(author4);
		
		WrittenBy writtenBy4 = new WrittenBy(book4, author4);
		writtenByRepository.save(writtenBy4);
		
		

		BookListener book5 = new BookListener();
		book5.setBookISBN_10("866754");
		book5.setTitle("Kashi: Secret of the Black Temple (Harappa)");
		book5.setRating(9f);
		book5.setPoster("https://images-na.ssl-images-amazon.com/images/I/51lSaLI9YfL._SX328_BO1,204,203,200_.jpg");
		book5.setVolume("2100ccm");
		book5.setAuthor("Vineet Bajpai");
		book5.setPublisher("Ashutosh");
		book5.setGenre("History");
		book5.setCost("200.00");
		book5.setPublishedYear("2016");
		book5.setPages("213 pages");
		book5.setDescription(
				"Kashi - Secret of the Black Temple is part 3 of the Harappa Series, written by Vineet Bajpai. It is a fiction novel that blends mythology and history with a modern-day thriller.\n"
						+ "\n"
						+ "1699 BCE, the Marshes of Aryavarta – As the titanic waves of pralay swallow city after city, a final battle for the Ark and the Earth begins. A ruthless barbarian-king threatens the very existence of mankind, heralding Kaliyuga - the eon of Kali.");
		book5.setLanguage("English");
		bookRepository.save(book5);
		
		Genre genre5 = new Genre(book5.getGenre());
		genreRepository.save(genre5);
		
		OfType ofType5 = new OfType(book5, genre5);
		ofTypeRepository.save(ofType5);
		
		Author author5 = new Author(book5.getAuthor());
		authorRepository.save(author5);
		
		WrittenBy writtenBy5 = new WrittenBy(book5, author5);
		writtenByRepository.save(writtenBy5);
		
		
		
		BookListener book6 = new BookListener();
		book6.setBookISBN_10("756554");
		book6.setTitle("Wings of Fire: An Autobiography of Abdul Kalam");
		book6.setRating(9f);
		book6.setPoster("https://images-na.ssl-images-amazon.com/images/I/51wbVQTpTgL._SX339_BO1,204,203,200_.jpg");
		book6.setVolume("3600ccm");
		book6.setAuthor("Arun Tiwari and A. P. J. Abdul Kalam");
		book6.setPublisher("Vishnu");
		book6.setGenre("Biography");
		book6.setCost("262.00");
		book6.setPublishedYear("2017");
		book6.setPages("132 pages");
		book6.setDescription(
				"Every common man who by his sheer grit and hard work achieves success should share his story with the rest for they may find inspiration and strength to go on, in his story. The 'Wings of Fire' is one such autobiography by visionary scientist Dr. APJ Abdul Kalam, who from very humble beginnings rose to be the President of India. The book is full of insights, personal moments and life experiences of Dr. Kalam. It gives us an understanding on his journey of success.");
		book6.setLanguage("English");
		bookRepository.save(book6);

		Genre genre6 = new Genre(book6.getGenre());
		genreRepository.save(genre6);
		
		OfType ofType6 = new OfType(book6, genre6);
		ofTypeRepository.save(ofType6);
		
		Author author6 = new Author(book6.getAuthor());
		authorRepository.save(author6);
		
		WrittenBy writtenBy6 = new WrittenBy(book6, author6);
		writtenByRepository.save(writtenBy6);
		
		

		BookListener book7 = new BookListener();
		book7.setBookISBN_10("808767");
		book7.setTitle("The Religion of Man: Rabindranath Tagore");
		book7.setRating(6f);
		book7.setPoster("https://images-na.ssl-images-amazon.com/images/I/51zufCG6CjL._SX310_BO1,204,203,200_.jpg");
		book7.setVolume("3600ccm");
		book7.setAuthor("Rabindranath Tagore");
		book7.setPublisher("Sai");
		book7.setGenre("Religion");
		book7.setCost("112.00");
		book7.setPublishedYear("2015");
		book7.setPages("232 pages");
		book7.setDescription(
				"Originally delivered as the Hibbert Lectures in Oxford in 1930, The Religion of Man (1931) is an extensive and commanding exposition of Rabindranath Tagore's understanding of the meaning and significance of religion in the cultural history of man. Tagore delivered these lectures when he was nearly seventy-years-old, they are born of experience, not theory.");
		book7.setLanguage("English");
		bookRepository.save(book7);
		
		Genre genre7 = new Genre(book7.getGenre());
		genreRepository.save(genre7);
		
		OfType ofType7 = new OfType(book7, genre7);
		ofTypeRepository.save(ofType7);
		
		Author author7 = new Author(book7.getAuthor());
		authorRepository.save(author7);
		
		WrittenBy writtenBy7 = new WrittenBy(book7, author7);
		writtenByRepository.save(writtenBy7);
		
		
		
		BookListener book8 = new BookListener();
		book8.setBookISBN_10("764535");
		book8.setTitle("Harappa - Curse of the Blood River");
		book8.setRating(6f);
		book8.setPoster("https://images-na.ssl-images-amazon.com/images/I/51OV4KduW3L._SX321_BO1,204,203,200_.jpg");
		book8.setVolume("4200ccm");
		book8.setAuthor("Vineet Bajpai");
		book8.setPublisher("Sai Teja");
		book8.setGenre("Thriller");
		book8.setCost("150.00");
		book8.setPublishedYear("2015");
		book8.setPages("211 pages");
		book8.setDescription(
				"2017, Delhi – Vidyut’s dying ancestor summons him to Banaras. The old Brahmin chieftain of the Dev-Raakshasa Matth, or the God-Demon Clan, bears a chilling secret. Their bloodline carries an ancient curse that will plague mankind - towards its own violent extinction. \n"
						+ "\n"
						+ "1700 BCE, Harappa – Harappa is a magnificent city on the banks of the mighty Saraswati river. The darkness of treachery, taantric exorcism and bloodshed unleashes itself on the last devta, paving the way for his devastating revenge…and the horrifying truth behind the fall of the glorious civilisation.");
		book8.setLanguage("English");
		bookRepository.save(book8);
		
		Genre genre8 = new Genre(book8.getGenre());
		genreRepository.save(genre8);
		
		OfType ofType8 = new OfType(book8, genre8);
		ofTypeRepository.save(ofType8);
		
		Author author8 = new Author(book8.getAuthor());
		authorRepository.save(author8);
		
		WrittenBy writtenBy8 = new WrittenBy(book8, author8);
		writtenByRepository.save(writtenBy8);
		
		
		
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
