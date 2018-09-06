import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Books } from "./book-details";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class BookService {

  private recommended_url = "http://172.23.238.193:8080/recommendation/api/v1/preferences/";
  private getTopSelling_url = "http://localhost:8888/api/v1/books";
  private getGenre_url = "http://172.23.238.193:8080/recommendation/api/v1/genre";
  private getBookById_url = "http://172.23.238.193:8080/book/api/v1/book/";
  private getBookByName_url = "http://172.23.238.193:8080/book/api/v1";
  private getTopRated_url = "http://172.23.238.193:8080/recommendation/api/v1/rating";
  private WishlistRecommendation_url ="http://172.23.238.172:8092/api/v1/save";

  constructor(private http: HttpClient) { }

 
  getBookByName(book) {
    return this.http.get(this.getBookByName_url + "/" + book);
  }

  getRecommendationList(id): Observable<Books[]> {
    return this.http.get<Books[]>(this.recommended_url+'/'+id);
  }

  getTopSellingList(): Observable<Books> {
    return this.http.get<Books>(this.getTopSelling_url);
  }

  getGenreList(): Observable<Books[]> {
    return this.http.get<Books[]>(this.getGenre_url);
  }
  getBook(id): Observable<Books[]> {
    return this.http.get<Books[]>(this.getBookById_url + '/' + id);
  }

  getTopRated(): Observable<Books[]> {
    return this.http.get<Books[]>(this.getTopRated_url);
  }

  itemToWishlistRecommendation(item): Observable<Books[]> {
    return this.http.post<Books[]>(this.WishlistRecommendation_url, item);
  }
}
