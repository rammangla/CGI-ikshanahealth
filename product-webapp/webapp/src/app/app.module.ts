import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatTabsModule} from '@angular/material/tabs';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatRadioModule} from '@angular/material/radio';
import {MatStepperModule} from '@angular/material/stepper';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatCarouselModule } from '@ngmodule/material-carousel';
import { MatSnackBarModule } from '@angular/material/snack-bar';
// import { MatDialogModule } from '@angular/material/dialog';
//import { MatSliderModule } from '@angular/material/slider';
// import {FormGroup,  FormControl} from '@angular/forms';
 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { ContactComponent } from './components/contact/contact.component';
import { LocationComponent } from './components/location/location.component';
import { RegisterComponent } from './components/register/register.component';
import { FooterComponent } from './components/footer/footer.component';
import { DoctorComponent } from './components/doctor/doctor.component';
import { HttpClientModule ,HTTP_INTERCEPTORS} from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatSliderModule } from '@angular/material/slider';
import { ServiceTypeComponent } from './components/service-type/service-type.component';
import { CaretakerComponent } from './components/caretaker/caretaker.component';
import { LoginComponent } from './components/login/login.component';
import { DoctorProfileComponent } from './components/doctor-profile/doctor-profile.component';
import { DoctorDashboardComponent } from './components/doctor-dashboard/doctor-dashboard.component';
import { MatDialogModule } from '@angular/material/dialog';
import { UserUpdationComponent } from './components/user-updation/user-updation.component';
import { CaretakerDashboardComponent } from './components/caretaker-dashboard/caretaker-dashboard.component';
import { CaretakerProfileComponent } from './components/caretaker-profile/caretaker-profile.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { DoctorProfileviewComponent } from './components/doctor-profileview/doctor-profileview.component';
import { BookAppointmentComponent } from './components/book-appointment/book-appointment.component';
import { CaretakerDataComponent } from './components/caretaker-data/caretaker-data.component';
import { NewsComponent } from './components/news/news.component';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { MatSidenavModule } from '@angular/material/sidenav';
import { Pay2Component } from './components/pay2/pay2.component';
import { SplitPipe } from './pipes/split.pipe';
import { ChatComponent } from './components/chat/chat.component';
import { UserDoctorProfileViewComponent } from './components/user-doctor-profile-view/user-doctor-profile-view.component';
import { CaretakerSlotComponent } from './components/caretaker-slot/caretaker-slot.component';
import { BookCaretakerComponent } from './components/book-caretaker/book-caretaker.component';
import { UserCaretakerProfileViewComponent } from './components/user-caretaker-profile-view/user-caretaker-profile-view.component';
import { PaymentSuccessfulComponent } from './components/payment-successful/payment-successful.component';
import { RecommendationComponent } from './components/recommendation/recommendation.component';
import { PaymentFailedComponent } from './components/payment-failed/payment-failed.component';
import { ViewdoctorsComponent } from './components/viewdoctors/viewdoctors.component';
import { ViewcaretakersComponent } from './components/viewcaretakers/viewcaretakers.component';
 
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    AboutComponent,
    ContactComponent,
    LocationComponent,
    FooterComponent,
    RegisterComponent,
    DoctorComponent,
    ServiceTypeComponent,
    UserUpdationComponent,
    UserDashboardComponent,
    UserProfileComponent,
    CaretakerComponent,
    LoginComponent,
    CaretakerDashboardComponent,
    CaretakerProfileComponent,
    UserDashboardComponent,
    UserProfileComponent,
    UserUpdationComponent,
    SearchComponent,
    DoctorProfileComponent,
    DoctorDashboardComponent,
    BookAppointmentComponent,
    DoctorProfileviewComponent,
    CaretakerDataComponent,
    NewsComponent,
    Pay2Component,
    SplitPipe,
    ChatComponent,
    UserDoctorProfileViewComponent,
    CaretakerSlotComponent,
    BookCaretakerComponent,
    UserCaretakerProfileViewComponent,
    PaymentSuccessfulComponent,
    RecommendationComponent,
    PaymentFailedComponent,
    ViewdoctorsComponent,
    ViewcaretakersComponent,
     
  ],
  entryComponents: [UserUpdationComponent,LocationComponent],
  imports: [
    BrowserModule,
    FormsModule,
    CommonModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatTabsModule,
    MatGridListModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatRadioModule,
    MatStepperModule,
    MatInputModule,
    MatTabsModule,
    MatButtonModule,
    HttpClientModule,
    FlexLayoutModule,
    MatSliderModule,
    MatCarouselModule.forRoot(),
    MatDialogModule,
    MatSidenavModule,
    MatSnackBarModule
  ],
  
  
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
