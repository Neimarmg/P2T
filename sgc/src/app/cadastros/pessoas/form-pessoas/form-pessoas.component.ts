import { PessoaService } from './../pessoa.service';
import { Pessoa } from 'app/cadastros/pessoas/pessoa';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-pessoas',
  templateUrl: './form-pessoas.component.html',
  styleUrls: ['./form-pessoas.component.css']
})

export class PessoasComponent implements OnInit {
  pessoa: Pessoa = new Pessoa();
  indice: number;
  
  constructor(
    private servico: PessoaService,
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

  salvarPessoa(){
    if(isNaN(this.indice)){
      this.servico.adicionaPessoa(this.pessoa);
      alert("Pessoa "+this.pessoa.nome+" Adicionado com sucesso!");
    
    }else{
      this.servico.atualizarPessoa(this.indice, this.pessoa);
      alert("Pessoa "+this.pessoa.nome+" Editado com sucesso!");
    }

    this.router.navigate(['/listaPessoa'])
  }

  cancelar(){
    this.router.navigate(['/ListaPessoa'])
  }

}