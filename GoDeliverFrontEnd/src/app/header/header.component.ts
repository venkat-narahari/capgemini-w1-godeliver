import { Component, EventEmitter, Output, OnInit } from "@angular/core";
import { BookService } from "../book.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"]
})
export class HeaderComponent implements OnInit {
  curUser: any;
  noUser: any;
  curuser: any;
  search:string="shivam";
  title:any;
  
  constructor(private bookService: BookService) {}

  ngOnInit() {
    if (localStorage.getItem("currentUserEmail") !== null) {
      this.curUser = JSON.parse(localStorage.getItem("currentUserEmail"));
      this.curuser = this.curUser.split(/@/);
      this.noUser = "";
    } else {
      this.noUser = "NoUser";
    }
  }
  // getBooks() {
  //   this.search=this.title;
  //   }

  //logout function to remove the user details from local storage
  logout() {
    localStorage.removeItem("currentUserEmail");
    localStorage.removeItem("currentUserToken");
    location.reload();
    this.noUser = "NoUser";
  }
}
