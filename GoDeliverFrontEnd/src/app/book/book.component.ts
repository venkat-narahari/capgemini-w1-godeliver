import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from '../book.service';
import { Books } from '../book-details';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  constructor(private router:ActivatedRoute, private bookService:BookService) { }
book:any;
curUser:any;
bookdetails=new Books("","","","","","","","","","","","","");
  ngOnInit() {
    this.router.params.subscribe((params) => {
      const id = params['book.bookISBN'];
      this.bookService.getBook(id).subscribe(data => {
        this.book = data;
        if(localStorage.getItem('userCurrentEmail')!=null) {
          this.curUser=JSON.parse(localStorage.getItem('userCurrentEmail'));
        }
        // console.log(data);
      });
    });
  }
  addBook(book) {
    this.bookdetails.title=book.title;
    this.bookdetails.bookISBN=book.bookISBN;
    this.bookdetails.rating=book.rating;
    this.bookdetails.publishedYear=book.publishedYear;
    this.bookdetails.poster=book.poster;
    this.bookdetails.cost=book.cost;
    this.bookdetails.publisher=book.publisher;
    this.bookdetails.description=book.description;
    this.bookdetails.author=book.author;
    this.bookdetails.volume=book.volume;
    this.bookdetails.genre=book.genre;
    this.bookdetails.pages=book.pages;   
   this.bookdetails.userId=JSON.parse(localStorage.getItem('userCurrentEmail'));
    this.bookService.addToWishlist(this.bookdetails).subscribe(res => console.log('Done'));
}
}
