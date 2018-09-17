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
 isRadioSelected1:boolean=false;
 isRadioSelected2:boolean=false;
 
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
radioselected(timeslot1) {
   this.isRadioSelected = true;
 }
 radioselected1(timeslot2) {
  this.isRadioSelected1= true;
}
radioselected2(timeslot3) {
  this.isRadioSelected2= true;
}

 isRadioSelect1() {
   return this.isRadioSelected;
 }

 isRadioSelect2() {
  return this.isRadioSelected1;
}
isRadioSelect3() {
  return this.isRadioSelected2;
}

billingRoute() {
  this.router.navigate(["/billing"]);
  location.reload();
}

}
