package rn;

import entidade.aCurso;
import entidade.aDisciplinas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class DisciplinaRN {

    public aDisciplinas inserir(aDisciplinas disciplinas) {
        new JPAUtil().execInsert(disciplinas, true);        
        return disciplinas;
    }

    
    public List<aDisciplinas> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aDisciplinas> query = manager.createQuery("SELECT m FROM aDisciplinas m",aDisciplinas.class);
        List<aDisciplinas> listaDisciplinas = query.getResultList();
        aCurso curso = new aCurso();
        CursoRN  cursoRN = new CursoRN();
        
        System.out.println("Disciplinas:");
        for (aDisciplinas m : listaDisciplinas) {
            cursoRN.buscarPorId(m.getIdCurso());
            view.View.msg(m.getIdDisciplina()+ "-" + m.getIdCurso()+ "-" + m.getNomeDisciplina()+ "-" + curso.getNomeCurso());
            
        }

        manager.close();

        return (listaDisciplinas);
    }

    
    public aDisciplinas buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        aDisciplinas disciplinas = manager.find(aDisciplinas.class, id);
        manager.close();
        return disciplinas;        
    }
    
    
    public aDisciplinas atualizar(Long id, aDisciplinas disciplinas) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        aDisciplinas disciplinaAtual = manager.find(aDisciplinas.class,id);
        
        if(disciplinaAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(disciplinas.getIdCurso()!=null && !disciplinas.getIdCurso().equals(id))
            disciplinaAtual.setIdCurso(disciplinas.getIdCurso());
        
        if(disciplinas.getNomeDisciplina()!=null && !disciplinas.getNomeDisciplina().isEmpty())
            disciplinaAtual.setNomeDisciplina(disciplinas.getNomeDisciplina());
       
        manager.getTransaction().commit();
        
        manager.close();
        
        return disciplinaAtual;    }
    
    
    public aDisciplinas deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aDisciplinas disciplinaAtual = manager.find(aDisciplinas.class,id);
        new JPAUtil().execDelete(manager, disciplinaAtual, true);
        
        return (disciplinaAtual);

    }

}