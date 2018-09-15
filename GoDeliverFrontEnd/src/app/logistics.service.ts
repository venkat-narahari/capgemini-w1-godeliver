import { Order } from './order-details';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class LogisticService {
 private logistics_url="http://localhost:8890/api/v1/save";
 private slot_url="http://localhost:8086/api/v1/";
 private vehicles_url="http://localhost:8890/api/v1/"
  constructor(private http: HttpClient) { }
  orderDetails(order) {
    return this.http.get<Order[]>(this.slot_url+'slots?orderId='+order.orderId+'&orderConsumerName='+order.orderConsumerName+'&orderConsumerAddress='+order.orderConsumerAddress+'&orderConsumerPhone='+order.orderConsumerPhone+'&orderLatitude='+order.orderLatitude+'&orderLongitude='+order.orderLongitude+'&orderVolume='+order.orderVolume+'&orderDate='+order.orderDate);
  }

}
