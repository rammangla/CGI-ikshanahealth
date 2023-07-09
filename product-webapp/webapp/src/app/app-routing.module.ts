import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { CaretakerComponent } from './components/caretaker/caretaker.component';
import { ContactComponent } from './components/contact/contact.component';
import { DoctorComponent } from './components/doctor/doctor.component';
import { HomeComponent } from './components/home/home.component';
import { LocationComponent } from './components/location/location.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { ServiceTypeComponent } from './components/service-type/service-type.component';
import { SearchComponent } from './components/search/search.component';
import { DoctorProfileComponent } from './components/doctor-profile/doctor-profile.component';
import { DoctorDashboardComponent } from './components/doctor-dashboard/doctor-dashboard.component';
import { BookAppointmentComponent } from './components/book-appointment/book-appointment.component';
import { DoctorProfileviewComponent } from './components/doctor-profileview/doctor-profileview.component';
import { CaretakerDashboardComponent } from './components/caretaker-dashboard/caretaker-dashboard.component';
import { CaretakerProfileComponent } from './components/caretaker-profile/caretaker-profile.component';
import { CaretakerDataComponent } from './components/caretaker-data/caretaker-data.component';
import { NewsComponent } from './components/news/news.component';
import { Pay2Component } from './components/pay2/pay2.component';
import { ChatComponent } from './components/chat/chat.component';
import { UserDoctorProfileViewComponent } from './components/user-doctor-profile-view/user-doctor-profile-view.component';
import { CaretakerSlotComponent } from './components/caretaker-slot/caretaker-slot.component';
import { UserCaretakerProfileViewComponent } from './components/user-caretaker-profile-view/user-caretaker-profile-view.component';
import { BookCaretakerComponent } from './components/book-caretaker/book-caretaker.component';
import { PaymentSuccessfulComponent } from './components/payment-successful/payment-successful.component';
import { RecommendationComponent } from './components/recommendation/recommendation.component';
import { PaymentFailedComponent } from './components/payment-failed/payment-failed.component';
import { ViewdoctorsComponent } from './components/viewdoctors/viewdoctors.component';
import { ViewcaretakersComponent } from './components/viewcaretakers/viewcaretakers.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'about', component: AboutComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'location', component: LocationComponent},
  {path: 'service-type', component: ServiceTypeComponent},
  {path: 'search', component: SearchComponent},
  {path: 'chat/:name/:room', component: ChatComponent},
  {path: 'doctor', component: DoctorComponent},
  {path: 'caretaker', component: CaretakerComponent},
  {path: 'location', component: LocationComponent},
  {path: 'login' , component: LoginComponent},
  {path: 'user-dashboard' , component: UserDashboardComponent},
  {path: 'user-profile' , component: UserProfileComponent},
  {path: 'book-appointment' , component: BookAppointmentComponent},
  {path: 'doctorprofile', component: DoctorProfileComponent},
  {path: 'doctordashboard', component:DoctorDashboardComponent},
  {path: 'doctorprofileview' , component:DoctorProfileviewComponent},
  {path: 'caretakerdashboard', component:CaretakerDashboardComponent},
  {path: 'caretakerprofile', component:CaretakerProfileComponent},
  {path: 'caretakerdata',component:CaretakerDataComponent},
  {path: 'news', component: NewsComponent},
  {path: 'payment', component: Pay2Component},
  {path:'userDoctorProfileView/:emailId', component: UserDoctorProfileViewComponent},
  {path:'book-appointment',component :BookAppointmentComponent},
  {path:'caretaker-slot',component :CaretakerSlotComponent},
  {path:'book-caretaker',component :BookCaretakerComponent},
  {path:'userCaretakerProfileView/:emailId',component:UserCaretakerProfileViewComponent},
  {path:'paymentsuccessfull',component:PaymentSuccessfulComponent},
  {path: 'recommendations', component: RecommendationComponent},
  {path:'paymentfailed',component:PaymentFailedComponent},
  {path: 'viewdoctors', component: ViewdoctorsComponent},
  {path: 'viewcaretakers', component: ViewcaretakersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
