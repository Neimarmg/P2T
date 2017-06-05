import { Injectable } from '@angular/core';
import { Pessoa } from './pessoa';

@Injectable()
export class PessoaService {

  pessoa: Pessoa[] = [
      {nome:"Neimar",telefone:"3322121130",email:"fulano@mail", cidade:"porto alegre"},
      {nome:"Neimar",telefone:"3322121130",email:"fulano@mail", cidade:"porto alegre"},
      {nome:"Neimar",telefone:"3322121130",email:"fulano@mail", cidade:"porto alegre"},

    ];

  constructor() {
    let pessoa = new Pessoa();
    pessoa.nome = 'Lorinho';
    pessoa.telefone = '12222222';
    pessoa.email = 'neimar@ddd';
    pessoa.cidade = 'Sapucaia';
    this.pessoa.push(pessoa);
  }

   getListaPessoa(){
     return this.pessoa;
   }

 adicionaPessoa(pessoa:Pessoa){
    this.pessoa.push(pessoa);
  }


  getPessoa(indice:number){
    return(this.pessoa[indice]);    
  }


  excluirPessoa(indice:number){
    this.pessoa.splice(indice,1);
  }


  atualizarPessoa(indice:number, pessoa:Pessoa){
    this.pessoa[indice] = pessoa;
  }
}
