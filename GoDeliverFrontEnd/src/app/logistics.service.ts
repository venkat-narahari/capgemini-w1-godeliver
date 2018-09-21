import { Order } from "./order-details";
import {
  HttpClient,
  HttpHeaders,
  HttpParams,
  HttpErrorResponse
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from "rxjs/operators";
import { log } from "util";
const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable({
  providedIn: "root"
})
export class LogisticService {
  data: any;
  private slot_url = "http://35.154.246.37:9088/logistics/api/v1/slots";
  private vehicles_url = "http://172.23.239.100:8080/logistics/api/v1/";
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
    this.data = this.http.get<Order[]>(this.slot_url, { params: params });
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

}
