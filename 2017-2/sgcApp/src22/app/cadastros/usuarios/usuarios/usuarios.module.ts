import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TabelaUsuariosComponent } from '../tabela-usuarios/tabela-usuarios.component';
import { FormUsuariosComponent } from '../form-usuarios/form-usuarios.component';
import { Routes, RouterModule } from '@angular/router';




const usuarioRouters:Routes=[
  { path:'lista', component:TabelaUsuariosComponent },
  { path:'novo', component:FormUsuariosComponent },
  { path:'edicao/:ind', component:FormUsuariosComponent }  
  
]


;


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(usuarioRouters),
  ],
  declarations: []
})
export class UsuariosRoutersModule { }
