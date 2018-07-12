package rn;


import entidade.aHorarioProfessor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class HorarioProfessorRN {

    public aHorarioProfessor inserir(aHorarioProfessor horarioProfessor) {
        new JPAUtil().execInsert(horarioProfessor, true);        
        return horarioProfessor;
    }

    
    public List<aHorarioProfessor> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aHorarioProfessor> query = manager.createQuery("SELECT m FROM aHorarioProfessor m",aHorarioProfessor.class);
        List<aHorarioProfessor> listaHorarioProfessors = query.getResultList();

        view.View.msg("Lista aulas professor:");
        for (aHorarioProfessor m : listaHorarioProfessors) {
            view.View.msg(m.getIdaHorarioProfessor()+ "-" + m.getPlanoDeAulas());
        }

        manager.close();

        return (listaHorarioProfessors);
    }

    
    public aHorarioProfessor buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        aHorarioProfessor horarioProfessor = manager.find(aHorarioProfessor.class, id);
        manager.close();
        return horarioProfessor;        
    }
    
    
    public aHorarioProfessor atualizar(Long id, aHorarioProfessor horarioProfessor) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        aHorarioProfessor horarioProfessorAtual = manager.find(aHorarioProfessor.class,id);
        
        if(horarioProfessorAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(horarioProfessor.getPlanoDeAulas()!=null && !horarioProfessor.getPlanoDeAulas().equals(id))
            horarioProfessorAtual.setPlanoDeAulas(horarioProfessor.getPlanoDeAulas());
        
        if(horarioProfessor.getProfessor()!=null && !horarioProfessor.getProfessor().equals(id))
            horarioProfessorAtual.setProfessor(horarioProfessor.getProfessor());
        
        horarioProfessorAtual.setValorAula(horarioProfessor.getValorAula());
        
        manager.getTransaction().commit();
        
        manager.close();
        
        return horarioProfessorAtual;    }
    
    
    public aHorarioProfessor deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aHorarioProfessor horarioProfessorAtual = manager.find(aHorarioProfessor.class,id);
        new JPAUtil().execDelete(manager, horarioProfessorAtual, true);
        
        return (horarioProfessorAtual);

    }

}