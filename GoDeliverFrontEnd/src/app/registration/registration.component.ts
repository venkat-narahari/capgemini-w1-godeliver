import { Component, OnInit } from "@angular/core";
import { UserDetails } from "../user-details";
import { UserDetailsService } from "../user-details.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: "app-registration",
  templateUrl: "./registration.component.html",
  styleUrls: ["./registration.component.css"]
})
export class RegistrationComponent implements OnInit {
  //model to store data and send to backend
  model: any = {};

  //userDetails fields
  user = new UserDetails("", "", "", "", []);

  //emailPattern
  emailPattern =
    "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
  //master preferences
  preferences = [
    { id: 1, name: "Trave," },
    { id: 2, name: "Horror" },
    { id: 3, name: "Programming" },
    { id: 4, name: "Philosophy" },
    { id: 5, name: "Thriller" },
    { id: 6, name: "Sci-Fi" },
    { id: 7, name: "Comic" },
    { id: 8, name: "Fiction" },
    { id: 9, name: "Romance" },
    { id: 10, name: "History" },
    { id: 11, name: "Aptitude" },
    { id: 12, name: "Education" },
    { id: 13, name: "Religion" },
    { id: 14, name: "Biography" }
  ];

  //creating userDetailService
  constructor(
    private router: Router,
    private userDetailService: UserDetailsService
  ) {}

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

    this.router.navigate(["/login"]);
    location.reload();
  }

  //selectedPreferenceList is coming from the front end in a list and stored in userPreference in form of array
  selectedPreferenceList(list) {
    console.log(list);
    this.user.userPreferences = list;
  }
}
