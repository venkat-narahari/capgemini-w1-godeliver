import { Order } from './order-details';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class LogisticService {

 private slot_url="http://172.23.239.100:8080/logistics/api/v1/slots";
 private vehicles_url="http://172.23.239.100:8080/logistics/api/v1/"
  constructor(private http: HttpClient) { }
  slots : object[];
  orderDetails(order) {
   const params = new HttpParams()
            .set("orderId", order.orderId)
            .set("orderConsumerName",order.orderConsumerName)
            .set("orderConsumerAddress",order.orderConsumerAddress)
            .set("orderConsumerPhone",order.orderConsumerPhone)
            .set("orderLatitude",order.orderLatitude)
            .set("orderLongitude",order.orderLongitude)
            .set("orderVolume",order.orderVolume)
            .set("orderDate",order.orderDate);
    return this.http.get<Order[]>(this.slot_url, {params: params}).subscribe(data =>
      {console.log(data)}
    );
  }
}

