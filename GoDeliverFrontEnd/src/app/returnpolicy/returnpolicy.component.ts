import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-returnpolicy',
  templateUrl: './returnpolicy.component.html',
  styleUrls: ['./returnpolicy.component.css']
})
export class ReturnpolicyComponent implements OnInit {
curUser:any;
curuser:any;
  constructor() { }

  ngOnInit() {
    if(localStorage.getItem('currentUserEmail')!=null)
    {
      this.curUser=JSON.parse(localStorage.getItem('currentUserEmail'));
      this.curUser=this.curUser.toUpperCase();
      this.curuser= this.curUser.split(/@/);
    }
  }

}
