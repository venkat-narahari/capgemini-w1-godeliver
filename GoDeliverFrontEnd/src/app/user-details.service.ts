import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { UserDetails } from "./user-details";
@Injectable({
  providedIn: "root"
})
export class UserDetailsService {
  //Making http client obj to perform http operations
  constructor(public http: HttpClient) { }

  private getUserDetails_url ="http://13.126.239.33:9088/userprofile/api/v1/user?userEmail=";
  private deleteUserDetails_url = "http://13.126.239.33:9088/userprofile/api/v1/user?userEmail=";
  //Backend saveuser url
  private post_url = "http://13.126.51.125:9072/userprofile/api/v1/user";

  addUser(user: UserDetails): Observable<UserDetails> {

    // To store the details through microservice into the database
    return this.http.post<UserDetails>(this.post_url, user);
  }

  getUser(user) :Observable<UserDetails[]>{

    return this.http.get<UserDetails[]>(this.getUserDetails_url+user);
  }

  deleteUser(useremail) :Observable<UserDetails[]>{

    return this.http.post<UserDetails[]>(this.deleteUserDetails_url,useremail);
  }
}
