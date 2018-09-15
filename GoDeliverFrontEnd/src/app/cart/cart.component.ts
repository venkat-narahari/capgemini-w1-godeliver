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
  interval: any;
  itemToEdit: any;
  noCart:boolean;
  constructor(private firebase: FirebaseService, route: ActivatedRoute) {}

  ngOnInit() {
        this.firebase.getCart().subscribe(carts => {
      this.carts = carts;
    });
    if(this.carts.length==null) {
      this.noCart=true;
    }
    else
    this.noCart=false;

  }

 
  deleteItem(event, item) {
    this.firebase.deleteItem(item);
  }
  incrementQuantity(event, item: Cart) {
    item.quantity += 1;
    item.totalPrice = item.quantity * item.cost;
    item.totalVolume = item.quantity * item.volume;
    if (item.quantity > 5) {
      item.quantity = 5;
    }
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
  getSum(index: number) : number {
    let sum = 0;
    let totalQuantity=0;
    for(let i = 0; i < this.carts.length; i++) {
      totalQuantity+= this.carts[i].quantity;
      sum += this.carts[i].totalPrice;
    }
    return sum;
  }
  totalQuantity(index: number) : number {
    let totalQuantity=0;
    for(let i = 0; i < this.carts.length; i++) {
      totalQuantity+= this.carts[i].quantity;
    }
    return totalQuantity;
  }
}
