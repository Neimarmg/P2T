/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;
import Model.TipoUtilitarios;
import java.util.ArrayList;
import javax.ejb.Stateless;
/**
 *
 * @author neimarmoises
 */
@Stateless
public class TipoUtilitarioService {
    private List<TipoUtilitarios> listaTipoUtilitarios;
    
    public TipoUtilitarioService(){
        listaTipoUtilitarios = new ArrayList<>();
        listaTipoUtilitarios.add(new TipoUtilitarios(1, "teste"));
    }

   
    public List<TipoUtilitarios> listar(){
        return listaTipoUtilitarios;
    }

     
    public int getIndice(int cod){
        for (int i = 0; i < listaTipoUtilitarios.size(); i++) {
            if (listaTipoUtilitarios.get(i).getCodTipoUtilitario() == cod) {
                return i;
            }
        }
        return  -1;
    }
    
    
    public TipoUtilitarios buscarPorCodigo(int cod){
        for (TipoUtilitarios tu : listaTipoUtilitarios) {
            if (tu.getCodTipoUtilitario() == cod){
                return new TipoUtilitarios(tu.getCodTipoUtilitario(),tu.getDescTipoUtilitario());
            }
        }
        return null;
    }       
 
    
    public void insere(TipoUtilitarios tipoUtilitario){
        listaTipoUtilitarios.add(tipoUtilitario);
    }
    
    
    public void atualiza(TipoUtilitarios tipoUtilitario){
        listaTipoUtilitarios.set(this.getIndice(tipoUtilitario.getCodTipoUtilitario()),tipoUtilitario);
    }
    
       
    public void exclui(TipoUtilitarios tipoUtilitarios){
        System.out.println(tipoUtilitarios.getCodTipoUtilitario());
        System.out.println(this.getIndice(tipoUtilitarios.getCodTipoUtilitario()));
        listaTipoUtilitarios.remove(this.getIndice(tipoUtilitarios.getCodTipoUtilitario()));
    }
}
