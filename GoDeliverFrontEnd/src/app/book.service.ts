import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Books } from './book-details';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http:HttpClient) { }

  private wishlist_url = 'http://localhost:8087/api/v1/book';
  private get_url = 'http://localhost:8087/api/v1/books';
  private delete_url = 'http://localhost:8087/api/v1/book/delete';
  private recommended_url ='http://localhost:8092/api/v1/recommendation';
  private getTopSelling_url='http://localhost:8888/api/v1/books';
  private getGenre_url='http://localhost:8075/api/v1/genre';
  private getBookById_url='http://localhost:8070/api/v1/book';
  getWishList(): Observable<Books[]> {
    return this.http.get<Books[]>(this.get_url);
  }

  addToWishlist(book:Books):Observable<Books>{
    return this.http.post<Books>(this.wishlist_url,book);
  }
  deleteWishList(id){
    return this.http.delete(this.delete_url + '/' +id)
    }

 getRecommendationList(id): Observable<Books[]> {
   return this.http.get<Books[]>(this.recommended_url+'/'+id);
 }

 getTopSellingList(): Observable<Books[]> {
   return this.http.get<Books[]>(this.getTopSelling_url);
 }

 getGenreList(): Observable<Books[]> {
  return this.http.get<Books[]>(this.getGenre_url);
}
getBook(id):Observable<Books[]> {
  return this.http.get<Books[]>(this.getBookById_url+'/'+id);
}
}