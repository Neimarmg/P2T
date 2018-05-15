package rn;


import entidade.aProjetocurso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class ProjetocursoRN {

    public aProjetocurso inserir(aProjetocurso projetocurso) {
        new JPAUtil().execInsert(projetocurso, true);        
        return projetocurso;
    }

    
    public List<aProjetocurso> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aProjetocurso> query = manager.createQuery("SELECT m FROM aProjetocurso m",aProjetocurso.class);
        List<aProjetocurso> listaProjetos = query.getResultList();

        System.out.println("Projetos:");
        for (aProjetocurso m : listaProjetos) {
            view.View.msg(m.getIdProjetoCurso()+ "-" +m.getDescricaoProjeto()+ "-" + m.getModalidadecurso()+ "-" + m.getFilial()+ "-" +m.getValorCurso());
        }

        manager.close();

        return (listaProjetos);
    }

    
    public aProjetocurso buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        aProjetocurso projetocurso = manager.find(aProjetocurso.class, id);
        manager.close();
        return projetocurso;        
    }
    
    
    public aProjetocurso atualizar(Long id, aProjetocurso projetocurso) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        aProjetocurso projetoAtual = manager.find(aProjetocurso.class,id);
        
        if(projetoAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(projetoAtual.getModalidadecurso()!=null && !projetocurso.getModalidadecurso().equals(id))
            projetoAtual.setModalidadecurso(projetocurso.getModalidadecurso());
        
         projetoAtual.setDescricaoProjeto(projetocurso.getDescricaoProjeto());      
        
        projetoAtual.setFilial(projetocurso.getFilial());
        
        //projetoAtual.getDataInicio(projetocurso.getDataInicio());
        //projetoAtual.getDataFim(projetocurso.getDataFim());   
        
        manager.getTransaction().commit();
        
        manager.close();
        
        return projetoAtual;    }
    
    
    public aProjetocurso deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aProjetocurso projetoAtual = manager.find(aProjetocurso.class,id);
        new JPAUtil().execDelete(manager, projetoAtual, true);
        
        return (projetoAtual);

    }

}