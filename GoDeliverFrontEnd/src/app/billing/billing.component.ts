import { LogisticService } from "./../logistics.service";
import { ActivatedRoute } from "@angular/router";
import { FirebaseService, Address, Cart } from "./../firebase.service";
import { Component, OnInit, Input, Output } from "@angular/core";
import {
  FormControl,
  FormGroup,
  FormsModule,
  FormGroupDirective,
  NgForm,
  FormBuilder,
  Validators
} from "@angular/forms";
import { SearchService } from "../search.service";
import { EventEmitter } from "events";
@Component({
  selector: "app-billing",
  templateUrl: "./billing.component.html",
  styleUrls: ["./billing.component.css"]
})
export class BillingComponent implements OnInit {
  orders: any;
  email = new FormControl("", [Validators.required, Validators.email]);
  bool = true;
  add: Address = {
    address: "",
    email: "",
    phone: "",
    city: "",
    name: ""
  };
  firstname: any;
  addressList;
  carts: Cart[];
  phoneNumb: any;
  address: any;
  city: any;
  usermail: any;
  usersAddress: any;
  date: any;
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
    private firebaseService: FirebaseService,
    private searchService: SearchService,
    private formBuilder: FormBuilder
  ) {
    this.firebaseService.getAddress().subscribe(data => {
      this.addressList = data;
      console.log(data);
    });
    this.firebaseService.getCart().subscribe(carts => {
      this.carts = carts;
      this.totalLength = carts.length;
    });
  }
  movies: any;

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      userName: ["", [Validators.required]],
      userEmail: ["", [Validators.required]],
      userPhone: ["", [Validators.required]],
      usercity: ["", [Validators.required]],
      userAddress: ["", [Validators.required]],
      userAddType: ["", [Validators.required]]
    });

    setTimeout(() => {
      this.totalQuant();
    }, 6000);
    setTimeout(() => {
      this.getVolume();
    }, 7000);
  }

  getLatLng(address) {
    this.searchService.getLatLng(address).subscribe(data => {
      this.movies = data["latLng"];
    });
  }

  totalQuant() {
    let totalQuantity = 0;

    for (let i = 0; i < this.totalLength; i++) {
      totalQuantity += this.carts[i].quantity;
    }
    console.log(totalQuantity);
    return totalQuantity;
  }

  getSum() {
    let sum = 0;

    for (let i = 0; i < this.totalLength; i++) {
      sum += this.carts[i].totalPrice;
    }
    return sum;
  }

  getVolume() {
    let volumeTotal = 0;
    for (let i = 0; i < this.totalLength; i++) {
      volumeTotal += this.carts[i].totalVolume;
    }
    localStorage.setItem("totalVolume", volumeTotal.toString());

    return volumeTotal;
  }
  onSubmit() {
    for (let i = 0; i < this.addressList.length; i++) {
      if (
        this.addressList[i].name === this.firstname &&
        this.addressList[i].address === this.address
      ) {
     
        this.bool = false;
      }
    }
    setTimeout(() => {}, 3000);
    this.add.name = this.firstname;
    this.add.phone = this.phoneNumb;
    this.add.address = this.address;
    this.add.city = this.city;
    this.add.email = this.usermail;
    this.searchService.getLatLng(this.address).subscribe(data => {
      setTimeout(() => {
        this.add.addLat = data["lat"];
        this.add.addLng = data["lng"];
      }, 1000);
    });
    setTimeout(() => {
      this.addAddress();
    }, 5000);
  }

  addAddress() {
     if (this.bool) {
      this.firebaseService.addAddress(this.add);
    }
  }

  getdetails() {
    this.firebaseService.addAddress(this.add);
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
  get usercity() {
    return this.userForm.get("usercity");
  }
  get userAddress() {
    return this.userForm.get("userAddress");
  }
}
