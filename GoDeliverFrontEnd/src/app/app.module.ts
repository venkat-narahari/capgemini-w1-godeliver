import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { RouterModule, Routes } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { AppComponent } from "./app.component";
import { RegistrationComponent } from "./registration/registration.component";
import { HomeComponent } from "./home/home.component";
import { ReactiveFormsModule } from "@angular/forms";
import { UserDetailsService } from "./user-details.service";
import { HeaderComponent } from "./header/header.component";
import { FooterComponent } from "./footer/footer.component";
import { LoginComponent } from "./login/login.component";
import { RoutingModule } from "./routing/routing.module";
import { MultipleCheckboxesModule } from 'multiple-checkboxes';
import { RecommendationsComponent } from './recommendations/recommendations.component';
import { WishlistComponent } from './wishlist/wishlist.component';
@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RecommendationsComponent,
    WishlistComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RoutingModule, MultipleCheckboxesModule, 
  ],
  providers: [UserDetailsService],
  bootstrap: [AppComponent]
})
export class AppModule {}
