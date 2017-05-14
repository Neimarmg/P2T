import { Component, OnInit } from '@angular/core';
import { UsuarioService } from "app/usuarios.service";
import { Usuario } from "app/usuario";

@Component({
  selector: 'app-tabela-usuarios',
  templateUrl: './tabela-usuarios.component.html',
  styleUrls: ['./tabela-usuarios.component.css']
})
export class TabelaUsuariosComponent implements OnInit {
  usuarios:Usuario[];
  constructor(private servico: UsuarioService) {  
  
}

  ngOnInit() {
      this.usuarios = this.servico.getListaUsuarios();  

  }

}
