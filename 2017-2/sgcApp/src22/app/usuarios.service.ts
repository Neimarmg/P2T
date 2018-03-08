import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs/RX'
import { Usuario } from "app/usuario";

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class UsuariosService {
  usuarios: Usuario[] = []; 
  urlResource = "http://localhost:8080/CrudUsuarios/api/usuario";

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

  excluirUsuario(usuario:Usuario):Observable<Usuario>{
    let url = this.urlResource+"/"+usuario.id;
    return this.http.delete(url)        
      .map((res:Response)=>{})
      .catch((error:any)=>Observable.throw(error));


    
  }

  getUsuario(id):Observable<Usuario>{
    let url = this.urlResource+"/"+id;
    return this.http.get(url)        
      .map((res:Response)=>res.json())
      .catch((error:any)=>Observable.throw(error));
  }

  atualizarUsuario(id:number, usuario:Usuario):Observable<Usuario>{
    let url = this.urlResource+"/"+id;
    let bodyString = JSON.stringify(usuario);
    console.log(bodyString);
    let headers = new Headers({'Content-Type':'application/json'})
    let options = new RequestOptions({headers:headers});
    return this.http.put(url,
        bodyString, options)
        .map((res:Response)=>{})
        .catch((error:any)=>Observable.throw(error));
  }
}
