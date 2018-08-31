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
      userDOB: ["", [Validators.required]]
    });
  }

  //onSubmit is called when user click on registration and data is sent to userDetailService.ts
  loginSubmit() {
    this.user.userName = this.userName.value;
    this.user.userEmail = this.userEmail.value;
    this.user.userDob = this.userDOB.value;
    this.user.userPassword = this.userPassword.value;
    this.user.userPreferences = [];
    this.userDetailService.addUser(this.user).subscribe(
      data => { },
      error => {
        this.error = "User already registered with this email";
      }
    );
    this.router.navigate(["/login"]);
    location.reload();
  }

  //   //selectedPreferenceList is coming from the front end in a list and stored in userPreference in form of array
  //   selectedPreferenceList(list) {
  //     console.log(list);
  //     this.user.userPreferences = list;
  //   }
  // }
  toppings = new FormControl();
  toppingList: string[] = [
    "Thriller",
    "Religion",
    "History",
    "Biography",
    "Education",
    "Aptitude",
    "Horror",
    "Comic"
  ];

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
    return this.userForm.get("userPreferences");
  }

  get userDOB() {
    return this.userForm.get("userDOB");
  }
  get userEmail() {
    return this.userForm.get("userEmail");
  }
}
