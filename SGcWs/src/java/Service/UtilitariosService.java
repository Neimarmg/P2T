/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Utilitarios;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author neimarmoises
 */
@Stateless
public class UtilitariosService {
    private List<Utilitarios> listaUtilitarios;
    
    public UtilitariosService(){
        listaUtilitarios = new ArrayList<>();
    }
 
    
    public List<Utilitarios> listar(){
        return listaUtilitarios;
    }

     
    public int getIndice(int cod){
        for (int i = 0; i < listaUtilitarios.size(); i++) {
            if (listaUtilitarios.get(i).getCodUtilitario()== cod) {
                return i;
            }
        }
        return  -1;
    }
    
    
    public Utilitarios buscarPorCodigo(int cod){
        for (Utilitarios tu : listaUtilitarios) {
            if (tu.getCodTipoUtilitario() == cod){
                return new Utilitarios(tu.getCodUtilitario(), tu.getDescUtilitario());
            }
        }
        return null;
    }       
 
    
    public void insere(Utilitarios tipoUtilitario){
        listaUtilitarios.add(tipoUtilitario);
    }
    
    
    public void atualiza(Utilitarios utilitario){
          listaUtilitarios.set(this.getIndice(utilitario.getCodTipoUtilitario()),utilitario);
    }
    
       
    public void exclui(Utilitarios utilitario){
        listaUtilitarios.remove(this.getIndice(utilitario.getCodUtilitario()));
    }
}
