import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuariosComponent } from './usuarios.component';
import { TabelaUsuariosComponent } from './tabela-usuarios/tabela-usuarios.component';

@NgModule({
  imports: [
    CommonModule
  ],
  exports :[TabelaUsuariosComponent],
  declarations: [UsuariosComponent, TabelaUsuariosComponent]
})
export class UsuariosModule { 


  
}
