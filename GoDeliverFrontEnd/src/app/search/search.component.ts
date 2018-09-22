import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { Input } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

    @Input("books_result") books_result;
    books = JSON.parse(this.books_result);
  constructor(private bookService:BookService) { }

  ngOnInit() {
    console.log(this.books_result);
  }


}
