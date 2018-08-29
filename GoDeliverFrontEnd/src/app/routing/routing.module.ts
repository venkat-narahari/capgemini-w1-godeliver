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

const appRoutes: Routes = [
  { path: "", component: HomeComponent },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegistrationComponent },
  { path: "wishlist", component: WishlistComponent },
  { path: "orders", component: OrdersComponent },
  { path: "profile", component: ProfileComponent },
  { path: "about", component: AboutusComponent },
  { path: "return", component: ReturnpolicyComponent },
  { path: "servicepage", component: ServicepageComponent },
  { path: "cart", component: CartComponent },
  { path: "book/:bookISBN", component: BookComponent },
  { path: "book", component: BookComponent },
];

@NgModule({
  imports: [CommonModule, RouterModule.forRoot(appRoutes)],
  exports: [CommonModule, RouterModule],
  declarations: []
})
export class RoutingModule {}
