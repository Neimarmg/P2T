package rn;

import entidade.aModalidadecurso;
import java.util.List;
import entidade.aCurso;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author LHRIES
 */
public class CursoRN {

    public aCurso inserir(aCurso curso) {
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        manager.persist(curso);
        manager.getTransaction().commit();
        
        manager.close();
        
        return curso;

    }

    
    public List<aCurso> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aCurso> query = manager.createQuery("SELECT m FROM aCurso m",aCurso.class);
        List<aCurso> listaCuros = query.getResultList();

        System.out.println("Cursos:");
        for (aCurso m : listaCuros) {
            System.out.println(m.getDescricao()/*+ "-" + m.getUso()*/);
        }

        manager.close();

        return (listaCuros);

    }

    
    public aCurso buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        aCurso curso = manager.find(aCurso.class, id);
        manager.close();
        return curso;
        
    }
    
    
    public aCurso atualizar(Long id, aCurso curso) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        
        aCurso cursoAtual = manager.find(aCurso.class,id);
        
        if(cursoAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
        if(curso.getDescricao()!=null && !curso.getDescricao().isEmpty())
            cursoAtual.setDescricao(cursoAtual.getDescricao());

        manager.getTransaction().commit();
        
        manager.close();
        
        return cursoAtual;
    }
    
    
    public aCurso deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aCurso cursoAtual = manager.find(aCurso.class,id);

        if(cursoAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
        manager.remove(cursoAtual);
        manager.getTransaction().commit();
        
        manager.close();
        
        return (cursoAtual);
        

    }

}
