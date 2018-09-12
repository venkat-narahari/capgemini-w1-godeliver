import { MatListModule } from '@angular/material/list';
import { LogisticService } from './../logistics.service';
import { v4 as uuid } from "uuid"
import { Order } from './../order-details';
import { ActivatedRoute } from "@angular/router";
import { FirebaseService, Address, Cart } from "./../firebase.service";
import { Component, OnInit, Input } from "@angular/core";
import {
  FormControl,
  FormGroup,
  FormsModule,
  FormGroupDirective,
  NgForm,
  FormBuilder,
  Validators
} from "@angular/forms";
@Component({
  selector: "app-billing",
  templateUrl: "./billing.component.html",
  styleUrls: ["./billing.component.css"]
})
export class BillingComponent implements OnInit {
  orders: any;
  order = new Order("","","","","","","","");
  email = new FormControl('', [Validators.required, Validators.email]);
  isRadioSelected: boolean = false;
  orderId: string;
  add: Address = {
    address: "",
    email: "",
    phone: "",
    city:"",
    name: "",
    addType: "",
    orderId: "",
  };
  firstname:any;
  usAdd: any;
  carts: Cart[];
  phoneNumb:any;
  address:any;
  city:any;
  usermail: any;
  usersAddress:any;
  // usersEmail:any
  userEmail: FormControl = new FormControl("", [
    Validators.required,
    Validators.email
  ]);
  panelOpenState = false;
  userForm: FormGroup;
  submitted = false;
  totalLength: any;
  constructor(
    private firebase: FirebaseService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private logistic: LogisticService
  ) {}

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      userName: ["", [Validators.required]],
      userEmail: ["", [Validators.required]],
      userPhone: ["", [Validators.required]],
      usercity: ["",[Validators.required]],
      userAddress: ["", [Validators.required]],
      userAddType: ["", [Validators.required]]
    });
    
    this.firebase.getAddress().subscribe(carts => {
      this.usAdd = carts;
    });
    this.firebase.getCart().subscribe(carts => {
      this.carts = carts;
      this.totalLength = carts.length;
    });
    // this.add.orderId=(uuid());
    // console.log(this.add.orderId);
  }
  totalQuant() {
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
 
 getVolume(){
   let volumeTotal = 0;
   for(let i=0; i < this.totalLength; i++) {
     volumeTotal += this.carts[i].totalVolume
   }
   return volumeTotal;
 } 
  onSubmit() {
      this.add.name = this.firstname;
      this.add.phone = this.phoneNumb;
      this.add.address=this.address;
      this.add.city=this.city;
      this.add.email=this.usermail;
      this.firebase.addAddress(this.add);
  }
  radioselected() {
    this.isRadioSelected = true;
  }

  isRadioSelect(){
    return this.isRadioSelected;
  }
  orderDetails(address){
  this.order.orderId=(uuid().replace(/-/g,''));
  console.log(this.order.orderId);
  this.order.orderConsumerName=this.add.name;
  console.log(this.order.orderConsumerName);
  this.order.orderConsumerAddress=this.add.address+", "+this.add.city;
  console.log(this.order.orderConsumerAddress);
  this.order.orderConsumerPhone=this.add.phone;
  console.log(this.order.orderConsumerName);
  this.order.orderVolume=this.getVolume().toString();
  console.log(this.order.orderVolume);
  //this.logistic.orderDetails().subscribe(res => console.log("done"));
  }

  deleteAddress(event, item) {
    this.firebase.deleteAdd(item);
  }
  get userName() {
    return this.userForm.get("userName");
  }
  get userPhone() {
    return this.userForm.get("userPhone");
  }
  get userMail() {
    return this.userForm.get("userEmail");
  }
  get usercity()
  {
    return this.userForm.get("usercity")
  }
  get userAddress() {
    return this.userForm.get("userAddress");
  }
}

 