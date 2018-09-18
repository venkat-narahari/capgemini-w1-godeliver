import { Component, OnInit } from '@angular/core';
import { FirebaseService } from '../firebase.service';

@Component({
  selector: 'app-deleteaccount',
  templateUrl: './deleteaccount.component.html',
  styleUrls: ['./deleteaccount.component.css']
})
export class DeleteaccountComponent implements OnInit {

  constructor(private firebase:FirebaseService) { }

  ngOnInit() {
  }

  deleteAccount() {
    this.firebase.deleteUnregUser();
  }
}
