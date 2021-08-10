import { TestBed } from '@angular/core/testing';

import { MolenService } from './molen.service';

describe('MolenService', () => {
  let service: MolenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MolenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
