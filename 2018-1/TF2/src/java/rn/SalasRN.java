package rn;import entidade.gSalas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class SalasRN {

    public gSalas inserir(gSalas salas) {
        new JPAUtil().execInsert(salas, true);        
        return salas;
    }

    
    public List<gSalas> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<gSalas> query = manager.createQuery("SELECT m FROM gSalas m",gSalas.class);
        List<gSalas> listaSalas = query.getResultList();

        view.View.msg("Predio:");
        for (gSalas s : listaSalas) {
            view.View.msg(s.getIdSala()
                    + "-" + s.getPredios()
                    + "-" +s.getIdSala()
                    + "-" +s.getCapacidade()
                    + "-" +s.getPavimento());
        }

        manager.close();

        return (listaSalas);
    }

    
    public gSalas buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        gSalas salas = manager.find(gSalas.class, id);
        manager.close();
        return salas;        
    }
    
    
    public gSalas atualizar(Long id,gSalas salas) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        gSalas salasAtual = manager.find(gSalas.class,id);
        
        if(salasAtual == null) throw new Exception();        
        manager.getTransaction().begin();
     
        if(salas.getSala()!=null && !salas.getSala().isEmpty())
            salasAtual.setSala(salas.getSala());
        
        if(salas.getPavimento()!=null && !salas.getPavimento().isEmpty())
            salasAtual.setPavimento(salas.getPavimento());     
        
        if(salas.getPredios()!=null && !salas.getPredios().isEmpty())
            salasAtual.setPredios(salas.getPredios());
       
        if(salas.getTipoSala()!=null && !salas.getTipoSala().isEmpty())
            salasAtual.setTipoSala(salas.getTipoSala()); 
        
        salasAtual.setCapacidade(salas.getCapacidade());
        salasAtual.setAtiva(salas.isAtiva());
        
        manager.getTransaction().commit();        
        manager.close();        
        return salasAtual;    }
    
    
    public gSalas deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        gSalas salasAtual = manager.find(gSalas.class,id);
        new JPAUtil().execDelete(manager, salasAtual, true);
       
        return (salasAtual);

    }

}