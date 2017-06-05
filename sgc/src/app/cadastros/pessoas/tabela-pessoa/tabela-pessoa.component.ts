
import { Component, OnInit } from '@angular/core';
import { Pessoa } from "app/cadastros/pessoas/pessoa";
import { PessoaService } from "app/cadastros/pessoas/pessoa.service";


@Component({
  selector: 'app-tabela-pessoa',
  templateUrl: './tabela-pessoa.component.html',
  styleUrls: ['./tabela-pessoa.component.css']
})
export class TabelaPessoaComponent implements OnInit {
  pessoa:Pessoa[];
  constructor(private servico:PessoaService) { 

  }

  ngOnInit() {
    this.pessoa = this.servico.getListaPessoa();
  } 

   excluirPessoa(indice:number){
    this.servico.excluirPessoa(indice);
  }
}
