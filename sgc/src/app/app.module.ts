import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from 'app/app.component';


import { UsuarioService } from './cadastros/usuarios/usuario.service';
import { FormUsuariosComponent } from './cadastros/usuarios/form-usuarios/form-usuarios.component';
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "app/Componentes-globais/home/home.component";
import { MenuGlobalComponent } from './Componentes-globais/menu-global/menu-global.component';

import { PessoasComponent} from './cadastros/pessoas/form/pessoas.component';
import { TabPessoaComponent } from './cadastros/pessoas/tabelas/tab-pessoa.component';
import { TabelaUsuariosComponent} from './cadastros/Usuarios/tabela-usuarios/tabela-usuarios.component';

const rotas:Routes=[
  { path:'lista', component:TabelaUsuariosComponent },
  { path:'novo', component:FormUsuariosComponent },
  { path:'edicao/:ind', component:FormUsuariosComponent },

  { path:'', component:HomeComponent },

  { path:'pessoa', component:PessoasComponent}
  
];

@NgModule({
  declarations: [
    AppComponent,
    TabelaUsuariosComponent,
    FormUsuariosComponent,
    HomeComponent,
    MenuGlobalComponent,
    PessoasComponent,
    TabPessoaComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(rotas)  
      
  ],
  providers: [UsuarioService],
  bootstrap: [AppComponent]
})
export class AppModule { 


}
