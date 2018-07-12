package rn;


import entidade.aHorariosAulas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class HorariosAulasRN {

    public aHorariosAulas inserir(aHorariosAulas horariosAulas) {
        new JPAUtil().execInsert(horariosAulas, true);        
        return horariosAulas;
    }

    
    public List<aHorariosAulas> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aHorariosAulas> query = manager.createQuery("SELECT m FROM aHorariosAulas m",aHorariosAulas.class);
        List<aHorariosAulas> listaHorariosAulas = query.getResultList();

        view.View.msg("Horarios Aulas:");
        for (aHorariosAulas m : listaHorariosAulas) {
            view.View.msg(m.getIdHorariosAulas()
                    + "-" + m.getTurmaDisciplinas()
                    + "-" +m.getDataInicio()
                    +"-" +m.getDataFim()
                    +"-" +m.getTurno());
        }

        manager.close();

        return (listaHorariosAulas);
    }

    
    public aHorariosAulas buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        aHorariosAulas horariosAulas = manager.find(aHorariosAulas.class, id);
        manager.close();
        return horariosAulas;        
    }
    
    
    public aHorariosAulas atualizar(Long id, aHorariosAulas horariosAulas) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        aHorariosAulas horariosAulasAtual = manager.find(aHorariosAulas.class,id);
        
        if(horariosAulasAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(horariosAulas.getTurmaDisciplinas()!=null && !horariosAulas.getTurmaDisciplinas().equals(id))
            horariosAulasAtual.setTurmaDisciplinas(horariosAulas.getTurmaDisciplinas());
        
        if(horariosAulas.getTurno()!=null && !horariosAulas.getTurno().isEmpty())
            horariosAulasAtual.setTurno(horariosAulas.getTurno());     
        
        horariosAulasAtual.setDataInicio(horariosAulas.getDataInicio()); 
        horariosAulasAtual.setDataFim(horariosAulas.getDataFim()); 
        
        
        manager.getTransaction().commit();
        
        manager.close();
        
        return horariosAulasAtual;    }
    
    
    public aHorariosAulas deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aHorariosAulas horariosAulasAtual = manager.find(aHorariosAulas.class,id);
        new JPAUtil().execDelete(manager, horariosAulasAtual, true);
        
        return (horariosAulasAtual);

    }

}