import { Component, OnInit } from "@angular/core";
import { UserDetails } from "../user-details";
import { UserDetailsService } from "../user-details.service";

@Component({
  selector: "app-registration",
  templateUrl: "./registration.component.html",
  styleUrls: ["./registration.component.css"]
})
export class RegistrationComponent implements OnInit {
  model: any = {};

  //userDetails fields
  user = new UserDetails("", "", "", "", []);
  preferences=[{id:1,name:"Action"},{id:2,name:"Drama"},{id:3,name:"Fantasy"},
  {id:4,name:"Education"},{id:5,name:"Sci-Fi"},{id:6,name:"Horror"},
  {id:7,name:"Thriller"},{id:8,name:"History"},{id:9,name:"Romance"}];


  //creating userDetailService
  constructor(private userDetailService: UserDetailsService) {}

  ngOnInit() {}

  //onSubmit is called when user click on registration and data is sent to userDetailService.ts
  onSubmit() {
    this.user.userName = this.model.Name;
    this.user.userEmail = this.model.email;
    this.user.userDob = this.model.Date;
    this.user.userPassword = this.model.password;
    this.userDetailService
      .addUser(this.user)
      .subscribe(res => console.log("Done"));
  }

  selectedPreferenceList(list){
    console.log(list)
     //if Pune and Goa is selected
     //output will be ["Pune","Goa"]   if updatedlist event used
     //output will be [{id:1,name:"Pune"},{id:4,name:"Goa"}] if updatedListWithFulldata event used
    }
  
}
