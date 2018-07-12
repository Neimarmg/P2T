package rn;
import entidade.gMenus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class MemusRN {

    public gMenus inserir(gMenus menus) {
        new JPAUtil().execInsert(menus, true);        
        return menus;
    }

    
    public List<gMenus> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<gMenus> query = manager.createQuery("SELECT m FROM gMenus m",gMenus.class);
        List<gMenus> listaMenus = query.getResultList();

        view.View.msg("Menus:");
        for (gMenus m : listaMenus) {
            view.View.msg(m.getIdMenu()+ "-" + m.getNomeMenu()+ "-" + m.getTipomenu());
        }

        manager.close();

        return (listaMenus);
    }

    
    public gMenus buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        gMenus mcurso = manager.find(gMenus.class, id);
        manager.close();
        return mcurso;        
    }
    
    
    public gMenus atualizar(Long id, gMenus menus) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        gMenus menusAtual = manager.find(gMenus.class,id);
        
        if(menusAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(menus.getTipomenu()!=null && !menus.getTipomenu().equals(id))
            menusAtual.setTipomenu(menus.getTipomenu());
       
        if(menus.getNomeMenu()!=null && !menus.getNomeMenu().isEmpty())
            menusAtual.setNomeMenu(menus.getNomeMenu());     
        
         menusAtual.setFavorito(menus.isFavorito());
         
        manager.getTransaction().commit();
        
        manager.close();
        
        return menusAtual;    }
    
    
    public gMenus deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        gMenus menusAtual = manager.find(gMenus.class,id);
        new JPAUtil().execDelete(manager, menusAtual, true);
        
        return (menusAtual);

    }

}