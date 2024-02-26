/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ExternosService } from './externos.service';

describe('Service: Externos', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExternosService]
    });
  });

  it('should ...', inject([ExternosService], (service: ExternosService) => {
    expect(service).toBeTruthy();
  }));
});
