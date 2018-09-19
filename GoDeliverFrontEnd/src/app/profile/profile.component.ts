import { Component, OnInit } from "@angular/core";
import { Address } from "../firebase.service";
import { FirebaseService } from "../firebase.service";
import { UserDetailsService } from "../user-details.service";
import { BookService } from "../book.service";
@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.css"]
})
export class ProfileComponent implements OnInit {
  wishlistLength;
  cartLength;
  addre: Address[];
  userdata: any;
  profile: any;
  userEmail: any;
  top_selling:any;
  constructor(
    private firebase: FirebaseService,
    private userDetails: UserDetailsService,
    private bookService:BookService
  ) {}

  ngOnInit() {
    this.userEmail = JSON.parse(localStorage.getItem("currentUserEmail"));
    console.log(this.userEmail);

    this.firebase.getAddress().subscribe(address => {
      this.addre = address;
    });

    this.firebase.getCart().subscribe(carts => {
      this.cartLength = carts.length;
    });

    this.firebase.getUserProfile().subscribe(profile => {
      this.profile = profile;
    });

    this.firebase.getWishlist().subscribe(wishlist => {
      this.wishlistLength = wishlist.length;
    });

    this.userDetails.getUser(this.userEmail).subscribe(data => {
      this.userdata = data;
    });
    //getting top sellingbooks
    this.bookService.getTopSellingList().subscribe(data => {
      this.top_selling = data;
    });
  }
}
