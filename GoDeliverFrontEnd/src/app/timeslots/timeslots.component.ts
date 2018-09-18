import { v4 as uuid } from "uuid"
import { FirebaseService, Cart } from './../firebase.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";

 

  //when user navigates to this component
  


@Component({
  selector: 'app-timeslots',
  templateUrl: './timeslots.component.html',
  styleUrls: ['./timeslots.component.css']
})
export class TimeslotsComponent implements OnInit {
carts: Cart[];
 usAdd:any;
 isRadioSelected:boolean=false;
  a :'a';
 public  slotAvailability:boolean[] = [true,false,true]; 
 public slotCost: string[]=["$5","","$7"];
 public timeslots:string[]=["9AM-12PM","3PM-6PM","6PM-9PM"];
 
// 

date:any;
totalLength: any;
// isButtonSelected: boolean = false;

  constructor(private firebase: FirebaseService, route: ActivatedRoute,  private router: Router) { 
  
      //Do Something
    
  }
  
  
  // ngOnInit(date){
  //   this.date = new Date();
  // }

  ngOnInit() {
   this.date = new Date();
    this.firebase.getAddress().subscribe(carts => {
      this.usAdd = carts;
    });
    this.firebase.getCart().subscribe(carts => {
      this.carts = carts;
      this.totalLength = carts.length;
    });
  
  }

// isButtonSelect(){
//   return this.isButtonSelected
// }
  totalQuantity() {
    let totalQuantity = 0;
 
    for (let i = 0; i < this.totalLength; i++) {
      totalQuantity += this.carts[i].quantity;
    }
    return totalQuantity;
  }
  getSum() {
    let sum = 0;
 
    for (let i = 0; i < this.totalLength; i++) {
      sum += this.carts[i].totalPrice;
    }
    return sum;
  }
  

// public list: string;
// public gen() {
//   console.log(typeof uuid());
//   this.list=(uuid().replace(/-/g,''));
// }
radioselected() {
   this.isRadioSelected = true;
 }
 

 isRadioSelect1() {
   return this.isRadioSelected;
 }

billingRoute() {
  this.router.navigate(["/billing"]);
  location.reload();
}

}
