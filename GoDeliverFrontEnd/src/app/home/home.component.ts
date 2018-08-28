import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
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
  constructor(
    private router: ActivatedRoute,
    private bookService: BookService
  ) {}
  ngOnInit() {
    
  }
}
