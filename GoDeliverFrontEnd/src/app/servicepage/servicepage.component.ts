import { Component, OnInit } from '@angular/core';
import { FirebaseService } from '../firebase.service';
import { Query } from "../firebase.service";
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-servicepage',
  templateUrl: './servicepage.component.html',
  styleUrls: ['./servicepage.component.css']
})
export class ServicepageComponent implements OnInit {
  //emailPattern 
  emailPattern = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/";

  model :Query= {
    name: "",
    email: "",
    query: ""
  };
  email = new FormControl('', [Validators.required, Validators.email]);
  name=new FormControl('', [Validators.required]);
  query=new FormControl('', [Validators.required]);
  userForm: FormGroup;
  redirectUrl: string;
  error = '';
  constructor(private formBuilder: FormBuilder,
    private firebaseService:FirebaseService,
    private router: Router) {
      // this.redirectUrl = this.activatedRoute.snapshot.queryParams['redirectTo'];
    }

    

    ngOnInit() {
      this.userForm = this.formBuilder.group({
        userEmail: [
          '',
          [Validators.required]
        ],
        userName: ['', [Validators.required]],
        userQuery: ['', [Validators.required]]
      });
    }
// when user clicks login button this method will be called and user input will be evaluated and corresponding error handling
    submit() {
      this.model.email=this.userEmail.value; 
      this.model.name = this.userName.value;
      this.model.query = this.userQuery.value;
      console.log(this.userName.value);
      this.firebaseService.addQuery(this.model);
      
    }
    
//method which gets the useremail and returns to login validation form
    get userEmail() {
      return this.userForm.get('userEmail');
    }
//method which gets the password and returns to login validation form
    get userName() {
      return this.userForm.get('userName');
    }
    get userQuery() {
      return this.userForm.get('userQuery');
    }
    getErrorMessage() {
      return this.email.hasError('required') ? 'You must enter a value' :
          this.email.hasError('email') ? 'Not a valid email' :
              '';
    }
    
}

