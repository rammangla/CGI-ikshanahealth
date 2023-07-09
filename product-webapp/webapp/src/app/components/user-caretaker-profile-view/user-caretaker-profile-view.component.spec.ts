import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCaretakerProfileViewComponent } from './user-caretaker-profile-view.component';

describe('UserCaretakerProfileViewComponent', () => {
  let component: UserCaretakerProfileViewComponent;
  let fixture: ComponentFixture<UserCaretakerProfileViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserCaretakerProfileViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserCaretakerProfileViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
