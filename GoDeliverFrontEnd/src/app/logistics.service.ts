import { Order } from './order-details';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class LogisticService {
 private logistics_url="http://localhost:8890/api/v1/save";
 private slot_url="http://localhost:8086/api/v1/";
 private vehicles_url="http://localhost:8890/api/v1/"
  constructor(private http: HttpClient) { }
orders: Order;
  orderDetails(): Observable<Order[]> {
    return this.http.get<Order[]>(this.slot_url+'slots?orderId='+this.orders.orderId+'&orderConsumerName='+this.orders.orderConsumerPhone+'&orderConsumerAddress='+this.orders.orderConsumerAddress+'&orderConsumerPhone='+this.orders.orderConsumerPhone+'&orderLatitude='+this.orders.orderLatitude+'&orderLongitude='+this.orders.orderLongitude+'&orderVolume='+this.orders.orderVolume+'&orderDate='+this.orders.orderDate);
  }

}
