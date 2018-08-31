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
  ) { }

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

}
