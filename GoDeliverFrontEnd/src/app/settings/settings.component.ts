import { Component, OnInit } from '@angular/core';
import { FirebaseService } from '../firebase.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  add:any;
  constructor(private firebase:FirebaseService) { }

  ngOnInit() {
    this.firebase.getAddress().subscribe(data => {
      this.add=data;
    })
  }

}
