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
  user = new UserDetails("", "", "", "");

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
}
