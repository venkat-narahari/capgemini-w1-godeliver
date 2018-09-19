import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  data:any;
  date = 'http://172.23.239.100:8080/logistics/api/v1/date?date='

  constructor(private http:HttpClient) { }

  getDate(item) {
  return this.http.get("../../assets/location.json");
}

  getRoute(item) {
   return this.getDate(item).pipe(map(body => body['slots'])).pipe(map(body => body[item.slot])).pipe(map(body => body['slotVehicle'])).pipe(map(body => body[item.vehicleId])).pipe(map(body => body['vehicleRoute']));
  }

  getTotalVolume(item) {
    return this.getDate(item).pipe(map(body => body['slots'])).pipe(map(body => body[item.slot])).pipe(map(body => body['slotVehicle'])).pipe(map(body => body[item.vehicleId]));
   }

   getTotalDistance(item) {
    return this.getDate(item).pipe(map(body => body['slots'])).pipe(map(body => body[item.slot])).pipe(map(body => body['slotVehicle']));
   }

   getSlotData(item) {
    return this.getDate(item).pipe(map(body => body['slots'])).pipe(map(body => body[item.slot])).pipe(map(body => body['slotVehicle']));
   }


}
