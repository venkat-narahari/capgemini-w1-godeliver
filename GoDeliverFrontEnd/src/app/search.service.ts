import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
 providedIn: 'root'
})
export class SearchService {

 constructor(private http: HttpClient) { }


 getLatLng(address) {
   return this.http.get('https://www.mapquestapi.com/geocoding/v1/address?key=6CiJIAqp5UGerYPsZZ0131jSf66f5xzP&inFormat=kvp&outFormat=json&location=' + address + '%2C+India&thumbMaps=false')
   .pipe(map(body => body['results']))
   .pipe(map(body=>body[0]))
   .pipe(map(body=>body['locations']))
   .pipe(map(body=>body[0]))
   .pipe(map(body=>body['latLng']));
 }
}
