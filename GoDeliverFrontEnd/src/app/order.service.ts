import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class OrderService {

  //orderdetails_url = "http://localhost:8080/order/api/v1/order?orderid=";
  constructor(public http: HttpClient) { }

  getOrder(orderId) {
    console.log(orderId);
   return this.http.get("assets/order.json").pipe(map(body=>body["order"])).pipe(map(body=>body[0])).pipe(map(body=>body[orderId])).pipe(map(body=>body[0]));
  }

}
