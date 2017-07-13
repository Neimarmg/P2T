import { TestBed, inject } from '@angular/core/testing';

import { UsuariosServiceService } from './usuarios.service';

describe('UsuariosServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UsuariosServiceService]
    });
  });

  it('should ...', inject([UsuariosServiceService], (service: UsuariosServiceService) => {
    expect(service).toBeTruthy();
  }));
});
