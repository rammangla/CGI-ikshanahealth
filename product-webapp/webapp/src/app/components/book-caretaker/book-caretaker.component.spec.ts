import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookCaretakerComponent } from './book-caretaker.component';

describe('BookCaretakerComponent', () => {
  let component: BookCaretakerComponent;
  let fixture: ComponentFixture<BookCaretakerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookCaretakerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookCaretakerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
