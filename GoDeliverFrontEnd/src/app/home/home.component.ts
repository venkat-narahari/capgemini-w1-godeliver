import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { BookService } from "../book.service";

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
  books_notAvailable:boolean=false;
  books_result: any;
  check = "hi";
 

  constructor(private bookService: BookService, private route: Router) {
    this.location = route.url;
  }
  ngOnInit() {
   
  }

  // get search results of movies
  searchBooks() {
    console.log(this.search);
    this.check = "";
    this.bookService.getBookByName(this.search).subscribe(data => {
      if(data!=null){
      this.books_result = data;
      }
      else
      {
        this.books_notAvailable=true;
      }
    });
  }

  backToHome() {
    this.route.navigate([""]);
    location.reload();
  }
}
