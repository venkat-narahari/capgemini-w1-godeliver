import { LoginComponent } from "./../login/login.component";
import { HomeComponent } from "./../home/home.component";

import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule, Routes } from "@angular/router";
import { RegistrationComponent } from "../registration/registration.component";

const appRoutes: Routes = [
  { path: "", component: HomeComponent },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegistrationComponent }
];

@NgModule({
  imports: [CommonModule, RouterModule.forRoot(appRoutes)],
  exports: [CommonModule, RouterModule],
  declarations: []
})
export class RoutingModule {}
