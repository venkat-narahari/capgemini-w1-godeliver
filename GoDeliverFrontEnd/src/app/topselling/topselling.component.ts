import { Component, OnInit } from '@angular/core';
import { BookService } from "../book.service";
@Component({
  selector: 'app-topselling',
  templateUrl: './topselling.component.html',
  styleUrls: ['./topselling.component.css']
})
export class TopsellingComponent implements OnInit {

  books:any;
  constructor(private bookService:BookService) { }

  ngOnInit() {
    this.bookService.getTopSellingList().subscribe(data => {
      this.books=data;
    })
  }

}
