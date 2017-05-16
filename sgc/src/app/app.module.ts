import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { TabelaUsuariosComponent } from './tabela-usuarios/tabela-usuarios.component';
import { UsuarioService } from "app/usuarios.service";
import { MenuGlobalComponent } from './c-globais/menu-global/menu-global.component';


@NgModule({
  declarations: [
    AppComponent,
    TabelaUsuariosComponent,
    MenuGlobalComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [UsuarioService],
  bootstrap: [AppComponent]
})
export class AppModule { }
