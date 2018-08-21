import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { UserDetails } from "./user-details";
@Injectable({
  providedIn: "root"
})
export class UserDetailsService {
  //Making http client obj to perform http operations
  constructor(public http: HttpClient) {}

  //Backend saveuser url
  private post_url = "http://localhost:8080/api/v1/save/user";

  addUser(user: UserDetails): Observable<UserDetails> {
    // To store the details through microservice into the database
    return this.http.post<UserDetails>(this.post_url, user);
  }
}
