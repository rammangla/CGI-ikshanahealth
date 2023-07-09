import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaretakerDataComponent } from './caretaker-data.component';

describe('CaretakerDataComponent', () => {
  let component: CaretakerDataComponent;
  let fixture: ComponentFixture<CaretakerDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaretakerDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaretakerDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
