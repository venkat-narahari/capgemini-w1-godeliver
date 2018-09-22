import { TimeslotsComponent } from './../timeslots /timeslots.component';
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


const appRoutes: Routes = [
  { path: "", component: HomeComponent },
  { path: "login", component: LoginComponent, canActivate: [LoginGuard] },
  {
    path: "register",
    component: RegistrationComponent,
    canActivate: [LoginGuard]
  },
  { path: "wishlist", component: WishlistComponent, canActivate: [AuthGuard] },
  { path: "orders", component: OrdersComponent, canActivate: [AuthGuard] },
  { path: "profile", component: ProfileComponent, canActivate: [AuthGuard] },
  { path: "about", component: AboutusComponent },
  { path: "return", component: ReturnpolicyComponent },
  { path: "servicepage", component: ServicepageComponent },
  { path: "cart", component: CartComponent },
  { path: "book/:bookISBN", component: BookComponent },
  { path: "book", component: BookComponent },
  { path: "billing", component: BillingComponent },
  { path: "allbooks", component: AllComponent },
  { path: "deleteaccount", component: DeleteaccountComponent },
  { path: "settings", component: SettingsComponent },
  { path: "timeslots", component: TimeslotsComponent },
  { path: "admin", component: AdminComponent },
  { path:"payment", component: PaymentComponent},
  { path:"toprated", component: TopratedComponent},
  { path:"topselling", component: TopsellingComponent},
  { path: "order/:orderid", component: OrdersComponent },
];


@NgModule({
  imports: [CommonModule, RouterModule.forRoot(appRoutes)],
  exports: [CommonModule, RouterModule],
  declarations: []
})
export class RoutingModule {}
