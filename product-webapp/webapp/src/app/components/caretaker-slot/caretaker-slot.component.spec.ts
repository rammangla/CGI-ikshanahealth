import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaretakerSlotComponent } from './caretaker-slot.component';

describe('CaretakerSlotComponent', () => {
  let component: CaretakerSlotComponent;
  let fixture: ComponentFixture<CaretakerSlotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaretakerSlotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaretakerSlotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
