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

    this.firebase.getWishlist().subscribe(data => {
      this.books = data;
      this.booksLength = data.length;
    });

    if (localStorage.getItem("currentUserEmail") != null) {
      this.email = JSON.parse(localStorage.getItem("currentUserEmail"));
    }

    this.firebase.getWishlist().subscribe(wishlist => {
      this.wishlist = wishlist;
      this.wishlistLength = wishlist.length;
    });

    this.firebase.getCart().subscribe(cart => {
      this.cart = cart;
      this.cartsLength = cart.length;
    });
  }

  addToCart(book) {
    let bool = true;
    this.item.title = book.title;
    this.item.poster = book.poster;
    this.item.bookISBN_10 = book.bookISBN_10;
    this.item.cost = parseInt(book.cost);
    this.item.genre = book.genre;
    this.item.quantity = 1;
    this.item.volume = parseInt(book.volume);
    this.item.totalPrice = parseInt(book.cost);
    this.item.totalVolume = parseInt(book.volume);
    for (let i = 0; i < this.cartsLength; i++) {
      if (this.item.bookISBN_10 === this.cart[i].bookISBN_10) {
        bool = false;
      }
    }
    if (bool) {
      this.firebase.addItem(this.item);
    }
  }

  addToWishlist(book) {
    let bool = true;
    this.wish.title = book.title;
    this.wish.poster = book.poster;
    this.wish.bookISBN_10 = book.bookISBN_10;
    this.wish.cost = book.cost;
    this.wish.genre = book.genre;
    this.wish.quantity = 1;
    this.wish.author = book.author;
    this.wish.volume = parseInt(book.volume);
    this.wish.publisher = book.publisher;
    for (let i = 0; i < this.wishlistLength; i++) {
      if (this.wish.bookISBN_10 === this.wishlist[i].bookISBN_10) {
        bool = false;
      }
    }
    if (bool) {
      this.firebase.addItemToWishlist(this.wish);
      this.bookService
        .itemToWishlistRecommendation(this.wish)
        .subscribe(data => {
          console.log("done");
        });
    }
  }

  removeFromWishlist(event, book) {
    this.firebase.removeFromWishlist(book);
  }
}
