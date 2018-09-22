import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";
import { Observable } from "rxjs";
import { Login } from "../login";
import { getMaxListeners } from "cluster";
import { FirebaseService } from "../firebase.service";
import { getLocaleTimeFormat } from "@angular/common";

@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient, private firebase: FirebaseService) {}

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
        `http://13.232.234.139:9088/user-login/api/v1/login`,
        { userEmail, userPassword },

        options
      )
      .pipe(
        map(user => {
          // login successful if there's a jwt token in the response



          if (user) {

            //delete unregistered user once the user is logged in
            if (localStorage.getItem("uid") != null) {
              this.firebase.deleteUnregUser();
              localStorage.removeItem("uid");
            }

            // store user details and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem(
              "currentUserToken",
              JSON.stringify(user.token)
            );
            localStorage.setItem(
              "currentUserEmail",
              JSON.stringify(user.email)
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
  }

  loggedIn() {
    return !!localStorage.getItem("currentUserToken");
  }
}
