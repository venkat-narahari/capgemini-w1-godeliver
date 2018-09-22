import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Books } from "./book-details";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class BookService {

  private recommended_url = "http://13.232.234.139:9088/recommendation/api/v1/preferences/?userMail=";
  private getTopSelling_url = "http://13.232.234.139:9088/book/api/v1/books";
 //private getTopSelling_url = "http://localhost:8888/api/v1/books";
  private getGenre_url = "http://13.232.234.139:9088/recommendation/api/v1/genre";
  private getBookById_url = "http://13.232.234.139:9088/book/api/v1/book/";
  private getBookByName_url = "http://13.232.234.139:9088/book/api/v1";
  private getTopRated_url = "http://13.232.234.139:9088/recommendation/api/v1/rating";
  private WishlistRecommendation_url ="http://13.232.234.139:9088/recommendation/api/v1/save";
  private getAllBooks_url = "http://13.232.234.139:9088/book/api/v1/books";


  constructor(private http: HttpClient) { }

  getAllBooks() : Observable<Books[]> {
    return this.http.get<Books[]>(this.getAllBooks_url)
  }

  getBookByName(book) {
    return this.http.get(this.getBookByName_url + "/" + book);
  }

  getRecommendationList(id): Observable<Books[]> {
    return this.http.get<Books[]>(this.recommended_url+id);
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
