import { Component, OnInit, Input } from "@angular/core";
import { BookService } from "../book.service";
import { Books } from "../book-details";

@Component({
  selector: "app-recommendations",
  templateUrl: "./recommendations.component.html",
  styleUrls: ["./recommendations.component.css"]
})
export class RecommendationsComponent implements OnInit {
  recommended_books: any;
  top_selling: any;
  top_rated;
  email;
  genres = [];
  bookdetails = new Books(
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    ""
  );

  constructor(private bookService: BookService) { }
  //Method for displaying the recommendations by considering user email as an input
  ngOnInit() {
    if (localStorage.getItem("currentUserEmail") != null) {
      this.email = localStorage.getItem("currentUserEmail");
      this.bookService.getRecommendationList(this.email).subscribe(fullList => {
        this.recommended_books = fullList;
        console.log("inside ngOnInit getRecommendationList");
      });
    }
    //getting top sellingbooks
    this.bookService.getTopSellingList().subscribe(data => {
      console.log("topselling data is 111111111 ", data);
      this.top_selling = data;
    });
    //getting toprated  books
    this.bookService.getTopRated().subscribe(list => {
      console.log("topselling data is 111 ", list);
      this.top_rated = list;
    });
  }
  //This method will be called when user clicks add to wishlist button on the card
  addToWishlist(book) {
    this.bookdetails.title = book.title;
    this.bookdetails.bookISBN_10 = book.bookISBN_10;
    this.bookdetails.rating = book.rating;
    this.bookdetails.publishedYear = book.publishedYear;
    this.bookdetails.poster = book.poster;
    this.bookdetails.cost = book.cost;
    this.bookdetails.publisher = book.publisher;
    this.bookdetails.description = book.description;
    this.bookdetails.author = book.author;
    this.bookdetails.volume = book.volume;
    this.bookdetails.genre = book.genre;
    this.bookdetails.pages = book.pages;
    this.bookdetails.userId = JSON.parse(
      localStorage.getItem("userCurrentEmail")
    );
    //this.bookService
      
  }
}
