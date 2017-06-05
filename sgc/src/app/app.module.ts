import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';

import { TabelaUsuariosComponent } from './cadastros/usuarios/tabela-usuarios/tabela-usuarios.component';

import { UsuarioService } from "app/cadastros/usuarios/usuario.service";
import { FormUsuariosComponent } from './cadastros/usuarios/form-usuarios/form-usuarios.component';
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "app/Componentes-globais/home/home.component";
import { MenuGlobalComponent } from './Componentes-globais/menu-global/menu-global.component';

import { PessoasComponent } from './cadastros/pessoas/form-pessoas/form-pessoas.component';
import { FormPrincipalComponent } from './Componentes-globais/form-principal/form-principal.component';
import { RodapeComponent } from './Componentes-globais/rodape/rodape.component';
import { TabelaPessoaComponent } from "app/cadastros/pessoas/tabela-pessoa/tabela-pessoa.component";
import { PessoaService } from './cadastros/pessoas/pessoa.service';
import { CaixaTabelaComponent } from './Operacoes/caixa-tabela/caixa-tabela.component';
import { CaixaFormComponent } from './Operacoes/caixa-form/caixa-form.component';
import { ContratosTabelaComponent } from './cadastros/Contratos/contratos-tabela/contratos-tabela.component';
import { ContratosFormComponent } from './cadastros/Contratos/contratos-form/contratos-form.component';
import { UtilitarioFormComponent } from './cadastros/Utilitarios/utilitario-form/utilitario-form.component';
import { UtilitarioTabelaComponent } from './cadastros/Utilitarios/utilitario-tabela/utilitario-tabela.component';



const rotas:Routes=[
  { path: 'formPrincipal', component: FormPrincipalComponent } ,
  { path:'', component:HomeComponent },
  
  { path:'lista', component:TabelaUsuariosComponent },
  { path:'novo', component:FormUsuariosComponent },
  { path:'edicao/:ind', component:FormUsuariosComponent },
  
  { path:'novoPessoa', component:PessoasComponent},
  { path:'editarPessoa/:ind', component:PessoasComponent},
  { path:'listaPessoa', component:TabelaPessoaComponent}, 

];

@NgModule({
  declarations: [
    AppComponent,
    TabelaUsuariosComponent,
    FormUsuariosComponent,
    HomeComponent,
    MenuGlobalComponent,
    PessoasComponent,
    FormPrincipalComponent,
    RodapeComponent,
    TabelaPessoaComponent,
    PessoasComponent,
    CaixaTabelaComponent,
    CaixaFormComponent,
    ContratosTabelaComponent,
    ContratosFormComponent,
    UtilitarioFormComponent,
    UtilitarioTabelaComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(rotas)  
      
  ],
  providers: [
      UsuarioService,
      PessoaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 
  

}
