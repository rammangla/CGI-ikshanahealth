import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserUpdationComponent } from './user-updation.component';

describe('UserUpdationComponent', () => {
  let component: UserUpdationComponent;
  let fixture: ComponentFixture<UserUpdationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserUpdationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserUpdationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
