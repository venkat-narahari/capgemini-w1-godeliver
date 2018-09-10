import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";
import { Observable } from "rxjs";
import { Login } from "../login";

@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient) { }

  login(userEmail: String, userPassword: String): Observable<any> {
    const httpHeaders = new HttpHeaders({
      "Content-Type": "application/json",
      "Cache-Control": "no-cache"
    });
    const options = {
      headers: httpHeaders
    };
    return this.http
      .post<Login>(
        `http://localhost:8080/user-login/api/v1/login`,
        { userEmail, userPassword },
        
        options
      )
      .pipe(
        map(user => {
          // login successful if there's a jwt token in the response
          if (user) {
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem(
              "currentUserToken",
              JSON.stringify(user.token)
            );
            localStorage.setItem(
              "currentUserEmail",
              JSON.stringify(user.email)
            );
            localStorage.setItem(
              "xavdnmzicn",
              JSON.stringify(user.role)
            );
          }
          console.log(userPassword);
          return user;
        })
      );
      
  }
  logout() {
    localStorage.removeItem("currentUserEmail");
    localStorage.removeItem("currentUserToken");
    localStorage.removeItem("xavdnmzicn");
   
  }

  loggedIn() {
    return !!localStorage.getItem('currentUserToken');
  }
}
