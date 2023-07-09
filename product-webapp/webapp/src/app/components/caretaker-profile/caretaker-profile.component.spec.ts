import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaretakerProfileComponent } from './caretaker-profile.component';

describe('CaretakerProfileComponent', () => {
  let component: CaretakerProfileComponent;
  let fixture: ComponentFixture<CaretakerProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaretakerProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaretakerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
