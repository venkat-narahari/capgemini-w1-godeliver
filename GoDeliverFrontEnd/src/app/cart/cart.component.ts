import { Component, OnInit } from "@angular/core";
import { Cart } from "../firebase.service";
import { FirebaseService } from "../firebase.service";
@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.css"]
})
export class CartComponent implements OnInit {
  carts: Cart[];
  constructor(private firebase: FirebaseService) {}

  ngOnInit() {
    this.firebase.getCart().subscribe(carts => {
      //console.log(carts);
      this.carts=carts; 
    });
  }
}
