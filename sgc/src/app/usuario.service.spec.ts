import { TestBed, inject } from '@angular/core/testing';

import { UsuarioService } from 'app/cadastros/Usuarios/usuario.service';

describe('UsuarioService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UsuarioService]
    });
  });

  it('should ...', inject([UsuarioService], (service: UsuarioService) => {
    expect(service).toBeTruthy();
  }));
});
