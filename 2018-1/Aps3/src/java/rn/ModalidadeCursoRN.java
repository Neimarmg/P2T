package rn;

import entidade.aModalidadecurso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author LHRIES
 */
public class ModalidadeCursoRN {

    public aModalidadecurso inserir(aModalidadecurso modalidadecurso) {
        new JPAUtil().executaInsert(modalidadecurso, true);        
        return modalidadecurso;

    }

    
    public List<aModalidadecurso> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aModalidadecurso> query = manager.createQuery("SELECT m FROM aModalidadecurso m",aModalidadecurso.class);
        List<aModalidadecurso> listaMotores = query.getResultList();

        System.out.println("Modalidades:");
        for (aModalidadecurso m : listaMotores) {
            System.out.println(m.getDescricao()/*+ "-" + m.getUso()*/);
        }

        manager.close();

        return (listaMotores);

    }

    
    public aModalidadecurso buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        aModalidadecurso modalidadecurso = manager.find(aModalidadecurso.class, id);
        manager.close();
        return modalidadecurso;
        
    }
    
    
    public aModalidadecurso atualizar(Long id, aModalidadecurso modalidadecurso) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        
        aModalidadecurso molalidadeAtual = manager.find(aModalidadecurso.class,id);
        
        if(molalidadeAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
        if(modalidadecurso.getDescricao()!=null && !modalidadecurso.getDescricao().isEmpty())
            molalidadeAtual.setDescricao(molalidadeAtual.getDescricao());

        manager.getTransaction().commit();
        
        manager.close();
        
        return molalidadeAtual;
    }
    
    
    public aModalidadecurso deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aModalidadecurso modalidadeAtual = manager.find(aModalidadecurso.class,id);

        if(modalidadeAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
        manager.remove(modalidadeAtual);
        manager.getTransaction().commit();
        
        manager.close();
        
        return (modalidadeAtual);
        

    }

}