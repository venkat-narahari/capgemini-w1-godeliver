import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Books } from './book-details';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http:HttpClient) { }

  private post_url = 'http://localhost:8087/api/v1/book';
  private get_url = 'http://localhost:8087/api/v1/books';
  private delete_url = 'http://localhost:8087/api/v1/book/delete';
  private recommended_url ='http://localhost:/8092/api/v1/recommendation';
  getWishList(): Observable<Books[]> {
    return this.http.get<Books[]>(this.get_url);
  }

  addMovie(book:Books):Observable<Books>{
    return this.http.post<Books>(this.post_url,book);
  }
  deleteWishList(id){
    return this.http.delete(this.delete_url + '/' +id)
    }

 


}
