import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  data:any;
  date = 'http://13.232.107.34:9088/logistics/api/v1/date?date=';

  constructor(private http:HttpClient) { }

  getDate(selectedDate) {
    //return this.http.get(this.date+selectedDate);
   return this.http.get("../../assets/location.json");
}

  // getRoute(selectedDate) {
  //  return this.getDate().pipe(map(body => body[selectedDate])).pipe(map(body => body['slots'])).pipe(map(body => body[selectedDate.slot])).pipe(map(body => body['slotVehicle'])).pipe(map(body => body[selectedDate.vehicleId])).pipe(map(body => body['vehicleRoute']));
  // }

  // getTotalVolume(selectedDate) {
  //   return this.getDate().pipe(map(body => body['slots'])).pipe(map(body => body[selectedDate.slot])).pipe(map(body => body['slotVehicle'])).pipe(map(body => body[selectedDate.vehicleId]));
  //  }

   getSlotVehicle(selectedDate) {
    //return this.getDate(selectedDate).pipe(map(body => body['slots'])).pipe(map(body => body[selectedDate.slot])).pipe(map(body => body['slotVehicle']));
    return this.getDate(selectedDate).pipe(map(body => body[selectedDate])).pipe(map(body => body['slots'])).pipe(map(body => body[selectedDate.slot])).pipe(map(body => body['slotVehicle']));
   }

   getSlotData(selectedDate) {
     //return this.getDate(selectedDate).pipe(map(body => body['slots']));
    return this.getDate(selectedDate).pipe(map(body => body[selectedDate])).pipe(map(body => body['slots']));
   }

}
