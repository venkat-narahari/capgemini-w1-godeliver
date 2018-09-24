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
  email: any;
  order = new Order("", "", "", "", "", "", "", "", "");

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

    if (localStorage.getItem("uid") != null) {
      this.email = JSON.parse(localStorage.getItem("uid"));
    } else this.email = JSON.parse(localStorage.getItem("currentUserEmail"));
    // JSON.parse(localStorage.getItem()
  }
  radioselected(address) {
    this.isRadioSelected = true;
  }

  isRadioSelect() {
    return this.isRadioSelected;
  }
  orderDetails(address, phone, name, city, addLat, addLng) {
    this.order.orderId = uuid().replace(/-/g, "");
    this.order.orderConsumerName = name;
    this.order.emailId = this.email;
    this.order.orderConsumerAddress = address + ", " + city;
    this.order.orderConsumerPhone = phone;
    this.order.orderVolume = localStorage.getItem("totalVolume");
    this.order.orderLatitude = addLat;
    this.order.orderLongitude = addLng;
    this.order.orderDate = this.date.toString();
    localStorage.setItem("orderDetails", JSON.stringify(this.order));

    setTimeout(() => {
      this.router.navigate(["/timeslots"]);
    }, 2000);
  }
  deleteAddress(event, item) {
    this.fb.deleteAdd(item);
  }
}
