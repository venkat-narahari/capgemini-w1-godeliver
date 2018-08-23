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
        email: [
          '',
          [Validators.required]
        ],
        password: ['', [Validators.required]]
      });
      this.authenticationService.logout();
    }

    loginSubmit() {

      this.authenticationService.login(this.email.value, this.password.value)
      .subscribe(
          data => {
              this.router.navigate(['/hi']);
          },
          error => {
              this.error = 'Username or password is incorrect';
          }
        );
    }


    get email() {
      return this.userForm.get('email');
    }

    get password() {
      return this.userForm.get('password');
    }

}