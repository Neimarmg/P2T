import { Component, OnInit } from '@angular/core';
import { Usuario } from 'app/cadastros/Usuarios/usuario';
import { UsuarioService } from 'app/cadastros/Usuarios/usuario.service';
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-form-usuarios',
  templateUrl: './usuarios/form-usuarios.component.html',
  styleUrls: ['./usuarios/form-usuarios.component.css']
})

export class TabelaUsuariosComponent implements OnInit {
  usuario: Usuario = new Usuario();
  indice: number;

  constructor(private servico:UsuarioService,    
    private router: Router,
    private rota: ActivatedRoute) {    
  }


  ngOnInit() {

    this.indice = this.rota.snapshot.params['ind'];

    if(isNaN(this.indice)){
      this.usuario = new Usuario();    
    }
    else{
      this.usuario = Object.assign({},
          this.servico.getUsuario(this.indice));
    }
  }


  salvarUsuario(){
    if(isNaN(this.indice)){
      this.servico.adicionaUsuario(this.usuario);
      alert("Usuario "+this.usuario.nome+" adicionado com sucesso!");
      this.usuario = new Usuario();    
    }
    else{
      this.servico.atualizarUsuario(
        this.indice,
        this.usuario
      );
      alert("Usuario "+this.usuario.nome+" editado com sucesso!");
    }
    
    this.router.navigate(['/lista']);
  }


  voltar(){ 
    this.router.navigate(['/lista']);
  }
}
