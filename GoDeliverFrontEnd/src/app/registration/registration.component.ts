import { Component, OnInit } from "@angular/core";
import { UserDetails } from "../user-details";
import { UserDetailsService } from "../user-details.service";
import { Router } from "@angular/router";
import {
  FormControl,
  FormGroup,
  FormGroupDirective,
  NgForm,
  FormBuilder,
  Validators
} from "@angular/forms";

@Component({
  selector: "app-registration",
  templateUrl: "./registration.component.html",
  styleUrls: ["./registration.component.css"]
})
export class RegistrationComponent implements OnInit {
  email = new FormControl('', [Validators.required, Validators.email]);
  password=new FormControl('', [Validators.required]);
  
  //model to store data and send to backend
  model: any = {};

  userForm: FormGroup;
  //userDetails fields
  user = new UserDetails("", "", "", "", []);

  error = "";

  //creating userDetailService
  constructor(
    private router: Router,
    private userDetailService: UserDetailsService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      userEmail: ["", [Validators.required]],
      userPassword: ["", [Validators.required]],
      userName: ["", [Validators.required]],
      userDOB: ["", [Validators.required]],
     
    });
  }

  //onSubmit is called when user click on registration and data is sent to userDetailService.ts
  loginSubmit() {
    this.user.userName = this.userName.value;
    this.user.userEmail = this.userEmail.value;
    this.user.userDob = this.userDOB.value;
    this.user.userPassword = this.userPassword.value;
    this.user.userPreferences = this.genre.value;
    console.log(this.genre.value);
    this.userDetailService.addUser(this.user).subscribe(
      data => { },
      error => {
        this.error = "Fields are required";
      }
    );
  }


  genre = new FormControl();
  genreList: string[] = [
    "Thriller",
    "Religion",
    "History",
    "Biography",
    "Education",
    "Aptitude",
    "Horror",
    "Comic"
  ];
  //console.log(toppingList[0]);

  emailFormControl = new FormControl("", [
    Validators.required,
    Validators.email
  ]);

  isErrorState(
    control: FormControl | null,
    form: FormGroupDirective | NgForm | null
  ): boolean {
    const isSubmitted = form && form.submitted;
    return !!(
      control &&
      control.invalid &&
      (control.dirty || control.touched || isSubmitted)
    );
  }

  get userName() {
    return this.userForm.get("userName");
  }

  get userPassword() {
    return this.userForm.get("userPassword");
  }
  get userPreferences() {
    return this.userForm.get("genre");
  }

  get userDOB() {
    return this.userForm.get("userDOB");
  }
  get userEmail() {
    return this.userForm.get("userEmail");
  }


  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
        this.email.hasError('email') ? 'Not a valid email' :
            '';
  }
  getErrorMessage2() {
    return this.password.hasError('required') ? 'This field is required' :
      
            '';
  }
  getErrorMessage3() {
    return this.userDOB.hasError('required') ? 'You must choose a date ' :
      
            '';
  }
  getErrorMessage4() {
    return this.userPreferences.hasError('required') ? 'You must choose a genre ' :
      
            '';
  }
  
}
