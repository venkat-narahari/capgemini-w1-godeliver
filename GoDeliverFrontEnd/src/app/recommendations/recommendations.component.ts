import { Component, OnInit } from "@angular/core";
import { BookService } from "../book.service";

@Component({
  selector: "app-recommendations",
  templateUrl: "./recommendations.component.html",
  styleUrls: ["./recommendations.component.css"]
})
export class RecommendationsComponent implements OnInit {
  constructor(private bookService: BookService) {}
  recommended_books: any;
  email: any;
  ngOnInit() {
    if (localStorage.getItem("currentUserEmail") != null) {
      this.email = localStorage.getItem("currentUserEmail");
      this.bookService
        .getRecommendationList(this.email)
        .subscribe(fullList => (this.recommended_books = fullList));
      console.log("fetching");
    } else {
      this.bookService
        .getTopSellingList()
        .subscribe(fullList => (this.recommended_books = fullList));
    }
  }
}
