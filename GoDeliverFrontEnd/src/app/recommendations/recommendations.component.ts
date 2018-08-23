import { Component, OnInit } from "@angular/core";
import { BookService } from "../book.service";

@Component({
  selector: "app-recommendations",
  templateUrl: "./recommendations.component.html",
  styleUrls: ["./recommendations.component.css"]
})
export class RecommendationsComponent implements OnInit {
  constructor(private bookService: BookService) {}
  recommended_books = [];
  ngOnInit() {
    this.bookService
      .recommendation()
      .subscribe(fullList => (this.recommended_books = fullList));
  }
}
