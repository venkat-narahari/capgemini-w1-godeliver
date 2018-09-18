import { Component, OnInit } from "@angular/core";
import { BookService } from "../book.service";
@Component({
  selector: "app-toprated",
  templateUrl: "./toprated.component.html",
  styleUrls: ["./toprated.component.css"]
})
export class TopratedComponent implements OnInit {
  books:any;
  constructor(private bookService: BookService) {}

  ngOnInit() {
    this.bookService.getTopRated().subscribe(data => {
      this.books = data;
    });
  }
}
