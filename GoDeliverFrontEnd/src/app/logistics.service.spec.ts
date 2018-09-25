import { TestBed, inject } from '@angular/core/testing';

import { LogisticService } from './logistics.service';

describe('LogisticsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LogisticService]
    });
  });

  it('should be created', inject([LogisticService], (service: LogisticService) => {
    expect(service).toBeTruthy();
  }));
});
