import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaretakerDashboardComponent } from './caretaker-dashboard.component';

describe('CaretakerDashboardComponent', () => {
  let component: CaretakerDashboardComponent;
  let fixture: ComponentFixture<CaretakerDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaretakerDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaretakerDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
