import { TimeslotsComponent } from "./../timeslots /timeslots.component";
import { BillingComponent } from "./../billing/billing.component";
import { LoginComponent } from "./../login/login.component";
import { HomeComponent } from "./../home/home.component";

import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule, Routes } from "@angular/router";
import { RegistrationComponent } from "../registration/registration.component";
import { WishlistComponent } from "../wishlist/wishlist.component";
import { OrdersComponent } from "../orders/orders.component";
import { ProfileComponent } from "../profile/profile.component";
import { AboutusComponent } from "../aboutus/aboutus.component";
import { ReturnpolicyComponent } from "../returnpolicy/returnpolicy.component";
import { ServicepageComponent } from "../servicepage/servicepage.component";
import { CartComponent } from "../cart/cart.component";
import { BookComponent } from "../book/book.component";
import { AllComponent } from "../all/all.component";
import { AuthGuard } from "../auth.guard";
import { LoginGuard } from "../login.guard";
import { DeleteaccountComponent } from "../deleteaccount/deleteaccount.component";
import { SettingsComponent } from "../settings/settings.component";
import { AdminComponent } from "../admin/admin.component";
import { PaymentComponent } from "../payment/payment.component";
import { TopratedComponent } from "../toprated/toprated.component";
import { TopsellingComponent } from "../topselling/topselling.component";
import { FirebaseService } from "../firebase.service";
import { LogisticService } from "../logistics.service";
import { HeaderComponent } from "../header/header.component";
import { AdminGuard } from "../admin.guard";

const appRoutes: Routes = [
  {
    path: "",
    component: HomeComponent,
    resolve: {
      cres: FirebaseService
    }
  },
  { path: "login", component: LoginComponent, canActivate: [LoginGuard] },
  {
    path: "register",
    component: RegistrationComponent,
    canActivate: [LoginGuard]
  },
  {
    path: "wishlist",
    component: WishlistComponent,
    canActivate: [AuthGuard],
    resolve: {
      cres: FirebaseService
    }
  },
  { path: "orders", component: OrdersComponent, canActivate: [AuthGuard] },
  { path: "profile", component: ProfileComponent, canActivate: [AuthGuard] },
  { path: "about", component: AboutusComponent },
  { path: "return", component: ReturnpolicyComponent },
  { path: "servicepage", component: ServicepageComponent },
  {
    path: "cart",
    component: CartComponent,
    resolve: {
      cres: FirebaseService
    }
  },
  { path: "book/:bookISBN", component: BookComponent },
  { path: "book", component: BookComponent },
  {
    path: "billing",
    component: BillingComponent,
    resolve: {
      cres: FirebaseService
    }
  },
  { path: "allbooks", component: AllComponent },
  { path: "deleteaccount", component: DeleteaccountComponent },
  { path: "settings", component: SettingsComponent },
  {
    path: "timeslots",
    component: TimeslotsComponent,
    resolve: {
      cres: FirebaseService
    }
  },
  { path: "admin", component: AdminComponent, canActivate:[AdminGuard] },
  { path: "payment", component: PaymentComponent },
  { path: "toprated", component: TopratedComponent },
  { path: "topselling", component: TopsellingComponent },
  {
    path: "order/:orderid",
    component: OrdersComponent,
    resolve: {
      cres: FirebaseService
    }
  }
];

@NgModule({
  imports: [CommonModule, RouterModule.forRoot(appRoutes)],
  exports: [CommonModule, RouterModule],
  declarations: []
})
export class RoutingModule {}
