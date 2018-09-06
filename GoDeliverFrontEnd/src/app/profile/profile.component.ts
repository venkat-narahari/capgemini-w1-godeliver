import { Component, OnInit } from "@angular/core";
import { Address } from "../firebase.service";
import { FirebaseService } from "../firebase.service";
import { Cart } from "../firebase.service";
import { UserDetailsService } from "../user-details.service";
@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.css"]
})
export class ProfileComponent implements OnInit {
  wishlistLength;
  cartLength;
  addre: Address[];
  user: any;
  profile: any;
  userEmail:any;
  constructor(
    private firebase: FirebaseService,
    private userDetails: UserDetailsService
  ) {}
  add: Address = {
    address: ""
  };

  ngOnInit() {
    this.userEmail=JSON.parse(localStorage.getItem('currentUserEmail'));
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

    this.userDetails
      .getUser(this.userEmail)
      .subscribe(data => {
        this.user = data;
      });

  }

  addAddress(address) {
    this.add.address = address;
    this.firebase.addAddress(this.add);
  }

  deleteAdd(event, item) {
    this.firebase.deleteAdd(item);
  }
}
