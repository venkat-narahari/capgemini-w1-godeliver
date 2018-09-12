import { v4 as uuid } from "uuid"
import { FirebaseService, Cart } from './../firebase.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-timeslots',
  templateUrl: './timeslots.component.html',
  styleUrls: ['./timeslots.component.css']
})
export class TimeslotsComponent  {
carts: Cart[];
usAdd:any;
totalLength: any;
isButtonSelected: boolean = false;

  constructor(private firebase: FirebaseService) { }

//   ngOnInit() {
//     this.firebase.getCart().subscribe(carts => {
//       this.carts = carts;
//       this.totalLength = carts.length;
//     });
//     this.firebase.getAddress().subscribe(carts => {
//       this.usAdd = carts;
//     });
//   }

// isButtonSelect(){
//   return this.isButtonSelected
// }
//   totalQuant() {
//     let totalQuantity = 0;
 
//     for (let i = 0; i < this.totalLength; i++) {
//       totalQuantity += this.carts[i].quantity;
//     }
//     return totalQuantity;
//   }
//   getSum() {
//     let sum = 0;
 
//     for (let i = 0; i < this.totalLength; i++) {
//       sum += this.carts[i].totalPrice;
//     }
//     return sum;
//   }

// public list: string;
// public gen() {
//   console.log(typeof uuid());
//   this.list=(uuid().replace(/-/g,''));
// }

}
