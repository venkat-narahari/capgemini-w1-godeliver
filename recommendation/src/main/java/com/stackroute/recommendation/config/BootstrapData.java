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
	public BootstrapData(BookRepository bookRepository, UserRepository userRepository, GenreRepository genreRepository,OfTypeRepository ofTypeRepository,AuthorRepository authorRepository,WrittenByRepository writtenByRepository) {
		this.bookRepository = bookRepository;
		this.userRepository=userRepository;
		this.genreRepository=genreRepository;
		this.ofTypeRepository=ofTypeRepository;
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
		book1.setVolume("200000ccm");
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
		book2.setVolume("420000ccm");
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
		book3.setVolume("320000ccm");
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
		book4.setVolume("200000cm");
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
		book5.setVolume("210000ccm");
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
		book6.setVolume("360000ccm");
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
		book7.setVolume("360000ccm");
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
		
		
		
		
		
		BookListener book9 = new BookListener();
		book9.setBookISBN_10("123456");
		book9.setTitle("The Rise of Sivagami : Book 1 of Baahubali - Before the Beginning ");
		book9.setRating(7f);
		book9.setPoster("https://images-eu.ssl-images-amazon.com/images/I/518kK%2BoIdIL.jpg");
		book9.setVolume("300000ccm");
		book9.setAuthor("Anand Neelakantan");
		book9.setPublisher("Sumanth");
		book9.setGenre("History");
		book9.setCost("218.00");
		book9.setPublishedYear("2018");
		book9.setPages("494 pages");
		book9.setDescription(
				"Blessed by the sacred Gauriparvat, Mahishmathi is an empire of abundance. The powerful kingdom is flourishing under its king, who enjoys the support and loyalty of his subjects, down to his lowly slaves. But is everything really as it appears, or is the empire hiding its own dirty secret?\n"
						+ "Orphaned at a young age and wrenched away from her foster family, Sivagami is waiting for the day she can avenge the death of her beloved father, cruelly branded a traitor. ");
		book9.setLanguage("English");
		bookRepository.save(book9);
		
		Genre genre9 = new Genre(book9.getGenre());
		genreRepository.save(genre9);
		
		OfType ofType9 = new OfType(book9, genre9);
		ofTypeRepository.save(ofType9);
		
		Author author9 = new Author(book9.getAuthor());
		authorRepository.save(author9);
		
		WrittenBy writtenBy9 = new WrittenBy(book9, author9);
		writtenByRepository.save(writtenBy9);
		
		
		BookListener book10 = new BookListener();
		book10.setBookISBN_10("987654");
		book10.setTitle("The Girl in Room 105");
		book10.setRating(5f);
		book10.setPoster("https://images-eu.ssl-images-amazon.com/images/I/413Ge3dy3qL.jpg");
		book10.setVolume("300000ccm");
		book10.setAuthor("Chetan Bhagat");
		book10.setPublisher("Sai teja");
		book10.setGenre("Romance");
		book10.setCost("159.00");
		book10.setPublishedYear("2017");
		book10.setPages("312 pages");
		book10.setDescription(
				"Hi, I’m Keshav, and my life is screwed. I hate my job and my girlfriend left me. Ah, the beautiful Zara. Zara is from Kashmir. She is a Muslim. And did I tell you my family is a bit, well, traditional? Anyway, leave that.\n"
						+ "\n"
						+ "Zara and I broke up four years ago. She moved on in life. I didn’t. I drank every night to forget her. I called, messaged, and stalked her on social media. She just ignored me.\n"
						+ "\n" + "However, that night, on the eve of h");
		book10.setLanguage("English");
		bookRepository.save(book10);
		
		Genre genre10 = new Genre(book10.getGenre());
		genreRepository.save(genre10);
		
		OfType ofType10 = new OfType(book10, genre10);
		ofTypeRepository.save(ofType10);
		
		Author author10 = new Author(book10.getAuthor());
		authorRepository.save(author10);
		
		WrittenBy writtenBy10 = new WrittenBy(book10, author10);
		writtenByRepository.save(writtenBy10);
		
		BookListener book11 = new BookListener();
		book11.setBookISBN_10("456378");
		book11.setTitle("Thirteen Reasons Why (TV Tie-in)");
		book11.setRating(9f);
		book11.setPoster("https://images-na.ssl-images-amazon.com/images/I/41YOGfaGsEL._SX324_BO1,204,203,200_.jpg");
		book11.setVolume("400000ccm");
		book11.setAuthor("Jay Asher");
		book11.setPublisher("Mass Market Paperback");
		book11.setGenre("Thriller");
		book11.setCost("216.00");
		book11.setPublishedYear("2017");
		book11.setPages("211 pages");
		book11.setDescription(
				"Read this sensational mystery bestseller before you watch the 13-part Netflix series, executive produced by Selena Gomez. This special edition is complete with exclusive behind-the-scenes content including a 16-page full-colour photo insert featuring scenes from the show and interviews with the cast and crew. You can't stop the future. You can't rewind the past. The only way to learn the secret . . . is to press play. ");
		book11.setLanguage("English");
		bookRepository.save(book11);
		
		
		Genre genre11 = new Genre(book11.getGenre());
		genreRepository.save(genre6);
		
		OfType ofType11 = new OfType(book11, genre11);
		ofTypeRepository.save(ofType11);
		
		Author author11 = new Author(book11.getAuthor());
		authorRepository.save(author11);
		
		WrittenBy writtenBy11 = new WrittenBy(book11, author11);
		writtenByRepository.save(writtenBy11);
		
		BookListener book12 = new BookListener();
		book12.setBookISBN_10("438763");
		book12.setTitle("Will You Still Love Me?");
		book12.setRating(8f);
		book12.setPoster("https://images-na.ssl-images-amazon.com/images/I/51%2Bg-0Yqk2L._SX324_BO1,204,203,200_.jpg");
		book12.setVolume("400000ccm");
		book12.setAuthor("RAVINDER SINGH ");
		book12.setPublisher("Leela");
		book12.setGenre("Romance");
		book12.setCost("109.00");
		book12.setPublishedYear("2018");
		book12.setPages("312 pages");
		book12.setDescription(
				"Lavanya Gogoi is from the scenic hills of Shillong while Rajveer Saini belongs to the shahi city of Patiala. Worlds apart from one another, the two land up next to each other on a flight from Mumbai to Chandigarh. It's love at first flight, at least for one of them. For the other . . . well, it's going to take more than a plane ride!");
		book12.setLanguage("English");
		bookRepository.save(book12);
		
		Genre genre12 = new Genre(book12.getGenre());
		genreRepository.save(genre12);
		
		OfType ofType12 = new OfType(book12, genre12);
		ofTypeRepository.save(ofType12);
		
		Author author12 = new Author(book12.getAuthor());
		authorRepository.save(author12);
		
		WrittenBy writtenBy12 = new WrittenBy(book12, author12);
		writtenByRepository.save(writtenBy12);
		
		
		
		BookListener book14 = new BookListener();
		book14.setBookISBN_10("542662");
		book14.setTitle("Murder on the Orient Express (Film Tie-in Edition)");
		book14.setRating(4f);
		book14.setPoster("https://images-na.ssl-images-amazon.com/images/I/51Jyq80W5oL._SX324_BO1,204,203,200_.jpg");
		book14.setVolume("350000ccm");
		book14.setAuthor("Agatha Christie");
		book14.setPublisher("Sumanth");
		book14.setGenre("Thriller");
		book14.setCost("203.00");
		book14.setPublishedYear("2017");
		book14.setPages("311 pages");
		book14.setDescription(
				"Agatha Christie’s most famous murder mystery, re issued with a new cover to tie in with the hugely anticipated 2017 film adaptation.\n"
						+ "Just after midnight, a snowdrift stops the Orient Express in its tracks. The luxurious train is surprisingly full for the time of the year, but by the morning it is one passenger fewer. An American tycoon lies dead in his compartment, stabbed a dozen times, his door locked from the inside");
		book14.setLanguage("English");
		bookRepository.save(book14);
		
		Genre genre14 = new Genre(book14.getGenre());
		genreRepository.save(genre6);
		
		OfType ofType14 = new OfType(book14, genre14);
		ofTypeRepository.save(ofType14);
		
		Author author14 = new Author(book14.getAuthor());
		authorRepository.save(author14);
		
		WrittenBy writtenBy14 = new WrittenBy(book14, author14);
		writtenByRepository.save(writtenBy14);
		
		BookListener book15 = new BookListener();
		book15.setBookISBN_10("647668");
		book15.setTitle("I Too Had a Love Story, Book 1");
		book15.setRating(7f);
		book15.setPoster("https://images-na.ssl-images-amazon.com/images/I/51JdOfIHZ9L._SX324_BO1,204,203,200_.jpg");
		book15.setVolume("320000ccm");
		book15.setAuthor("Ravinder Singh ");
		book15.setPublisher("Leela");
		book15.setGenre("Romance");
		book15.setCost("113.00");
		book15.setPublishedYear("2017");
		book15.setPages("333 pages");
		book15.setDescription("I too had a love story\n"
				+ "Debut novel of well-known Indian English writer Ravinder Singh 'I too had a love story’ was published in 2008 by Shrishti publishers and republished in 2012 by Penguin India. This book has remained in the bestsellers list, even after 6 years of publication. This novel is based on a real life event that happened with Ravinder Singh, the author. This story is about two people Ravin and khushi who are looking forward to get married. Their parents have been pushing them towards getting into an engagement or wedding soon. They sign up on a web matrimonial site and as fate would have it; they meet and fall in love. They decide to get married. Ravin goes to Delhi to meet Khushi and they are very happy. Both the families are very pleased with the relationship and they are about to be engaged. But destiny had other plans. A day before their engagement, something bad happens. Their lives are not the same anymore. Destiny is something we can never change, what we can do is make better choices. After that tragic incident, how do their lives turn out to be? Do they stay together? Are they safe? You will find out all once you finish this captivating tale of love, loss and destiny.");
		book15.setLanguage("English");
		bookRepository.save(book15);
		
		Genre genre15 = new Genre(book15.getGenre());
		genreRepository.save(genre15);
		
		OfType ofType15 = new OfType(book15, genre15);
		ofTypeRepository.save(ofType15);
		
		Author author15 = new Author(book15.getAuthor());
		authorRepository.save(author15);
		
		WrittenBy writtenBy15 = new WrittenBy(book15, author15);
		writtenByRepository.save(writtenBy15);
		
		BookListener book16= new BookListener();
		book16.setBookISBN_10("744437");
		book16.setTitle("Twice the Chill: Two SHORT Horror Stories");
		book16.setRating(5f);
		book11.setPoster("https://images-eu.ssl-images-amazon.com/images/I/516KRbgw55L.jpg");
		book16.setVolume("352000ccm");
		book16.setAuthor("Rachel A Olson");
		book16.setPublisher("Shirisha");
		book16.setGenre("Horror");
		book16.setCost("77.00");
		book16.setPublishedYear("2012");
		book16.setPages("15 pages");
		book16.setDescription(
				"Sometimes it's hard to believe there really are creatures that go bump in the night. Bey had spent his entire life running through the woods and never once saw anything to convince him there were creatures worth fearing. When his littler sister, Chensei, whines about the trip home at night, Bey only mocks her. Until she disappears beyond the treeline.\n"
						+ "\n" + "I, PONTIANAK\n" + "\n"
						+ "Everyone hates and fears monsters, except for when you’re the monster. I never asked for it, and honestly I can’t say I’ve really enjoyed it. But I am what I am, and I can’t change it. Hell, I can’t even control it. My name used to be Anastasia, and I am a Pontianak.");
		book16.setLanguage("English");
		bookRepository.save(book16);
		
		Genre genre16 = new Genre(book16.getGenre());
		genreRepository.save(genre6);
		
		OfType ofType16 = new OfType(book16, genre16);
		ofTypeRepository.save(ofType16);
		
		Author author16 = new Author(book16.getAuthor());
		authorRepository.save(author16);
		
		WrittenBy writtenBy16 = new WrittenBy(book16, author16);
		writtenByRepository.save(writtenBy16);

		
		BookListener book17 = new BookListener();
		book17.setBookISBN_10("89665");
		book17.setTitle("The Amityville Horror");
		book17.setRating(9f);
		book17.setPoster("https://images-na.ssl-images-amazon.com/images/I/51JGWSQoIWL._SX303_BO1,204,203,200_.jpg");
		book17.setVolume("333000ccm");
		book17.setAuthor("Jay Anson");
		book17.setPublisher("Sai Teja");
		book17.setGenre("Horror");
		book17.setCost("471.00");
		book17.setPublishedYear("2013");
		book17.setPages("356 pages");
		book17.setDescription(
				"Provides a chilling account account of the four weeks of terror experienced by an Amityville, Long Island, family after moving into a house in which a particularly gruesome mass murder had once been committed. Reissue.");
		book17.setLanguage("English");
		bookRepository.save(book17);
		
		Genre genre17 = new Genre(book17.getGenre());
		genreRepository.save(genre6);
		
		OfType ofType17 = new OfType(book17, genre17);
		ofTypeRepository.save(ofType17);
		
		Author author17 = new Author(book17.getAuthor());
		authorRepository.save(author17);
		
		WrittenBy writtenBy17= new WrittenBy(book17, author17);
		writtenByRepository.save(writtenBy17);
		
		BookListener book18 = new BookListener();
		book18.setBookISBN_10("965439");
		book18.setTitle("The Black Book of Horror: Bk. 1");
		book18.setRating(7f);
		book18.setPoster("https://images-na.ssl-images-amazon.com/images/I/51O6XZ8YnyL._SX311_BO1,204,203,200_.jpg");
		book18.setVolume("352000ccm");
		book18.setAuthor("Charles Black");
		book18.setPublisher("Sumanth");
		book18.setGenre("Horror");
		book18.setCost("1179.00");
		book18.setPublishedYear("2014");
		book18.setPages("321 pages");
		book18.setDescription(
			"The Black Book of Horror contains 18 excursions into the realms of terror. Ranging from the supernatural to the macabre, the stories selected for this anthology feature black magic, the dead, monstrous beasts, and things from beyond. There are tales that witness madness, and the evil that man does. Contents: CROWS - Frank Nicholas REGINA vs. ZOSKIA - Mark Samuels THE OLDER MAN - Gary Fry POWER - Steve Goodwin CORDS - Roger B. Pile THE SOUND OF MUZAK - Sean Parker SHAPED LIKE A SNAKE - D. F. Lewis ONLY IN YOUR DREAMS - David A. Sutton THE WOLF AT JESSIE'S DOOR - Paul Finch SIZE MATTERS - John L. Probert SPARE RIB: A ROMANCE - John Kenneth Dunham FAMILY FISHING - Gary McMahon SUBTLE INVASION - David Conyers A PIE WITH THICK GRAVY - D. F. Lewis LOCK-IN - David A. Riley LAST CHRISTMAS (I GAVE YOU MY LIFE) - Franklin Marsh \"SHALT THOU KNOW MY NAME?\" - Daniel McGachey TO SUMMON A FLESH EATING DEMON - Charles Black");
		book18.setLanguage("English");
		bookRepository.save(book18);
		
		Genre genre18 = new Genre(book18.getGenre());
		genreRepository.save(genre18);
		
		OfType ofType18 = new OfType(book18, genre18);
		ofTypeRepository.save(ofType18);
		
		Author author18 = new Author(book18.getAuthor());
		authorRepository.save(author18);
		
		WrittenBy writtenBy18= new WrittenBy(book18, author18);
		writtenByRepository.save(writtenBy18);
		
		
		BookListener book19 = new BookListener();
		book19.setBookISBN_10("897433");
		book19.setTitle("A Century is not Enough: My Roller-coaster Ride to Success");
		book19.setRating(8f);
		book19.setPoster("https://images-na.ssl-images-amazon.com/images/I/51p3ve79yRL._SX315_BO1,204,203,200_.jpg");
		book19.setVolume("320000ccm");
		book19.setAuthor("Sourav Ganguly");
		book19.setPublisher("Mukul");
		book19.setGenre("Sports");
		book19.setCost("391.00");
		book19.setPublishedYear("2016");
		book19.setPages("128 pages");
		book19.setDescription(
				"Sourav Ganguly's life has been full of highs and lows. Arguably India's greatest cricket captain, he gave confidence to the team, re-energized them and took India, for the first time, to spectacular overseas victories. But Ganguly's story also came with great challenges - from his early days where he had to wait four long years before being included in the team to the ugly battle with the Australian coach Greg Chappell. He fo");
		book19.setLanguage("English");
		bookRepository.save(book19);
		
		Genre genre19 = new Genre(book19.getGenre());
		genreRepository.save(genre19);
		
		OfType ofType19 = new OfType(book19, genre19);
		ofTypeRepository.save(ofType19);
		
		Author author19 = new Author(book19.getAuthor());
		authorRepository.save(author19);
		
		WrittenBy writtenBy19= new WrittenBy(book19, author19);
		writtenByRepository.save(writtenBy19);
		
		
		BookListener book20 = new BookListener();
		book20.setBookISBN_10("6544332");
		book20.setTitle("Barefoot to Boots: The Many Lives of Indian Football");
		book20.setRating(5f);
		book20.setPoster("https://images-na.ssl-images-amazon.com/images/I/51kbZjIpmFL._SX310_BO1,204,203,200_.jpg");
		book20.setVolume("400000ccm");
		book20.setAuthor("Novy Kapadia");
		book20.setPublisher("Venkat");
		book20.setGenre("Sports");
		book20.setCost("191.00");
		book20.setPublishedYear("2017");
		book20.setPages("321pages");
		book20.setDescription("A thrilling account of the incredible journey of Indian football\n" + "\n"
				+ "Few football-crazy fans today may be aware that India was once called the 'Brazil of Asia'. Or that the competition between East Bengal and Mohun Bagan is ranked among the top fifty club rivalries in the world. Brilliant players, world-class coaches and passionate fans-including political leaders and film celebrities-once combined to make India a football-worshipping nation. A major source of sports entertainment in India, football ");
		book20.setLanguage("English");
		bookRepository.save(book20);
		
		Genre genre20 = new Genre(book20.getGenre());
		genreRepository.save(genre20);
		
		OfType ofType20= new OfType(book20, genre20);
		ofTypeRepository.save(ofType20);
		
		Author author20 = new Author(book20.getAuthor());
		authorRepository.save(author20);
		
		WrittenBy writtenBy20= new WrittenBy(book20, author20);
		writtenByRepository.save(writtenBy20);
		
		
		
		BookListener book21 = new BookListener();
		book21.setBookISBN_10("76574");
		book21.setTitle("History of Modern India");
		book21.setRating(3f);
		book21.setPoster("https://images-na.ssl-images-amazon.com/images/I/517IXBU1KVL._SX321_BO1,204,203,200_.jpg");
		book21.setVolume("350000ccm");
		book21.setAuthor("Bipan Chandra");
		book21.setPublisher("Hari Krishna");
		book21.setGenre("History");
		book21.setCost("295.00");
		book21.setPublishedYear("2016");
		book21.setPages("122 pages");
		book21.setDescription(
				"History of Modern India is one of the famous books of Bipin Chandra. This book was published by Orient Blackswan in 2009. This book is a journey mapping the path of colonial India in from the eighteenth century to the twentieth century. It traces the timeline of British rule which majorly coincides with the modern Indian history. However, this is not a political narrative of the British rule, the freedom struggle and the Independence. Rather, this book is a social understanding of the factors that allowed British rulers to rule over India for such a long span of time. It tries to understand what the social effects of the rule were. How the small villages and corners of India was affected by a power that was controlled from Europe. What did this rule bring for the Indians to make their lives better? How did the colonized people perceive");
		book21.setLanguage("English");
		bookRepository.save(book21);
		
		Genre genre21 = new Genre(book21.getGenre());
		genreRepository.save(genre21);
		
		OfType ofType21 = new OfType(book21, genre21);
		ofTypeRepository.save(ofType21);
		
		Author author21 = new Author(book21.getAuthor());
		authorRepository.save(author21);
		
		WrittenBy writtenBy21= new WrittenBy(book21, author21);
		writtenByRepository.save(writtenBy21);
		
		
		
		BookListener book22 = new BookListener();
		book22.setBookISBN_10("877665");
		book22.setTitle("An Era of Darkness: The British Empire in India");
		book22.setRating(6f);
		book22.setPoster("https://images-na.ssl-images-amazon.com/images/I/518uWCOXgxL._SX334_BO1,204,203,200_.jpg");
		book22.setVolume("310000ccm");
		book22.setAuthor(" Shashi Tharoor");
		book22.setPublisher("Shivam");
		book22.setGenre("History");
		book22.setCost("459.00");
		book22.setPublishedYear("2017");
		book22.setPages("354 pages");
		book22.setDescription(
				"In 1930, the American historian and philosopher Will Durant wrote that Britain’s ‘conscious and deliberate bleeding of India… [was the] greatest crime in all history’. He was not the only one to denounce the rapacity and cruelty of British rule, and his assessment was not exaggerated. Almost thirty-five million Indians died because of acts of commission and omission by the British—in famines, epidemics, communal riots and wholesale slaughter like the reprisal killings after the 1857 War of Independence ");
		book22.setLanguage("English");
		bookRepository.save(book22);
		
		Genre genre22 = new Genre(book22.getGenre());
		genreRepository.save(genre22);
		
		OfType ofType22 = new OfType(book22, genre22);
		ofTypeRepository.save(ofType22);
		
		Author author22 = new Author(book22.getAuthor());
		authorRepository.save(author22);
		
		WrittenBy writtenBy22= new WrittenBy(book22, author22);
		writtenByRepository.save(writtenBy22);
		
		
		
		BookListener book23 = new BookListener();
		book23.setBookISBN_10("646477");
		book23.setTitle("Beyond Religion: Ethics for a Whole World");
		book23.setRating(7f);
		book23.setPoster(
				"data:image/webp;base64,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");
		book23.setVolume("400000ccm");
		book23.setAuthor("Dalai Lama");
		book23.setPublisher("Babu Ahmed");
		book23.setGenre("Religion");
		book23.setCost("239.00");
		book23.setPublishedYear("2016");
		book23.setPages("231 pages");
		book23.setDescription(
				"In his bestselling Ethics for a New Millennium, His Holiness the Dalai Lama first proposed an approach to ethics based on universal rather than religious principles. Now, in Beyond Religion: Ethics for a Whole World, the Dalai Lama, at his most compassionate and outspoken, elaborates and deepens his vision for the nonreligious way. Transcending the mere 'religion wars', he outlines a system of ethics for our shared world, one that gives full respect to religion. With the highest level of spiritual and intellectual authority, the Dalai Lama makes a stirring appeal for what he calls a 'third way', a path to an ethical and happy life and to a global human community based on understanding and mutual respect. Beyond Religion is an essential statement from the Dalai Lama, a blueprint for all those who may choose not to identify with a religious tradition, yet still yearn for a life of spiritual fulfilment as they work for a better world.");
		book23.setLanguage("English");
		bookRepository.save(book23);
		
		
		Genre genre23 = new Genre(book23.getGenre());
		genreRepository.save(genre23);
		
		OfType ofType23 = new OfType(book23, genre23);
		ofTypeRepository.save(ofType23);
		
		Author author23 = new Author(book23.getAuthor());
		authorRepository.save(author23);
		
		WrittenBy writtenBy23= new WrittenBy(book23, author23);
		writtenByRepository.save(writtenBy23);
		
		BookListener book24 = new BookListener();
		book24.setBookISBN_10("32217");
		book24.setTitle("The Religions Book (Big Ideas)");
		book24.setRating(5f);
		book24.setPoster(
				"data:image/webp;base64,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");
		book24.setVolume("300000ccm");
		book24.setAuthor("D.K.");
		book24.setPublisher("Ximantha");
		book24.setGenre("Religion");
		book24.setCost("699.00");
		book24.setPublishedYear("2017");
		book24.setPages("232 pages");
		book24.setDescription("An innovative and accessible guide to the world's religions\n" + "\n"
				+ "The Religions Book clearly explains the key concepts behind the earliest religious beliefs right up to the world's newest faiths, getting to the heart of what it means to believe. Principles of the five main world religions - Hinduism, Buddhism, Judaism, Christianity and Islam - as well as ancient and primal belief systems are explored in this informative tome.\n"
				+ "\n" + "");
		book24.setLanguage("English");
		bookRepository.save(book24);
		
		Genre genre24 = new Genre(book24.getGenre());
		genreRepository.save(genre24);
		
		OfType ofType24 = new OfType(book24, genre24);
		ofTypeRepository.save(ofType24);
		
		Author author24 = new Author(book24.getAuthor());
		authorRepository.save(author24);
		
		WrittenBy writtenBy24 = new WrittenBy(book24, author24);
		writtenByRepository.save(writtenBy24);

		BookListener book25 = new BookListener();
		book25.setBookISBN_10("653224");
		book25.setTitle("Code Name God: The Spiritual Odyssey of a Man of Science");
		book25.setRating(9f);
		book25.setPoster(
				"data:image/webp;base64,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");
		book25.setVolume("320000ccm");
		book25.setAuthor("Mani Bhaumik");
		book25.setPublisher("Satwik");
		book25.setGenre("Religion");
		book25.setCost("233.00");
		book25.setPublishedYear("2014");
		book25.setPages("311 pages");
		book25.setDescription(
				"The split between man and maker has long been widening, and many acknowledge that the wedge is science. Given the subtitle of ‘The Spiritual Odyssey of a Man of Science’, and very rightly so called, Code Name God strikes a perfect balance between spirituality and science to explain the deep concepts that seem to be giving shape and meaning to our lives.");
		book25.setLanguage("English");
		bookRepository.save(book25);
		
		Genre genre25 = new Genre(book25.getGenre());
		genreRepository.save(genre25);
		
		OfType ofType25 = new OfType(book25, genre25);
		ofTypeRepository.save(ofType25);
		
		Author author25 = new Author(book25.getAuthor());
		authorRepository.save(author25);
		
		WrittenBy writtenBy25 = new WrittenBy(book25, author25);
		writtenByRepository.save(writtenBy25);
		
		BookListener book26 = new BookListener();
		book26.setBookISBN_10("86659");
		book26.setTitle("The Best of Panchatantra");
		book26.setRating(5f);
		book26.setPoster("https://images-na.ssl-images-amazon.com/images/I/51v0mYmjAAL._SX333_BO1,204,203,200_.jpg");
		book26.setVolume("340000ccm");
		book26.setAuthor("Rungeen Singh");
		book26.setPublisher("Shanker");
		book26.setGenre("Comic");
		book26.setCost("49.00");
		book26.setPublishedYear("2013");
		book26.setPages("122 pages");
		book26.setDescription(
				"The Best of Panchatantra, compiled by well-known author Mrs Rungeen Singh, has outstanding stories from Panchatantra, an ancient inter-related collection of animal fables in verse and prose. The stories in the books are very useful for the parents to help them guide their children towards the values of human life. The stories have been compiled in easy-to-understand and simple language so that even mediocre persons may comprehend them effortlessly.");
		book26.setLanguage("English");
		bookRepository.save(book26);
		
		Genre genre26 = new Genre(book26.getGenre());
		genreRepository.save(genre26);
		
		OfType ofType26 = new OfType(book26, genre26);
		ofTypeRepository.save(ofType26);
		
		Author author26 = new Author(book26.getAuthor());
		authorRepository.save(author26);
		
		WrittenBy writtenBy26= new WrittenBy(book26, author26);
		writtenByRepository.save(writtenBy26);
		
		BookListener book28 = new BookListener();
		book28.setBookISBN_10("789890");
		book28.setTitle("The Dhoni Touch: Unravelling the Enigma That Is Mahendra Singh Dhoni");
		book28.setRating(3f);
		book28.setPoster("https://images-na.ssl-images-amazon.com/images/I/51NM7ftGisL._SX320_BO1,204,203,200_.jpg");
		book28.setVolume("350000ccm");
		book28.setAuthor("Bharat Sundaresan");
		book28.setPublisher("Jyothi");
		book28.setGenre("Biography");
		book28.setCost("150.00");
		book28.setPublishedYear("2018");
		book28.setPages("232 pages");
		book28.setDescription(
				"For over a decade, Mahendra Singh Dhoni has captivated the world of cricket and over a billion Indians with his incredible ingenuity as captain, wicketkeeper and batsman. Bharat Sundaresan tracks down the cricketer's closest friends in Ranchi and artfully presents the different shades of Dhoni-the Ranchi boy, the fauji, the diplomat, Chennai's beloved Thala, the wicketkeeping Pythagoras-and lays bare the man underneath. He discovers a certain je ne sais quoi about the man who has a magical ability to transform and elevate everything which comes into his orbit-the Dhoni Touch.");
		book28.setLanguage("English");
		bookRepository.save(book28);
		
		Genre genre28 = new Genre(book28.getGenre());
		genreRepository.save(genre28);
		
		OfType ofType28 = new OfType(book28, genre28);
		ofTypeRepository.save(ofType28);
		
		Author author28 = new Author(book28.getAuthor());
		authorRepository.save(author28);
		
		WrittenBy writtenBy28= new WrittenBy(book28, author28);
		writtenByRepository.save(writtenBy28);
		
		BookListener book29 = new BookListener();
		book29.setBookISBN_10("732465");
		book29.setTitle("Playing It My Way");
		book29.setRating(2f);
		book29.setPoster(
				"data:image/webp;base64,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");
		book29.setVolume("380000ccm");
		book29.setAuthor("Sachin Tendulkar");
		book29.setPublisher("Amarnath");
		book29.setGenre("Biography");
		book29.setCost("323.00");
		book29.setPublishedYear("2018");
		book29.setPages("243 pages");
		book29.setDescription(
				"This is the autobiography of legend and the renowned personality in cricket, Sachin Tendulkar. Sachin Tendulkar played for good 24 years and retired in 2013. In this book Sachin talks about his journey from his first Test match at the age of 16 to his 100th international century and the final farewell. He was awarded with India's highest civilian honor, Bharat Ratna Award, on the day of his retirement.");
		book29.setLanguage("English");
		bookRepository.save(book29);
		
		Genre genre29 = new Genre(book29.getGenre());
		genreRepository.save(genre29);
		
		OfType ofType29= new OfType(book29, genre29);
		ofTypeRepository.save(ofType29);
		
		Author author29 = new Author(book29.getAuthor());
		authorRepository.save(author29);
		
		WrittenBy writtenBy29= new WrittenBy(book29, author29);
		writtenByRepository.save(writtenBy29);
		
		BookListener book30 = new BookListener();
		book30.setBookISBN_10("978665");
		book30.setTitle("Rafa: My Story");
		book30.setRating(6f);
		book30.setPoster("https://images-na.ssl-images-amazon.com/images/I/51io57Ii5kL._SX316_BO1,204,203,200_.jpg");
		book30.setVolume("380000ccm");
		book30.setAuthor("Rafael Nadal and John Carlin");
		book30.setPublisher("Haritha");
		book30.setGenre("Sports");
		book30.setCost("459.00");
		book30.setPublishedYear("2018");
		book30.setPages("356 pages");
		book30.setDescription(
				"Rafa: My Story written by Rafael Nadal and John Carlin is an astonishing account through the eyes of the elite tennis player. This autobiography, serves up a smashing report of his life - on and off the court. It provides revealing insights about a supreme athlete known for his reticence and modesty.\n"
						+ "\n"
						+ "Born in Mallorca, a tiny island off the coast of Spain, is where he returns to rejuvenate his mind and body after gruelling tennis seasons the");
		book30.setLanguage("English");
		bookRepository.save(book30);
		
		Genre genre30 = new Genre(book30.getGenre());
		genreRepository.save(genre30);
		
		OfType ofType30 = new OfType(book30, genre30);
		ofTypeRepository.save(ofType30);
		
		Author author30 = new Author(book30.getAuthor());
		authorRepository.save(author30);
		
		WrittenBy writtenBy30= new WrittenBy(book30, author30);
		writtenByRepository.save(writtenBy30);

		
		BookListener book31 = new BookListener();
		book31.setBookISBN_10("986086");
		book31.setTitle("Mother Teresa: The Untold Story");
		book31.setRating(6f);
		book31.setPoster("https://images-na.ssl-images-amazon.com/images/I/41d0i4K%2B6zL._SX321_BO1,204,203,200_.jpg");
		book31.setVolume("320000ccm");
		book31.setAuthor("Aroup Chatterjee");
		book31.setPublisher("Likitha");
		book31.setGenre("Biography");
		book31.setCost("199.00");
		book31.setPublishedYear("2016");
		book31.setPages("243 pages");
		book31.setDescription(
				"The popular tendency is to deify myths, gurus, and personalities without investigating the claims thoroughly. Mother Teresa is one such name. Does Mother Teresa deserve her reputation as the most charitable person who ever lived? This book makes for a gripping but disconcerting read.\n"
						+ "‘Brilliant, heroic, devastating’");
		book31.setLanguage("English");
		bookRepository.save(book31);
		
		
		Genre genre31 = new Genre(book31.getGenre());
		genreRepository.save(genre31);
		
		OfType ofType31 = new OfType(book31, genre31);
		ofTypeRepository.save(ofType31);
		
		Author author31 = new Author(book31.getAuthor());
		authorRepository.save(author31);
		
		WrittenBy writtenBy31= new WrittenBy(book31, author31);
		writtenByRepository.save(writtenBy31);
		
		

		
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
