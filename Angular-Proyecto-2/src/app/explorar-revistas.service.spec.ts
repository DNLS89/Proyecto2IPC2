import { TestBed } from '@angular/core/testing';

import { ExplorarRevistasService } from '../services/explorar-revistas.service';

describe('ExplorarRevistasService', () => {
  let service: ExplorarRevistasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExplorarRevistasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
