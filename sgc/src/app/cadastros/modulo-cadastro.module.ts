import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaService } from "app/cadastros/pessoas/pessoa.service";

@NgModule({
  imports: [
    CommonModule
  ],

  
  declarations: [PessoaService]
})
export class ModuloCadastroModule { }
