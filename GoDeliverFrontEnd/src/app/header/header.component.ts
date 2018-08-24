import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  curUser:any;
  noUser:any;
  curuser:any;
  constructor() { }

  ngOnInit() { 
    if(localStorage.getItem('currentUserEmail')!==null) {
      this.curUser=JSON.parse(localStorage.getItem('currentUserEmail'));
       this.curuser= this.curUser.split(/@/);
      this.noUser="";
    }
    else 
    {
      this.noUser="NoUser";
    }
   }


  //logout function to remove the user details from local storage
  logout() {
    localStorage.removeItem('currentUserEmail');
    localStorage.removeItem('currentUserToken');
    location.reload();
    this.noUser="NoUser"; 
}

}
