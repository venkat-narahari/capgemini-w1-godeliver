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
  genres;
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

  constructor(private bookService: BookService) {}
  //Method for displaying the recommendations by considering user email as an input
  ngOnInit() {
    if (localStorage.getItem("currentUserEmail") != null) {
      this.email = JSON.parse(localStorage.getItem("currentUserEmail"));
      this.bookService.getRecommendationList(this.email).subscribe(fullList => {
        this.recommended_books = fullList;
      });
    }
    //getting top sellingbooks
    this.bookService.getTopSellingList().subscribe(data => {
      this.top_selling = data;
    });
    //getting toprated  books
    this.bookService.getTopRated().subscribe(list => {
      console.log("topselling data is 111 ", list);
      this.top_rated = list;
    });

  
  }
}
