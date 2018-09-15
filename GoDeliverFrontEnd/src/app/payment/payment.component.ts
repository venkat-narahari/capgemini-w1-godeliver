import { Component, OnInit } from "@angular/core";
import { Http, Headers } from "@angular/http";

@Component({
  selector: "app-payment",
  templateUrl: "./payment.component.html",
  styleUrls: ["./payment.component.css"]
})
export class PaymentComponent implements OnInit {
  title = "payment-example";
  msg: string = null;
  msg1: string = null;
  cardNumber: string;
  expiryMonth: string;
  expiryYear: string;
  cvc: string;

  constructor(private http: Http) {}
  chargeCard(token: string) {
    const headers = new Headers({ token: token, amount: 50 });
    this.http
      .post("http://localhost:8080/payment/charge", {}, { headers: headers })
      .subscribe(resp => {
        console.log(resp);
      });
  }

  refundCard() {
    this.http
      .post("http://localhost:8080/payment/refund", {})
      .subscribe(res => {
        console.log(res);
      });
  }

  chargeCreditCard() {
    (<any>window).Stripe.card.createToken(
      {
        number: this.cardNumber,
        exp_month: this.expiryMonth,
        exp_year: this.expiryYear,
        cvc: this.cvc
      },
      (status: number, response: any) => {
        if (status === 200) {
          const token = response.id;
          if (token != null) {
            this.chargeCard(token);
            console.log(token);
            this.msg = "Your Transaction is success";
          }

          if (token == null) {
            this.msg = "Payment failure! Plase Check Your Internet Connection";
          }
        } else {
          console.log(response.error.message);
          this.msg = "Payment Failure! Please Enter valid Credentials ";
        }
      }
    );
  }

  refundCreditCard() {
    this.refundCard();
  }

  ngOnInit() {}
}
