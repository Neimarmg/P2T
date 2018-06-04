package rn;


import entidade.aPlanoDeAula;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class PlanoAulaRN {

    public aPlanoDeAula inserir(aPlanoDeAula planoDeAula) {
        new JPAUtil().execInsert(planoDeAula, true);        
        return planoDeAula;
    }

    
    public List<aPlanoDeAula> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aPlanoDeAula> query = manager.createQuery("SELECT m FROM aPlanoDeAula m",aPlanoDeAula.class);
        List<aPlanoDeAula> listaPlanoDeAulas = query.getResultList();

        view.View.msg("Lista de aulas:");
        for (aPlanoDeAula m : listaPlanoDeAulas) {
            view.View.msg(m.getIdPlanoDeAula()
                    + "-" + m.getDataAula()
                    + "-" +m.getHorariosAulas()
                    + "-" +m.getTurno()        
                    + "-" +m.isConfirmada());
        }

        manager.close();

        return (listaPlanoDeAulas);
    }

    
    public aPlanoDeAula buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        aPlanoDeAula curso = manager.find(aPlanoDeAula.class, id);
        manager.close();
        return curso;        
    }
    
    
    public aPlanoDeAula atualizar(Long id, aPlanoDeAula planoDeAula) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        aPlanoDeAula planoDeAulaAtual = manager.find(aPlanoDeAula.class,id);
        
        if(planoDeAulaAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(planoDeAula.getHorariosAulas()!=null && !planoDeAula.getHorariosAulas().equals(id))
            planoDeAulaAtual.setHorariosAulas(planoDeAula.getHorariosAulas());
        
        if(planoDeAula.getDataAula()!=null && !planoDeAula.getDataAula().isEmpty())
            planoDeAulaAtual.setDataAula(planoDeAula.getDataAula());     
        
        planoDeAulaAtual.setConfirmada(planoDeAula.isConfirmada()); 
        planoDeAulaAtual.setConteudo(planoDeAula.getConteudo()); 
        manager.getTransaction().commit();
        
        manager.close();
        
        return planoDeAulaAtual;    }
    
    
    public aPlanoDeAula deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aPlanoDeAula planoDeAulaAtual = manager.find(aPlanoDeAula.class,id);
        new JPAUtil().execDelete(manager, planoDeAulaAtual, true);
        
        return (planoDeAulaAtual);

    }

}