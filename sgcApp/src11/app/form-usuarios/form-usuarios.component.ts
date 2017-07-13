import { Component, OnInit } from '@angular/core';
import { Usuario } from "app/usuario";
import { UsuariosService } from "app/usuarios.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-form-usuarios',
  templateUrl: './form-usuarios.component.html',
  styleUrls: ['./form-usuarios.component.css']
})
export class FormUsuariosComponent implements OnInit {
  usuario:Usuario;
  erro: string;
  indice: number;
  constructor(private servico: UsuariosService ,
              private router: Router,
              private rota:ActivatedRoute) { 
  }

  ngOnInit() {
    //this.indice = this.rota.snapshot.params['ind'];

    //if(isNaN(this.indice)){
      this.usuario = new Usuario();    
    /*}
    else{
      this.usuario = Object.assign({},
          this.servico.getUsuario(this.indice));
    }*/

    
  }

  salvar(){
    //if(isNaN(this.indice)){
      console.log(this.usuario);
      this.servico.addUsuario(this.usuario).
        subscribe(
          usuario => this.router.navigate(['/lista']),
          error => this.erro = error
        );


      //this.usuario = new Usuario();    
   // }
    /*else{
      this.servico.atualizarUsuario(
        this.indice,
        this.usuario
      );
    }*/
        
  }

}
