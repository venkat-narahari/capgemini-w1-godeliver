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
  wishlistLength: any;
  cartLength: any;
  addre: Address[];
  user: any;
  profile:any;
  constructor(
    private firebase: FirebaseService,
    private userDetails: UserDetailsService
  ) {}
  add: Address = {
    address: ""
  };

  ngOnInit() {
    this.userDetails
      .getUser(JSON.parse(localStorage.getItem("currentUserEmail")))
      .subscribe(data => (this.user = data));

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
  }

  addAddress(address) {
    this.add.address = address;
    this.firebase.addAddress(this.add);
  }

  deleteAdd(event, item) {
    this.firebase.deleteAdd(item);
  }
}
