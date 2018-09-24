import { FirebaseService, Cart } from "./../firebase.service";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { LogisticService } from "../logistics.service";
import { NgxSpinnerService } from "ngx-spinner";
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
  costarray = [0, 0, 0];
  slotsFinal = [
    {
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
  newOrder: any;
  array2: any;
  time: any;
  minValue: any;
  slotFinalPrice: any;
  finalPriceA: any;
  public slotAvailability: any;
  public slotCost: any;
  public timeslots: string[] = ["9AM-12PM", "3PM-6PM", "6PM-9PM"];
  orderdetails: any;
  date: any;
  sloty: any;
  totalLength: any;
  dat: any;
  constructor(
    private firebaseService: FirebaseService,
    route: ActivatedRoute,
    private logisticService: LogisticService,
    private router: Router,
    private spinner: NgxSpinnerService
  ) {
    //Do Something
    this.firebaseService.getCart().subscribe(carts => {
      this.carts = carts;
      this.totalLength = carts.length;
    });

    this.orderdetails = JSON.parse(localStorage.getItem("orderDetails"));
    this.logisticService
      .getAvailableSlots(this.orderdetails)
      .subscribe(data => {
        this.dat = data;
      });
  }

  ngOnInit() {
    this.spinner.show();

    setTimeout(() => {
      /** spinner ends after 5 seconds */
      this.spinner.hide();
    }, 10000);
    setTimeout(() => {
      this.det();
    }, 9000);

    setTimeout(() => {
      for (let i = 0; i < 3; i++) {
        if (this.array2[i] > 0) {
          this.costarray[i] = this.array2[i] - this.minValue;
        }
      }
      this.final();
    }, 9000);
  }

  det() {
    this.slotAvailability = this.dat.slotAvailability;
    this.slotCost = this.dat.slotCost;
    this.array2 = this.slotCost.map(Number);
    this.minValue = Math.min.apply(null, this.array2.filter(Boolean));
    this.addtoTotalPrice();
  }
  addtoTotalPrice() {
    for (let i = 0; i < this.array2.length; i++) {
      this.carts[0].totalPrice += this.costarray[i];
    }
    return this.carts[0].totalPrice;
  }

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
    for (let i = 0; i < 3; i++) {
      this.slotsFinal[i].slotCosts = this.costarray[i];
      this.slotsFinal[i].slotAvailability = this.slotAvailability[i];
    }
  }
  radioselected(slots, i) {
    this.isRadioSelected = true;
    this.time = i;
    this.slotFinalPrice = slots.slotCosts;
    this.finalPriceA = this.slotFinalPrice + this.getSum();

    this.logisticService.getOrder().subscribe(data=> {
      this.newOrder=data;
    });
    this.spinner.show();
    setTimeout(() => {
      /** spinner ends after 5 seconds */
      this.spinner.hide();
    }, 8000);
    setTimeout(() => {
      this.new();
    }, 8000);
  }

  new() {
    this.newOrder.newOrder.selectedSlot = this.time;
    console.log(this.newOrder);
  }
  isRadioSelect1() {
    return this.isRadioSelected;
  }
  paymentRoute() {
    localStorage.setItem("newOrder", JSON.stringify(this.newOrder));
    console.log(this.newOrder);
    setTimeout(() => {
      this.router.navigate(["/payment"]);
    }, 1000);
  }
}
