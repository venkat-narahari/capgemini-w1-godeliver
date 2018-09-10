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
  search: string = "shivam";
  title: any;
  admin:any;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    if (localStorage.getItem("currentUserEmail") !== null) {
      if(localStorage.getItem("xavdnmzicn")!==null) {
        this.admin=JSON.parse(localStorage.getItem("xavdnmzicn"));
      }
      this.curUser = JSON.parse(localStorage.getItem("currentUserEmail"));
      console.log(this.curUser);
      this.noUser = "";
    } else {
      this.noUser = "NoUser";
    }
  }

  //logout function to remove the user details from local storage
  logout() {
    localStorage.removeItem("currentUserEmail");
    localStorage.removeItem("currentUserToken");
    localStorage.removeItem("xavdnmzicn");
    location.reload();
    this.noUser = "NoUser";
  }
}
