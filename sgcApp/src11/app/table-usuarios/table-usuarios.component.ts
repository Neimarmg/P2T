import { Component, OnInit } from '@angular/core';
import { Usuario } from "app/usuario";
import { UsuariosService } from "app/usuarios.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-table-usuarios',
  templateUrl: './table-usuarios.component.html',
  styleUrls: ['./table-usuarios.component.css']
})
export class TableUsuariosComponent implements OnInit {
  usuarios: Usuario[];
  erro: string;

  constructor(private servico: UsuariosService){}    
  
  ngOnInit() {
    
    this.carregarLista();
  }

  carregarLista(){
      this.servico.getUsuarios().subscribe(
        data => this.usuarios = data,
        error => this.erro = error
      );

  }

  excluirUsuario(indice:number){
    this.servico.excluirUsuario(indice);
  }

}
