import { NgModule } from "@angular/core";
import {FormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser'
import { RouterModule, Routes } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { AppComponent } from "./app.component";
import { RegistrationComponent } from "./registration/registration.component";
import { HomeComponent } from './home/home.component';
import { ReactiveFormsModule } from '@angular/forms';
import {UserDetailsService} from './user-details.service';


const appRoutes: Routes = [
  { path: '', component: RegistrationComponent },
  { path: 'home', component: HomeComponent }];

@NgModule({
  declarations: [AppComponent, RegistrationComponent, HomeComponent],
  imports: [BrowserModule, FormsModule, HttpClientModule, ReactiveFormsModule, 
  RouterModule.forRoot(appRoutes)],
  providers: [UserDetailsService],
  bootstrap: [AppComponent]
})
export class AppModule {}
