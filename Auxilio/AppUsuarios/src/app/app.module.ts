import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { TableUsuariosComponent } from './table-usuarios/table-usuarios.component';
import { FormUsuariosComponent } from './form-usuarios/form-usuarios.component';
import { UsuariosService } from './usuarios.service';
import { RouterModule, Routes } from '@angular/router';

const appRotas: Routes = [
  {path: 'novo', component:FormUsuariosComponent},
  {path: 'edicao/:id', component:FormUsuariosComponent},
  {path: 'lista', component:TableUsuariosComponent},
  {path: '', redirectTo:'lista',pathMatch:'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    TableUsuariosComponent,
    FormUsuariosComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRotas)
  ],
  providers: [UsuariosService],
  bootstrap: [AppComponent]
})
export class AppModule { }
