import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { BookService } from "../book.service";
import { FirebaseService, Cart } from "../firebase.service";
import { Books } from "../book-details";

@Component({
  selector: "app-wishlist",
  templateUrl: "./wishlist.component.html",
  styleUrls: ["./wishlist.component.css"]
})
export class WishlistComponent implements OnInit {
  wishlist: Cart[];
  email: any;
  item: Cart = {
    bookISBN_10: "",
    title: "",
    cost: 1,
    poster: "",
    genre: "",
    quantity: 1
  };

  constructor(
    private firebaseService: FirebaseService,
    private router: ActivatedRoute,
    private bookService: BookService
  ) {
    this.email = JSON.parse(localStorage.getItem("currentUserEmail"));
    this.firebaseService.getWishlist().subscribe(carts => {
      this.wishlist = carts;
    });
  }
  ngOnInit() {}

  addToCart(book) {
    this.item.title = book.title;
    this.item.poster = book.poster;
    this.item.bookISBN_10 = book.bookISBN_10;
    this.item.cost = parseInt(book.cost);
    this.item.genre = book.genre;
    this.item.quantity = 1;
    this.item.totalPrice = parseInt(book.cost);
    this.firebaseService.addItem(this.item);
  }

  removeFromWishlist(event, book) {
    this.firebaseService.removeFromWishlist(book);
  }
}
