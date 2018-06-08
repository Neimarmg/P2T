package rn;
import entidade.gTipoutilitarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class TipoUtilitarioRN {

    public gTipoutilitarios inserir(gTipoutilitarios tipoutilitarios) {
        new JPAUtil().execInsert(tipoutilitarios, true);        
        return tipoutilitarios;
    }

    
    public List<gTipoutilitarios> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<gTipoutilitarios> query = manager.createQuery("SELECT m FROM gTipoutilitarios m",gTipoutilitarios.class);
        List<gTipoutilitarios> listaTipoUtilitarios = query.getResultList();

        view.View.msg("TipoUtilitarios:");
        for (gTipoutilitarios g : listaTipoUtilitarios) {
            view.View.msg(g.getIdTipoUtilitario()+ "-" + g.getDescricao());
        }

        manager.close();

        return (listaTipoUtilitarios);
    }

    
    public gTipoutilitarios buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        gTipoutilitarios tipoUtilitarios = manager.find(gTipoutilitarios.class, id);
        manager.close();
        return tipoUtilitarios;        
    }
    
    
    public gTipoutilitarios atualizar(Long id, gTipoutilitarios tipoutilitario) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        gTipoutilitarios tipoUtilitariosAtual = manager.find(gTipoutilitarios.class,id);
        
        if(tipoUtilitariosAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
   
        if(tipoutilitario.getDescricao()!=null && !tipoutilitario.getDescricao().isEmpty())
            tipoUtilitariosAtual.setDescricao(tipoutilitario.getDescricao());     
        
        manager.getTransaction().commit();
        
        manager.close();
        
        return tipoUtilitariosAtual;    }
    
    
    public gTipoutilitarios deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        gTipoutilitarios tipoUtilitariosAtual = manager.find(gTipoutilitarios.class,id);
        new JPAUtil().execDelete(manager, tipoUtilitariosAtual, true);
        
        return (tipoUtilitariosAtual);

    }

}