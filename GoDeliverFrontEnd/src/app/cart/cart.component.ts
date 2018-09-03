import { Component, OnInit } from "@angular/core";
import { Cart } from "../firebase.service";
import { FirebaseService } from "../firebase.service";
import { ActivatedRoute } from "@angular/router";
@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.css"]
})
export class CartComponent implements OnInit {
  carts: Cart[];
  constructor(private firebase: FirebaseService, route:ActivatedRoute) {}

  ngOnInit() {
    this.firebase.getCart().subscribe(carts => {
      this.carts=carts; 
    });
  }

  deleteItem(event, item) {
    this.firebase.deleteItem(item);
  }
  incrementQuantity(item:Cart) {
    item.quantity+=1;
    if(item.quantity>5) {
      item.quantity=5
    }
  }
  decrementQuantity(item:Cart) {
    item.quantity-=1;
    if(item.quantity<1) {
      item.quantity=1
    }
  }
}
