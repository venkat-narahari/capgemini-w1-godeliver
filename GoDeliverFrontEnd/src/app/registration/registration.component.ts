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
  prefList: Array<string> = []; 
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
registerSubmit() {
    this.user.userName = this.userName.value;
    this.user.userEmail = this.userEmail.value;
    this.user.userDob = this.userDOB.value;
    this.user.userPassword = this.userPassword.value;
    
  }
preferencesSubmit(){
  this.user.userPreferences = this.prefList;
    this.userDetailService.addUser(this.user).subscribe(
      data => { },
      error => {
        this.error = "Fields are required";
      }
    );
    this.router.navigate(['/login']);

}

  genre = new FormControl();
  genreList = [
    {id:1, name:"Thriller", card:"card1", click:"card1", img:"https://image.ibb.co/g2POcz/fffff.jpg"},
    {id:2,name:"Fiction", card:"card2", click:"card2", img:"https://image.ibb.co/dAAMEe/rooot123.jpg)" },
    {id:3,name:"History", card:"card3", click:"card3", img:"https://image.ibb.co/jyc1fK/meghs.jpg"},
    {id:4,name:"Biography", card:"card4", click:"card4", img:"https://image.ibb.co/mEFJSz/6666.jpg"},
    {id:5,name:"Sports", card:"card5", click:"card5", img:"https://image.ibb.co/d0Cq7z/Virat_Kohli.jpg"},
    {id:6,name:"Romance", card:"card6", click:"card6", img:"https://image.ibb.co/jBP6xz/wwww_160x100.jpg"},
    {id:7,name:"Horror", card:"card7", click:"card7", img:"https://image.ibb.co/iPbicz/thenunnnn.jpg"},
    {id:8,name:"Comic", card:"card8", click:"card8", img:"https://preview.ibb.co/fGjpZe/d3255.jpg"}
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
    return this.prefList;
   
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
  // // getErrorMessage4() {
  // //   return this.userPreferences.hasError('required') ? 'You must choose a genre ' :
      
  // //           '';
  // }
  onclick(id,card,genres){
    
  
    if(document.getElementById(card).style.opacity == "0.3"){
      document.getElementById(card).style.opacity= "1";
      this.prefList.pop();
    }
    else{
      document.getElementById(card).style.opacity="0.3";
      this.prefList.push(genres);
}
}
}