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
  idUsuario: number;
  constructor(private servico: UsuariosService ,
              private router: Router,
              private rota:ActivatedRoute) { 
  }

  ngOnInit() {
    this.idUsuario = this.rota.snapshot.params['id'];

    if(isNaN(this.idUsuario)){
      this.usuario = new Usuario();    
    }
    else{
      this.servico.getUsuario(this.idUsuario).subscribe(
        data => this.usuario = data,
        error => this.erro = error
      )
    }    
  }

  salvar(){
    if(isNaN(this.idUsuario)){
      console.log(this.usuario);
      this.servico.addUsuario(this.usuario).
        subscribe(
          usuario => this.router.navigate(['/lista']),
          error => this.erro = error
        );
    }

      //this.usuario = new Usuario();    
   // }
    else{
      this.servico.atualizarUsuario(this.idUsuario,this.usuario)
      .subscribe(
        data => this.router.navigate(['/lista']),
        error => this.erro = error
      );
    }
        
  }

}
