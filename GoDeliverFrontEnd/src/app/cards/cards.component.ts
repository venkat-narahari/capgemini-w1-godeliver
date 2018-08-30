import { Component, OnInit, Input } from "@angular/core";
import { BookService } from "../book.service";
@Component({
  selector: "app-cards",
  templateUrl: "./cards.component.html",
  styleUrls: ["./cards.component.css"]
})
export class CardsComponent implements OnInit {
  // To take input data from other pages
  @Input("book")
  book;

  //To store current user email for wishlist
  email: any;

  constructor(private bookService: BookService) {}

  ngOnInit() {
    if (localStorage.getItem("currentUserEmail") != null) {
      this.email = JSON.parse(localStorage.getItem("currentUserEmail"));
    }
  }

  addToWishlist(book) {
    this.bookService.addToWishlist(book).subscribe(data => console.log("done"));
  }
}
