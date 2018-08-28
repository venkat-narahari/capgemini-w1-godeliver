import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute} from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userForm: FormGroup;
  redirectUrl: string;
  error = '';
  constructor(private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router, private authenticationService: AuthenticationService) {
      // this.redirectUrl = this.activatedRoute.snapshot.queryParams['redirectTo'];
    }

    ngOnInit() {
      this.userForm = this.formBuilder.group({
        userEmail: [
          '',
          [Validators.required]
        ],
        userPassword: ['', [Validators.required]]
      });
      this.authenticationService.logout();
    }

    loginSubmit() {

      this.authenticationService.login(this.userEmail.value, this.userPassword.value)
      .subscribe(
          data => {
              this.router.navigate(['']);
              location.reload();
          },
          error => {
              this.error = 'Username or password is incorrect';
          }
        );
    }


    get userEmail() {
      return this.userForm.get('userEmail');
    }

    get userPassword() {
      return this.userForm.get('userPassword');
    }

}