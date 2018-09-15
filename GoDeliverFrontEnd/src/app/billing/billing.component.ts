import { LogisticService } from "./../logistics.service";
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
import { SearchService } from "../search.service";
@Component({
  selector: "app-billing",
  templateUrl: "./billing.component.html",
  styleUrls: ["./billing.component.css"]
})
export class BillingComponent implements OnInit {
  orders: any;

  email = new FormControl("", [Validators.required, Validators.email]);

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
    private firebase: FirebaseService,
    private route: ActivatedRoute,
    private searchService: SearchService,
    private formBuilder: FormBuilder,
    private logistic: LogisticService
  ) {}
  movies: any;
  getMovie(address) {
    this.searchService.getLatLng(address).subscribe(data => {
      this.movies = data["latLng"];
    });
  }

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      userName: ["", [Validators.required]],
      userEmail: ["", [Validators.required]],
      userPhone: ["", [Validators.required]],
      usercity: ["", [Validators.required]],
      userAddress: ["", [Validators.required]],
      userAddType: ["", [Validators.required]]
    });
    this.firebase.getAddress().subscribe(carts => {
      this.addressList = carts;
    });
    this.firebase.getCart().subscribe(carts => {
      this.carts = carts;
      this.totalLength = carts.length;
    });
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

  getVolume() {
    let volumeTotal = 0;
    for (let i = 0; i < this.totalLength; i++) {
      volumeTotal += this.carts[i].totalVolume;
    }
    return volumeTotal;
  }
  onSubmit() {
    console.log(this.address);
    this.add.name = this.firstname;
    this.add.phone = this.phoneNumb;
    this.add.address = this.address;
    this.add.city = this.city;
    this.add.email = this.usermail;
    this.searchService.getLatLng(this.address).subscribe(data => {
      setTimeout(() => {
        this.add.addLat = data["lat"];
        console.log(this.add.addLat);
        this.add.addLng = data["lng"];
      }, 1);
    });
    this.firebase.addAddress(this.add);
  }
  getdetails() {
    this.firebase.addAddress(this.add);
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
