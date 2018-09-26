import { Component, OnInit } from "@angular/core";
import { BookService } from "../book.service";
import { FirebaseService } from "../firebase.service";
import { Cart } from "../firebase.service";
import { Wishlist } from "../firebase.service";
@Component({
  selector: "app-all",
  templateUrl: "./all.component.html",
  styleUrls: ["./all.component.css"]
})
export class AllComponent implements OnInit {
  books: any;
  email: any;
  heartImage: any;
  booksLength: any;
  wishlist: any;
  cart: any;
  cartsLength: any;
  wishlistLength: any;

  item: Cart = {
    bookISBN_10: "",
    title: "",
    cost: 1,
    poster: "",
    genre: "",
    quantity: 1,
    volume: 1,
    totalVolume: 1
  };
  deleteList: any;
  like: boolean = true;

  wish: Wishlist = {
    bookISBN_10: "",
    title: "",
    cost: 1,
    poster: "",
    genre: "",
    quantity: 1,
    author: "",
    publisher: "",
    volume: 1
  };

  //To store current user email for wishlist

  constructor(
    private bookService: BookService,
    private firebase: FirebaseService
  ) {}

  ngOnInit() {
    this.bookService.getAllBooks().subscribe(data => {
      this.books = data;
    });
}
}
