import { ActivatedRoute } from "@angular/router";
import { FirebaseService, Address, Cart } from "./../firebase.service";
import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  FormGroup,
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
  isSubmitted = true;
  add: Address = {
    address: "",
    email: "",
    phone: 1,
    name: ""
  };
  usAdd: any;
  carts: Cart[];
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
    private formBuilder: FormBuilder
  ) {}

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      userName: ["", [Validators.required]],
      userEmail: ["", [Validators.required]],
      userPhone: ["", [Validators.required]],
      userAddress: ["", [Validators.required]]
    });

    this.firebase.getAddress().subscribe(carts => {
      this.usAdd = carts;
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

  onSubmit() {
    // console.log(this.userName.value);
    if (this.userName.value == null) {
      return;
    } else {
      this.add.name = this.userName.value;
      this.add.phone = this.userPhone.value;
      this.add.address = this.userAddress.value;
      this.add.email = this.userEmail.value;
      this.firebase.addAddress(this.add);
    }
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
  get userAddress() {
    return this.userForm.get("userAddress");
  }
  getErrorMessage() {
    return this.userEmail.hasError("required")
      ? "You must enter a value"
      : this.userEmail.hasError("userEmail")
        ? "Not a valid email"
        : "";
  }
}
