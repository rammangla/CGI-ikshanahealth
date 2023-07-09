import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewcaretakersComponent } from './viewcaretakers.component';

describe('ViewcaretakersComponent', () => {
  let component: ViewcaretakersComponent;
  let fixture: ComponentFixture<ViewcaretakersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewcaretakersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewcaretakersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
