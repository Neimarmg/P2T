package rn;


import entidade.aUnidadeHabiltacao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class UnidadeHabilitacaoRN {

    public aUnidadeHabiltacao inserir(aUnidadeHabiltacao unidadeHabiltacao) {
        new JPAUtil().execInsert(unidadeHabiltacao, true);        
        return unidadeHabiltacao;
    }

    
    public List<aUnidadeHabiltacao> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aUnidadeHabiltacao> query = manager.createQuery("SELECT m FROM aUnidadeHabiltacao m",aUnidadeHabiltacao.class);
        List<aUnidadeHabiltacao> listaUnidadeHabiltacaos = query.getResultList();

        view.View.msg("Unidade Habiltacao:");
        for (aUnidadeHabiltacao m : listaUnidadeHabiltacaos) {
            view.View.msg(m.getIdUnidadeHabiltacao()
                    + "-" + m.getCurso()
                    + "-" +m.getFilial()
                    + " -" +m.getNomeHabilitacao()
                    + " -" +m.getMatriz()
                    + " -" +m.getProjetocursos());
        }
        manager.close();

        return (listaUnidadeHabiltacaos);
    }

    
    public aUnidadeHabiltacao buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        aUnidadeHabiltacao unidadeHabiltacao = manager.find(aUnidadeHabiltacao.class, id);
        manager.close();
        return unidadeHabiltacao;        
    }
    
    
    public aUnidadeHabiltacao atualizar(Long id, aUnidadeHabiltacao unidadeHabiltacao) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        aUnidadeHabiltacao cursoUnidadeHabiltacao = manager.find(aUnidadeHabiltacao.class,id);
        
        if(cursoUnidadeHabiltacao == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(unidadeHabiltacao.getProjetocursos()!=null && !unidadeHabiltacao.getProjetocursos().isEmpty())
            cursoUnidadeHabiltacao.setProjetocursos(unidadeHabiltacao.getProjetocursos());
        
        if(unidadeHabiltacao.getCurso()!=null && !unidadeHabiltacao.getCurso().isEmpty())
            cursoUnidadeHabiltacao.setCurso(unidadeHabiltacao.getCurso());

        if(unidadeHabiltacao.getFilial()!=null && !unidadeHabiltacao.getFilial().isEmpty())
            cursoUnidadeHabiltacao.setFilial(unidadeHabiltacao.getFilial());
       
        if(unidadeHabiltacao.getMatriz()!=null && !unidadeHabiltacao.getMatriz().isEmpty())
            cursoUnidadeHabiltacao.setMatriz(unidadeHabiltacao.getMatriz());
        
        if(unidadeHabiltacao.getNomeHabilitacao()!=null && !unidadeHabiltacao.getNomeHabilitacao().isEmpty())
            cursoUnidadeHabiltacao.setNomeHabilitacao(unidadeHabiltacao.getNomeHabilitacao());     
        
        manager.getTransaction().commit();
        
        manager.close();
        
        return cursoUnidadeHabiltacao;    }
    
    
    public aUnidadeHabiltacao deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aUnidadeHabiltacao unidadeHabiltacaoAtual = manager.find(aUnidadeHabiltacao.class,id);
        new JPAUtil().execDelete(manager, unidadeHabiltacaoAtual, true);
        
        return (unidadeHabiltacaoAtual);

    }

}