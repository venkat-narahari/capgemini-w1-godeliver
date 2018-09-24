import { Component, OnInit } from "@angular/core";
import { Cart } from "../firebase.service";
import { FirebaseService } from "../firebase.service";
import { ActivatedRoute, Router } from "@angular/router";
@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.css"]
})
export class CartComponent implements OnInit {
  carts: Cart[] = [];
  interval: any;
  itemToEdit: any;

  constructor(
    private firebase: FirebaseService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.firebase.getCart().subscribe(data => {
      this.carts = data;
    });

  }

  ngOnInit() {}

  deleteItem(event, item) {
    this.firebase.deleteItem(item);
  }

  incrementQuantity(event, item: Cart) {
    item.quantity += 1;
    if (item.quantity > 5) {
      item.quantity = 5;
      item.totalPrice = item.quantity * item.cost;
      item.totalVolume = item.quantity * item.volume;
    }
    item.totalPrice = item.quantity * item.cost;
    item.totalVolume = item.quantity * item.volume;
    this.firebase.updateItem(item);
  }

  decrementQuantity(event, item: Cart) {
    if (item.quantity > 1) {
      item.quantity -= 1;
      item.totalPrice = item.quantity * item.cost;
      item.totalVolume = item.quantity * item.volume;
      this.firebase.updateItem(item);
    }
  }

  getSum(index: number): number {
    let sum = 0;
    let totalQuantity = 0;
    for (let i = 0; i < this.carts.length; i++) {
      totalQuantity += this.carts[i].quantity;
      sum += this.carts[i].totalPrice;
    }
    return sum;
  }

  totalQuantity(index: number): number {
    let totalQuantity = 0;
    for (let i = 0; i < this.carts.length; i++) {
      totalQuantity += this.carts[i].quantity;
    }
    return totalQuantity;
  }


}
