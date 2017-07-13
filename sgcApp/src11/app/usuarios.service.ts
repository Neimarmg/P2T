import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs/RX'
import { Usuario } from "app/usuario";

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class UsuariosService {
  usuarios: Usuario[] = []; 
  urlResource = "http://localhost:8080/CrudUsuarios/api/usuarios";

  constructor(private http: Http) { 
  }

  getUsuarios() : Observable<Usuario[]>{
    return this.http.get(this.urlResource)
      .map((res:Response)=>res.json())
      .catch((error:any)=>Observable.throw(error));

  }

  addUsuario(usuario):Observable<Usuario>{
    let bodyString = JSON.stringify(usuario);
    console.log(bodyString);
    let headers = new Headers({'Content-Type':'application/json'})
    let options = new RequestOptions({headers:headers});
    return this.http.post(this.urlResource,
        bodyString, options)
        .map((res:Response)=>{})
        .catch((error:any)=>Observable.throw(error));
    
  }

  excluirUsuario(indice:number){
    this.usuarios.splice(indice,1);
  }

  getUsuario(indice:number){
    return(this.usuarios[indice]);
  }

  atualizarUsuario(indice:number, usuario:Usuario){
    this.usuarios[indice] = usuario;
  }
}
