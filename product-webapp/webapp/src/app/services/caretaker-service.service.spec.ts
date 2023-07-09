import { TestBed } from '@angular/core/testing';

import { CaretakerServiceService } from './caretaker-service.service';

describe('CaretakerServiceService', () => {
  let service: CaretakerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CaretakerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
