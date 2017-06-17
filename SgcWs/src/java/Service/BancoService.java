package Service;

import Model.Bancos;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 * @author neimarmoises
 */
@Stateless
public class BancoService {
    private List<Bancos> listaDeBancos; 

    
    public BancoService() {
        listaDeBancos = new ArrayList<>();
    }
    
    
    public List<Bancos> listar(){
        return listaDeBancos;
    }
    
    
    public Bancos buscarPorCodigo(int cod){
        for (Bancos b : listaDeBancos) {
            if (b.getCodBanco() == cod){
                return new Bancos(b.getCodBanco(), b.getNroBanco(), b.getDescricao(), b.getWebPage());
            }
        }
         return null;
    }
    
    
    private int getIndice(int codigo){
        for (int i = 0; i < listaDeBancos.size(); i++) {
            if (listaDeBancos.get(i).getCodBanco() == codigo){
                return i;
            }
        }
        return -1;     
    }
   
    
    public void insere(Bancos banco){
        listaDeBancos.add(banco);
    }
    
    
    public void atualiza(Bancos banco){
        listaDeBancos.set(this.getIndice(banco.getCodBanco()), banco);
    }
    
    
     public void exclui(Bancos banco){
        listaDeBancos.remove(this.getIndice(banco.getCodBanco()));
    }    
}
