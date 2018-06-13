package rn;
import entidade.aTurmaDisciplinas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class TurmaDisciplinasRN {

    public aTurmaDisciplinas inserir(aTurmaDisciplinas turmaDisciplinas) {
        new JPAUtil().execInsert(turmaDisciplinas, true);        
        return turmaDisciplinas;
    }

    
    public List<aTurmaDisciplinas> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<aTurmaDisciplinas> query = manager.createQuery("SELECT m FROM aTurmaDisciplinas m",aTurmaDisciplinas.class);
        List<aTurmaDisciplinas> turmaDisciplinasCursos = query.getResultList();

        view.View.msg("Turmas Disciplinas:");
        for (aTurmaDisciplinas m : turmaDisciplinasCursos) {
            view.View.msg(m.getIdTurmaDisciplina()
                    + "-" + m.getDisciplina()
                    + "-" +m.getTurno()
                    + "-" +m.getUnidadeHabiltacao()
                    + "-" +m.getDataInicio()
                    + "-" +m.getDataFim());
        }

        manager.close();

        return (turmaDisciplinasCursos);
    }

    
    public aTurmaDisciplinas buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        aTurmaDisciplinas turmaDisciplinas = manager.find(aTurmaDisciplinas.class, id);
        manager.close();
        return turmaDisciplinas;        
    }
    
    
    public aTurmaDisciplinas atualizar(Long id, aTurmaDisciplinas turmaDisciplinas) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        aTurmaDisciplinas turmaDisciplinasAtual = manager.find(aTurmaDisciplinas.class,id);
        
        if(turmaDisciplinasAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(turmaDisciplinas.getUnidadeHabiltacao()!=null && !turmaDisciplinas.getUnidadeHabiltacao().isEmpty())
            turmaDisciplinasAtual.setUnidadeHabiltacao(turmaDisciplinas.getUnidadeHabiltacao());
        
        if(turmaDisciplinas.getTurno()!=null && !turmaDisciplinas.getTurno().isEmpty())
            turmaDisciplinas.setTurno(turmaDisciplinas.getTurno());     
        
        if(turmaDisciplinas.getDisciplina()!=null && !turmaDisciplinas.getDisciplina().isEmpty())
            turmaDisciplinas.setDisciplina(turmaDisciplinas.getDisciplina());        

        if(turmaDisciplinas.getDataInicio()!=null && !turmaDisciplinas.getDataInicio().isEmpty())
            turmaDisciplinas.setDataInicio(turmaDisciplinas.getDataInicio());        

        if(turmaDisciplinas.getDataFim()!=null && !turmaDisciplinas.getDataFim().isEmpty())
            turmaDisciplinas.setDataFim(turmaDisciplinas.getDataFim());        
        
        manager.getTransaction().commit();
        
        manager.close();
        
        return turmaDisciplinasAtual;    }
    
    
    public aTurmaDisciplinas deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        aTurmaDisciplinas turmaDisciplinasAtual = manager.find(aTurmaDisciplinas.class,id);
        new JPAUtil().execDelete(manager, turmaDisciplinasAtual, true);
        
        return (turmaDisciplinasAtual);

    }

}