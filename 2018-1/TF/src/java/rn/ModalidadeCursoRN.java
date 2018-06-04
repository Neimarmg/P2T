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
        new JPAUtil().execInsert(modalidadecurso, true);        
        return modalidadecurso;
    }

    
    public List<aModalidadecurso> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aModalidadecurso> query = manager.createQuery("SELECT m FROM aModalidadecurso m",aModalidadecurso.class);
        List<aModalidadecurso> listaMotores = query.getResultList();

        view.View.msg("Modalidades:");
        for (aModalidadecurso m : listaMotores) {
           view.View.msg(m.getIdModalidade() + "-" + m.getDescricao());
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
        
        aModalidadecurso modalidadeAtual = manager.find(aModalidadecurso.class,id);
        
        if(modalidadeAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(modalidadecurso.getDescricao()!=null && !modalidadecurso.getDescricao().isEmpty())
            modalidadeAtual.setDescricao(modalidadecurso.getDescricao());
       
        manager.getTransaction().commit();
        
        manager.close();
        
        return modalidadeAtual;
    }
    
    
    public aModalidadecurso deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aModalidadecurso modalidadeAtual = manager.find(aModalidadecurso.class,id);
        new JPAUtil().execDelete(manager, modalidadeAtual, true);
        
        return (modalidadeAtual);        
    }

}