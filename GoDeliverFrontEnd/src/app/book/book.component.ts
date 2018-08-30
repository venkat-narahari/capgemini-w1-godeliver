import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { BookService } from "../book.service";
import { Books } from "../book-details";

@Component({
  selector: "app-book",
  templateUrl: "./book.component.html",
  styleUrls: ["./book.component.css"]
})
export class BookComponent implements OnInit {
  //To store book details for wishlist
  book: any;

  //To store currentUser email for wishlist button
  curUser: any;

  constructor(
    private router: ActivatedRoute,
    private bookService: BookService
  ) {}

  // bookdetails=new Books("","","","","","","","","","","","","");

  ngOnInit() {
    this.router.params.subscribe(params => {
      const id = params['bookISBN'];
      this.bookService.getBook(id).subscribe(list => {
        this.book = list
      //  console.log(list);
      });
    });
    if (localStorage.getItem("currentUserEmail") != null) {
      this.curUser = JSON.parse(localStorage.getItem("currentUserEmail"));
    }
  }

  // addBook(book) {
  //   this.bookdetails.title=book.title;
  //   this.bookdetails.bookISBN=book.bookISBN_10;
  //   this.bookdetails.rating=book.rating;
  //   this.bookdetails.publishedYear=book.publishedYear;
  //   this.bookdetails.poster=book.poster;
  //   this.bookdetails.cost=book.cost;
  //   this.bookdetails.publisher=book.publisher;
  //   this.bookdetails.description=book.description;
  //   this.bookdetails.author=book.author;
  //   this.bookdetails.volume=book.volume;
  //   this.bookdetails.genre=book.genre;
  //   this.bookdetails.pages=book.pages;
  //  //this.bookdetails.userId=JSON.parse(localStorage.getItem('userCurrentEmail'));
  //   this.bookService.addToWishlist(this.bookdetails).subscribe(res => console.log('Done'));
  // }
}
