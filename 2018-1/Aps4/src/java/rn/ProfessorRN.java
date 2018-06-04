package rn;


import entidade.aProfessor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class ProfessorRN {

    public aProfessor inserir(aProfessor professor) {
        new JPAUtil().execInsert(professor, true);        
        return professor;
    }

    
    public List<aProfessor> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aProfessor> query = manager.createQuery("SELECT m FROM aProfessor m",aProfessor.class);
        List<aProfessor> listaProfessors = query.getResultList();

        view.View.msg("Professor:");
        for (aProfessor m : listaProfessors) {
            view.View.msg(m.getIdProfessor()+ "-" + m.getPessoas());
        }

        manager.close();

        return (listaProfessors);
    }

    
    public aProfessor buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        aProfessor professor = manager.find(aProfessor.class, id);
        manager.close();
        return professor;        
    }
    
    
    public aProfessor atualizar(Long id, aProfessor professor) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        aProfessor professorAtual = manager.find(aProfessor.class,id);
        
        if(professorAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(professor.getPessoas()!=null && !professor.getPessoas().equals(id))
            professorAtual.setPessoas(professor.getPessoas());

        professorAtual.setAtivo(professor.isAtivo());     
        
        manager.getTransaction().commit();
        
        manager.close();
        
        return professorAtual;    }
    
    
    public aProfessor deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aProfessor professorAtual = manager.find(aProfessor.class,id);
        new JPAUtil().execDelete(manager, professorAtual, true);
        
        return (professorAtual);

    }

}