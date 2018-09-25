import { Wishlist } from "./../firebase.service";
import { Component, OnInit, Input } from "@angular/core";
import { BookService } from "../book.service";
import { FirebaseService } from "../firebase.service";
import { Cart } from "../firebase.service";
import { MatSnackBar } from "@angular/material";
import { SnackbarComponent } from "../snackbar/snackbar.component";

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
  like: boolean = false;
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
  email: any;
  books: any;
  heartImage: any;
  booksLength: any;
  wishlist: any;
  cart: any;
  cartsLength: any;
  wishlistLength: any;
  constructor(
    private bookService: BookService,
    private firebase: FirebaseService,
    public snackBar: MatSnackBar
  ) {
   
  }

  ngOnInit() {
  
    if (localStorage.getItem("currentUserEmail") != null) {
      this.email = JSON.parse(localStorage.getItem("currentUserEmail"));
    }

    setTimeout(() => {
      this.heaImage();
      this.firebase.getWishlist().subscribe(wishlist => {
        this.wishlist = wishlist;
        this.wishlistLength = wishlist.length;
      });
  
      this.firebase.getCart().subscribe(cart => {
        this.cart = cart;
        this.cartsLength = cart.length;
      });
  
      this.firebase.getWishlist().subscribe(data => {
        this.books = data;
        this.booksLength = data.length;
      });
    }, 2000);

  }

  heaImage() {
    //this.heartImage = "../../assets/white.png";
    for (let i = 0; i < this.booksLength; i++) {
      if (this.book.title == this.books[i].title) {
        this.like = true;
      }
    }
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
      if (this.item.bookISBN_10 == this.cart[i].bookISBN_10) {
        bool = false;
      }
    }
    if (bool) {
      this.firebase.addItem(this.item);
    }

    setTimeout(() => {
      this.openSnackBar();
    }, 500);
  }

  openSnackBar() {
    this.snackBar.openFromComponent(SnackbarComponent, {
      duration: 2000
    });
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
        this.firebase.removeFromWishlist(this.wishlist[i]);
      }
    }
    if (bool) {
      this.firebase.addItemToWishlist(this.wish);
      this.bookService
        .itemToWishlistRecommendation(this.wish)
        .subscribe(data => {
        });
    }

    if (this.like == false) {
      this.like = true;
    } else if (this.like == true) {
      this.like = false;
    }
  }
}
