import { FirebaseService, Cart } from "./../firebase.service";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";

//when user navigates to this component
@Component({
 selector: "app-timeslots",
 templateUrl: "./timeslots.component.html",
 styleUrls: ["./timeslots.component.css"]
})
export class TimeslotsComponent implements OnInit {
 carts: Cart[];
 usAdd: any;
 isRadioSelected: boolean = false;
 costarray=[0,0,0];
 slotsFinal = [{
   slotAvailability: false,
   slotCosts: null
 },
 {
   slotAvailability: false,
   slotCosts: null
 },
 {
   slotAvailability: false,
   slotCosts: null
 }
];

slotFinalPrice:any;
finalPriceA:any;
 public slotAvailability: boolean[] = [true, true, false];
 public slotCost: string[] = ["70", "40", "0"];
 array2 = this.slotCost.map(Number);
 minValue = Math.min.apply(null, this.array2.filter(Boolean));
 public timeslots: string[] = ["9AM-12PM", "3PM-6PM", "6PM-9PM"];

 date: any;
 totalLength: any;
 // isButtonSelected: boolean = false;
 constructor(
   private firebase: FirebaseService,
   route: ActivatedRoute,
   private router: Router
 ) {
   //Do Something
 }

 ngOnInit() {
   this.date = new Date();
   this.firebase.getAddress().subscribe(carts => {
     this.usAdd = carts;
   });
   this.firebase.getCart().subscribe(carts => {
     this.carts = carts;
     this.totalLength = carts.length;
   });
   console.log(this.minValue);

     for(let i=0;i<3;i++){
       if((this.array2[i])>0){
         this.costarray[i]=this.array2[i]-this.minValue;
       }
       setTimeout(()=> {
         this.final();
       },1000);
     }


     console.log(this.costarray);
 }
  addtoTotalPrice() {
    for(let i=0;i<this.array2.length;i++) {
      this.carts[0].totalPrice += this.costarray[i];
    }
    return this.carts[0].totalPrice;
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

final() {
 for(let  i=0;i<3;i++) {
     this.slotsFinal[i].slotCosts=this.costarray[i];
     this.slotsFinal[i].slotAvailability=this.slotAvailability[i];

 }
}
 radioselected(slots) {
   this.isRadioSelected = true;
   this.slotFinalPrice=slots.slotCosts;
   this.finalPriceA= this.slotFinalPrice+this.getSum();
 }

 isRadioSelect1() {
   return this.isRadioSelected;
 }

 paymentRoute() {
   this.router.navigate(["/payment"]);
   location.reload();
 }
}
