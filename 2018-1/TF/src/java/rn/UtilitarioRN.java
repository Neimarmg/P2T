package rn;


import entidade.gUtilitarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class UtilitarioRN {

    public gUtilitarios inserir(gUtilitarios utilitarios) {
        new JPAUtil().execInsert(utilitarios, true);        
        return utilitarios;
    }

    
    public List<gUtilitarios> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<gUtilitarios> query = manager.createQuery("SELECT m FROM gUtilitarios m",gUtilitarios.class);
        List<gUtilitarios> listaUtilitarios = query.getResultList();

        System.out.println("Curso:");
        for (gUtilitarios u : listaUtilitarios) {
            view.View.msg(u.getIdUtilitario()+ "-" + u.getNemeUtilitario()+ "-" + u.getTipoutilitario()+ "-" + u.getObs()+ "-" + u.isFavorita());
        }

        manager.close();

        return (listaUtilitarios);
    }

    
    public gUtilitarios buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        gUtilitarios cursoUtilitarios = manager.find(gUtilitarios.class, id);
        manager.close();
        return cursoUtilitarios;        
    }
    
    
    public gUtilitarios atualizar(Long id, gUtilitarios utilitarios) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        gUtilitarios utilitariosAtual = manager.find(gUtilitarios.class,id);
        
        if(utilitariosAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(utilitarios.getAplicacao()!=null && !utilitarios.getAplicacao().equals(id))
            utilitariosAtual.setAplicacao(utilitarios.getAplicacao());
        
        if(utilitarios.getTipoutilitario()!=null && !utilitarios.getTipoutilitario().equals(id))
            utilitarios.setTipoutilitario(utilitarios.getTipoutilitario());
        
        if(utilitarios.getNemeUtilitario()!=null && !utilitarios.getNemeUtilitario().isEmpty())
            utilitariosAtual.setNemeUtilitario(utilitarios.getNemeUtilitario());     
        
        utilitariosAtual.setObs(utilitarios.getObs());  
        utilitariosAtual.setFavorita(utilitarios.isFavorita());  
        
        manager.getTransaction().commit();
        
        manager.close();
        
        return utilitariosAtual;    }
    
    
    public gUtilitarios deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        gUtilitarios utilitarioAtual = manager.find(gUtilitarios.class,id);
        new JPAUtil().execDelete(manager, utilitarioAtual, true);
        
        return (utilitarioAtual);

    }

}