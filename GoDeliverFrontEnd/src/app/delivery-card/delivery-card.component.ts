import { Router } from "@angular/router";
import { LogisticService } from "./../logistics.service";
import { FirebaseService, Cart } from "./../firebase.service";
import { Order } from "./../order-details";
import { Component, OnInit, Input, Output } from "@angular/core";
import { v4 as uuid } from "uuid";

@Component({
  selector: "app-delivery-card",
  templateUrl: "./delivery-card.component.html",
  styleUrls: ["./delivery-card.component.css"]
})
export class DeliveryCardComponent implements OnInit {
  @Input()
  address;
  isRadioSelected: boolean = false;
  orders: any;
  date: any;
  carts: Cart[];
  totalLength: any;
  order = new Order("", "", "", "", "", "", "", "");

  constructor(
    private fb: FirebaseService,
    private logistic: LogisticService,
    private router: Router
  ) {}

  ngOnInit() {
    this.fb.getCart().subscribe(carts => {
      this.carts = carts;
      this.totalLength = carts.length;
    });
    // JSON.parse(localStorage.getItem()
  }
  radioselected(address) {
    this.isRadioSelected = true;
  }

  isRadioSelect() {
    return this.isRadioSelected;
  }
  orderDetails(address, phone, name, city, addLat, addLng, orderDate) {
    console.log(address, phone, name, addLat);
    this.order.orderId = uuid().replace(/-/g, "");
    console.log(this.order.orderId);
    this.order.orderConsumerName = name;
    console.log(this.order.orderConsumerName);
    this.order.orderConsumerAddress = address + ", " + city;
    console.log(this.order.orderConsumerAddress);
    this.order.orderConsumerPhone = phone;
    console.log(this.order.orderConsumerName);
    this.order.orderVolume = localStorage.getItem('totalVolume');
    console.log(this.order.orderVolume);
    this.order.orderLatitude = addLat;
    console.log(addLat);
    this.order.orderLongitude = addLng;
    console.log(addLng);
    this.order.orderDate = this.date.toString();
    console.log(this.order.orderDate);
    localStorage.setItem("orderDetails", JSON.stringify(this.order));
    console.log(this.order);
    
    setTimeout(() => {
      this.router.navigate(["/timeslots"]);
      location.reload();
    }, 9000);
  }
  deleteAddress(event, item) {
    this.fb.deleteAdd(item);
  }
  // totalQuant() {
  //   let totalQuantity = 0;

  //   for (let i = 0; i < this.totalLength; i++) {
  //     totalQuantity += this.carts[i].quantity;
  //   }
  //   console.log(totalQuantity);
  //   return totalQuantity;
  // }
  // getVolume() {
  //   let volumeTotal = 0;
  //   for (let i = 0; i < this.totalLength; i++) {
  //     volumeTotal += this.carts[i].totalVolume;
  //   }
  //   console.log(volumeTotal);
  //   return volumeTotal;
  // }
}
