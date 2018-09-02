import { Component, OnInit } from '@angular/core';
import {Address} from '../firebase.service';
import { FirebaseService } from "../firebase.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  addre: Address[];
  constructor(private firebase:FirebaseService) { }
  add: Address = {
   address:''
   };

  ngOnInit() {
    this.firebase.getAddress().subscribe(address => {
      this.addre=address; 
    });
  }

  addAddress(address) {
   this.add.address=address;
    this.firebase.addAddress(this.add);
  }

  deleteAdd(event, item) {
    this.firebase.deleteAdd(item);
  }

}
