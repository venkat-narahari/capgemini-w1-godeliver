import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { BookService } from "../book.service";
import { v4 as uuid } from "uuid";
@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit {
  $: any;
  Name: any;
  genre: any;
  location: any;
  search: any;
  books_result: any;
  check = "hi";
  uniqueId: any;

  constructor(private bookService: BookService, private route: Router) {
    this.location = route.url;
  }
  ngOnInit() {
    if (localStorage.getItem("currentUserEmail") == null) {
      if (localStorage.getItem("uid") != null) {
        console.log("already there");
      } else {
        this.uniqueId = uuid().replace(/-/g, "");
        let key = "uid";
        localStorage.setItem(key, JSON.stringify(this.uniqueId));
        console.log(this.uniqueId);
      }
    }
  }

  // get search results of movies
  searchBooks() {
    console.log(this.search);
    this.check = "";
    this.bookService.getBookByName(this.search).subscribe(data => {
      this.books_result = data;
    });
  }

  backToHome() {
    this.route.navigate([""]);
    location.reload();
  }
}
