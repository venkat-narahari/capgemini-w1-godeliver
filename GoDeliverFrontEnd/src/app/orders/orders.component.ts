import { Component, OnInit } from "@angular/core";
import { OrderService } from "../order.service";
import { ActivatedRoute, Router } from "@angular/router";
import { FirebaseService, Cart } from "../firebase.service";

@Component({
  selector: "app-orders",
  templateUrl: "./orders.component.html",
  styleUrls: ["./orders.component.css"]
})
export class OrdersComponent implements OnInit {
  orderDetails: any;
  carts: Cart[];
  constructor(
    private orderService: OrderService,
    private router: ActivatedRoute,
    private route: Router,
    private firebaseService: FirebaseService
  ) {
    this.firebaseService.getCart().subscribe(data => {
      this.carts = data;
    });
  }

  ngOnInit() {
    this.router.params.subscribe(params => {
      const id = params["orderid"];
      // this.orderService.getOrder(id).subscribe(data => {
      //   this.orderDetails = data;
      //   console.log(data);
      
      // });
      this.orderDetails = JSON.parse(localStorage.getItem("orderDetails"));
    });
  
  }

  deleteCart() {
    localStorage.removeItem("orderDetails");
    this.firebaseService.deleteCart();
    this.route.navigate([""]);
  }
}
