import { Route } from './Model/Route';
import { Observable } from 'rxjs';
import { Order } from "./order-details";
import {
  HttpClient,
  HttpHeaders,
  HttpParams,
  HttpErrorResponse
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from "rxjs/operators";
;

const headers = new HttpHeaders({
  'Content-Type': 'application/json'
});

@Injectable({
  providedIn: "root"
})
export class LogisticService {
  data: any;
  private logistic_url = "http://13.233.38.65:9088/logistics/api/v1/slots";
  private slot_url = "http://13.233.38.65:9088/logistics/api/v1/slot";
  private order_url = "http://13.233.38.65:9088/order/api/v1/detail"
  constructor(private http: HttpClient) {}
  slots: object[];
  getOrderDetails(order) {
    const params = new HttpParams()
      .set("orderId", order.orderId)
      .set("orderConsumerName", order.orderConsumerName)
      .set("orderConsumerAddress", order.orderConsumerAddress)
      .set("orderConsumerPhone", order.orderConsumerPhone)
      .set("orderLatitude", order.orderLatitude)
      .set("orderLongitude", order.orderLongitude)
      .set("orderVolume", order.orderVolume)
      .set("orderDate", order.orderDate);
    this.data = this.http.get<Order[]>(this.logistic_url, { params: params });
    return this.data;
  }

  getAvailableSlots(order) {
    return this.getOrderDetails(order)
      .pipe(map(body => body["newOrder"]))
      .pipe(map(body => body["availableSlots"]));
  }

  getOrder() {
    return this.data;
  }
 setSlot(newOrder){
   return this.http.post(this.slot_url,JSON.stringify(newOrder), {headers});
 }
 setOrderDetails(newOrder) {
    return this.http.post(this.order_url,JSON.stringify(newOrder),{headers})
 }
}
