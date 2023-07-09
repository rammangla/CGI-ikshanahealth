import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorProfileviewComponent } from './doctor-profileview.component';

describe('DoctorProfileviewComponent', () => {
  let component: DoctorProfileviewComponent;
  let fixture: ComponentFixture<DoctorProfileviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorProfileviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorProfileviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
