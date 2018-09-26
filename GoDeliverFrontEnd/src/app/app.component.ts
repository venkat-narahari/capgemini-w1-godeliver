import { Component, OnInit } from '@angular/core';
import { v4 as uuid } from "uuid";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'GoDeliver';
  uniqueId: any;
  
  constructor(){}
ngOnInit() {
  if (localStorage.getItem("currentUserEmail") == null) {
    if (localStorage.getItem("uid") != null) {
    } else {
      this.uniqueId = uuid().replace(/-/g, "");
      let key = "uid";
      localStorage.setItem(key, JSON.stringify(this.uniqueId));
    }
  }
}
}
