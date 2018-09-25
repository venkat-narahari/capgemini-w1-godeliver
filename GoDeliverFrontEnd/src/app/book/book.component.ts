import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { BookService } from "../book.service";
import { FirebaseService, Cart } from "../firebase.service";
import { MatSnackBar } from "@angular/material";
import { SnackbarComponent } from "../snackbar/snackbar.component";

@Component({
  selector: "app-book",
  templateUrl: "./book.component.html",
  styleUrls: ["./book.component.css"]
})
export class BookComponent implements OnInit {
  //To store book details for wishlist
  book: any;
  cart: any;
  cartsLength: any;
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
  //To store currentUser email for wishlist button
  curUser: any;

  constructor(
    private router: ActivatedRoute,
    private bookService: BookService,
    private firebaseService: FirebaseService,
    private route: Router,
    public snackBar: MatSnackBar
  ) {
    this.firebaseService.getCart().subscribe(cart => {
      this.cart = cart;
      this.cartsLength = cart.length;
    });
  }

  // bookdetails=new Books("","","","","","","","","","","","","");

  ngOnInit() {
    this.router.params.subscribe(params => {
      const id = params["bookISBN"];
      this.bookService.getBook(id).subscribe(list => {
        this.book = list;
        //  console.log(list);
      });
    });
    if (localStorage.getItem("currentUserEmail") != null) {
      this.curUser = JSON.parse(localStorage.getItem("currentUserEmail"));
    }
  }

  billing() {
    let bool = true;
    this.item.title = this.book.title;
    this.item.poster = this.book.poster;
    this.item.bookISBN_10 = this.book.bookISBN_10;
    this.item.cost = parseInt(this.book.cost);
    this.item.genre = this.book.genre;
    this.item.quantity = 1;
    this.item.volume = parseInt(this.book.volume);
    this.item.totalPrice = parseInt(this.book.cost);
    this.item.totalVolume = parseInt(this.book.volume);
    for (let i = 0; i < this.cartsLength; i++) {
      if (this.item.bookISBN_10 === this.cart[i].bookISBN_10) {
        bool = false;
      }
    }
    if (bool) {
      this.firebaseService.addItem(this.item);
    }
    setTimeout(() => {
      this.routeToBilling();
    }, 1000);
  }

  routeToBilling() {
    this.route.navigate(["/billing"]);
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
      this.firebaseService.addItem(this.item);
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
}
