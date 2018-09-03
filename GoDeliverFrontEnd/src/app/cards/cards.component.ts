import { Component, OnInit, Input } from "@angular/core";
import { BookService } from "../book.service";
import { FirebaseService } from '../firebase.service';
import { Cart } from "../firebase.service";
@Component({
  selector: "app-cards",
  templateUrl: "./cards.component.html",
  styleUrls: ["./cards.component.css"]
})
export class CardsComponent implements OnInit {
  // To take input data from other pages
  @Input("book")
  book;
  item: Cart = {
    bookISBN_10: '',
    title: '',
    cost:'',
    poster: '',
    genre: '',
    quantity:1
   };


  //To store current user email for wishlist
  email: any;

  constructor(private bookService: BookService, private firebase: FirebaseService) { }

  ngOnInit() {
    if (localStorage.getItem("currentUserEmail") != null) {
      this.email = JSON.parse(localStorage.getItem("currentUserEmail"));
    }


  }

  addToCart(book) {
    this.item.title = book.title;
    this.item.poster = book.poster;
    this.item.bookISBN_10 = book.bookISBN_10;
    this.item.cost = book.cost;
    this.item.genre = book.genre;
    this.item.quantity=1;
    this.firebase.addItem(this.item);
  }

  addToWishlist(book) {
    this.item.title = book.title;
    this.item.poster = book.poster;
    this.item.bookISBN_10 = book.bookISBN_10;
    this.item.cost = book.cost;
    this.item.genre = book.genre;
    this.item.quantity=1;
    this.firebase.addItem(this.item);
  }
}
