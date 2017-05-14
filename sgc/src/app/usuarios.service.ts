import { Injectable } from '@angular/core';
import { Usuario } from "app/usuario";

@Injectable()
export class UsuarioService {
  
  usuarios: Usuario[] = [
    {nome:"Fulano",email:"fulano@mail",username:"fulano"},
    {nome:"Sicrano",email:"sicrano@mail",username:"sicrano"}
  ];

  constructor() { 
    let usuario = new Usuario();
    usuario.nome="Beltrano";
    usuario.email="beltrano@mail.com";
    usuario.username="belt";    
    this.usuarios.push(usuario);
  }

  getListaUsuarios(){
    return this.usuarios;
  }

}
