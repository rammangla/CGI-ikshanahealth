import { TestBed } from '@angular/core/testing';

import { DateCaretakerStorageService } from './date-caretaker-storage.service';

describe('DateCaretakerStorageService', () => {
  let service: DateCaretakerStorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DateCaretakerStorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
