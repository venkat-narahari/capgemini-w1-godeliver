import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute} from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import {FormControl, Validators} from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
//Class for evaluating user Credentials
export class LoginComponent implements OnInit {
  email = new FormControl('', [Validators.required, Validators.email]);
  password=new FormControl('', [Validators.required]);
  userDOB=new FormControl('', [Validators.required]);
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
// when user clicks login button this method will be called and user input will be evaluated and corresponding error handling
    loginSubmit() {

      this.authenticationService.login(this.userEmail.value, this.userPassword.value)
      .subscribe(
          data => {
              this.router.navigate(['']);
              location.reload();
          
            }
            
          // error => {
          //     this.error = 'Username or password is required'; 
          // },
        );
    }
    
//method which gets the useremail and returns to login validation form
    get userEmail() {
      return this.userForm.get('userEmail');
    }
//method which gets the password and returns to login validation form
    get userPassword() {
      return this.userForm.get('userPassword');
    }
    getErrorMessage() {
      return this.email.hasError('required') ? 'You must enter a value' :
          this.email.hasError('email') ? 'Not a valid email' :
              '';
    }
    getErrorMessage2() {
      return this.password.hasError('required') ? 'You must enter a value' :
        
              '';
    }
    
}

