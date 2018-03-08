import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";

import { PessoaService } from '../pessoa.service';
import { Pessoa } from 'app/cadastros/pessoas/pessoa';

@Component({
  selector: 'app-form-pessoa',
  templateUrl: './form-pesssoa.component.html',
  styleUrls: ['./form-pesssoa.component.css']
})

export class FormPessoasComponent implements OnInit {
  pessoa: Pessoa = new Pessoa();
  indice: number;

  constructor(private servico:PessoaService,    
    private router: Router,
    private rota: ActivatedRoute) {    
  }


  ngOnInit() {

    this.indice = this.rota.snapshot.params['ind'];

    if(isNaN(this.indice)){
      this.pessoa = new Pessoa();    
   
    }else{
      this.pessoa = Object.assign({},
          this.servico.getPessoa(this.indice));
    }
  }


  salvarUsuario(){
    if(isNaN(this.indice)){
      this.servico.adicionaPessoa(this.pessoa);
      alert("Pessoa "+this.pessoa.nome+" adicionado com sucesso!");
      this.pessoa = new Pessoa();    
    }
    else{
      this.servico.atualizarPessoa(
        this.indice,
        this.pessoa
      );
      alert("Pessoa "+this.pessoa.nome+" editado com sucesso!");
    }
    
    this.router.navigate(['/listaPessoa']);
  }


  voltar(){ 
    this.router.navigate(['/listaPessoa']);
  }
}
