import { Component, OnInit, Input } from "@angular/core";
import { BookService } from "../book.service";
import { Books } from "../book-details";

@Component({
  selector: "app-recommendations",
  templateUrl: "./recommendations.component.html",
  styleUrls: ["./recommendations.component.css"]
})
export class RecommendationsComponent implements OnInit {
  recommended_books;
  topselling;
  email;
  bookdetails = new Books("", "", "", "","", "", "", "", "", "", "", "", "", "");

  constructor(private bookService: BookService) {}

  ngOnInit() {
    if (localStorage.getItem("currentUserEmail") != null) {
      this.email = localStorage.getItem("currentUserEmail");
      this.bookService.getRecommendationList(this.email).subscribe(fullList => {
        this.recommended_books = fullList;
        console.log("inside ngOnInit getRecommendationList");
      });
    }
    this.bookService.getTopSellingList().subscribe(data => {
      console.log("topselling data is 111111111 ", data);
      this.topselling = data;
    });
  }

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
    this.bookService
      .addToWishlist(this.bookdetails)
      .subscribe(res => console.log("Done"));
  }
}
