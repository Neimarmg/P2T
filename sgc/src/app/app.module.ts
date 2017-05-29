import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { TabelaUsuariosComponent } from './tabela-usuarios/tabela-usuarios.component';

import { UsuarioService } from "app/usuario.service";
import { FormUsuariosComponent } from './form-usuarios/form-usuarios.component';
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "app/Componentes-globais/home/home.component";
import { MenuGlobalComponent } from './Componentes-globais/menu-global/menu-global.component';

import { PessoasComponent} from './cadastros/pessoas/pessoas.component';


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
    PessoasComponent

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
