package rn;import entidade.gPredio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class PredioRN {

    public gPredio inserir(gPredio predio) {
        new JPAUtil().execInsert(predio, true);        
        return predio;
    }

    
    public List<gPredio> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<gPredio> query = manager.createQuery("SELECT m FROM gPredio m",gPredio.class);
        List<gPredio> listaPredio = query.getResultList();

        view.View.msg("Predio:");
        for (gPredio p : listaPredio) {
            view.View.msg(p.getIdPredio()
                    + "-" + p.getFilial()
                    + "-" +p.getNomePredio()
                    + "-" +p.getQtPavimentos());
        }

        manager.close();

        return (listaPredio);
    }

    
    public gPredio buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        gPredio predio = manager.find(gPredio.class, id);
        manager.close();
        return predio;        
    }
    
    
    public gPredio atualizar(Long id,gPredio predio) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        gPredio gPredioAtual = manager.find(gPredio.class,id);
        
        if(gPredioAtual == null) throw new Exception();        
        manager.getTransaction().begin();
     
        if(predio.getFilial()!=null && !predio.getFilial().isEmpty())
            gPredioAtual.setFilial(predio.getFilial());
        
        if(predio.getNomePredio()!=null && !predio.getNomePredio().isEmpty())
            gPredioAtual.setNomePredio(predio.getNomePredio());     
        
        gPredioAtual.setQtPavimentos(predio.getQtPavimentos());
         gPredioAtual.setAtivo(predio.isAtivo()); 
        
        manager.getTransaction().commit();        
        manager.close();        
        return gPredioAtual;    }
    
    
    public gPredio deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        gPredio predioAtual = manager.find(gPredio.class,id);
        new JPAUtil().execDelete(manager, predioAtual, true);
       
        return (predioAtual);

    }

}