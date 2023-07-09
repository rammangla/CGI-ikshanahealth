import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDoctorProfileViewComponent } from './user-doctor-profile-view.component';

describe('UserDoctorProfileViewComponent', () => {
  let component: UserDoctorProfileViewComponent;
  let fixture: ComponentFixture<UserDoctorProfileViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDoctorProfileViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDoctorProfileViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
