import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaretakerComponent } from './caretaker.component';

describe('CaretakerComponent', () => {
  let component: CaretakerComponent;
  let fixture: ComponentFixture<CaretakerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaretakerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaretakerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
