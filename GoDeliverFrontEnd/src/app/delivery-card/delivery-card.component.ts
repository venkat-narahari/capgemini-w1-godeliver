import { LogisticService } from './../logistics.service';
import { FirebaseService, Cart } from './../firebase.service';
import { Order } from './../order-details';
import { Component, OnInit, Input } from "@angular/core";
import { v4 as uuid} from "uuid";

@Component({
  selector: "app-delivery-card",
  templateUrl: "./delivery-card.component.html",
  styleUrls: ["./delivery-card.component.css"]
})
export class DeliveryCardComponent implements OnInit {
  @Input() address;
  isRadioSelected: boolean = false;
  orders: any;
  date:any;
  carts: Cart[];
  totalLength: any;
  order = new Order("", "", "", "", "", "", "", "");

  constructor(private fb: FirebaseService, private logistic: LogisticService) {}

  ngOnInit() {
    this.fb.getCart().subscribe(carts => {
      this.carts = carts;
      this.totalLength = carts.length;
    });
  }
  radioselected(address) {
    this.isRadioSelected = true;
  }

  isRadioSelect() {
    return this.isRadioSelected;
  }
  orderDetails(address, phone, name, city, addLat, addLng, orderDate) {
    console.log(address, phone, name,addLat);
    this.order.orderId = uuid().replace(/-/g, "");
    console.log(this.order.orderId);
    this.order.orderConsumerName = name;
    console.log(this.order.orderConsumerName);
    this.order.orderConsumerAddress = address + ", " + city;
    console.log(this.order.orderConsumerAddress);
    this.order.orderConsumerPhone = phone;
    console.log(this.order.orderConsumerName);
    this.order.orderVolume = this.getVolume().toString();
    console.log(this.order.orderVolume);
    this.order.orderLatitude = addLat;
    console.log(addLat);
    this.order.orderLongitude = addLng;
    console.log(addLng);
    this.order.orderDate = this.date.toString();
    console.log(this.order.orderDate)
    this.logistic.orderDetails(this.order);
  }
  deleteAddress(event, item) {
    this.fb.deleteAdd(item);
  }
  getVolume() {
    let volumeTotal = 0;
    for (let i = 0; i < this.totalLength; i++) {
      volumeTotal += this.carts[i].totalVolume;
    }
    console.log(volumeTotal);
    return volumeTotal;
  }
}