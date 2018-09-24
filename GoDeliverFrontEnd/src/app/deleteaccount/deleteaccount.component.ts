import { Component, OnInit } from '@angular/core';
import { FirebaseService } from '../firebase.service';
import { UserDetailsService } from '../user-details.service';

@Component({
  selector: 'app-deleteaccount',
  templateUrl: './deleteaccount.component.html',
  styleUrls: ['./deleteaccount.component.css']
})
export class DeleteaccountComponent implements OnInit {

  email:any;
  constructor(private firebaseService:FirebaseService, private userProfile:UserDetailsService) { }

  ngOnInit() {
  }

  deleteAccount() {
    this.firebaseService.deleteUnregUser();
    this.email=JSON.parse(localStorage.getItem("currentUserEmail"));
    this.userProfile.deleteUser(this.email);

  }
}
