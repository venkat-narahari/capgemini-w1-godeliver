import { Component, OnInit } from "@angular/core";
import { BookService } from "../book.service";
@Component({
  selector: "app-all",
  templateUrl: "./all.component.html",
  styleUrls: ["./all.component.css"]
})
export class AllComponent implements OnInit {
 
 books:any;
 
  constructor(private bookService: BookService) {}

  ngOnInit() {
    this.bookService.getAllBooks().subscribe(data => {
      this.books = data;
    });
  }
}
