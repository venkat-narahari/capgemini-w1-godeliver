import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {
  constructor(private _router:Router, private _authService:AuthenticationService) {}

  canActivate() :   boolean {
    if(this._authService.loggedIn()) {
      this._router.navigate([''])
      return false;
    } else {
      return true;
    }
  }
}
