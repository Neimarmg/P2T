package rn;

import entidade.yUsuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class UsuarioRN {

    public yUsuarios inserir(yUsuarios usuarios) {
        new JPAUtil().execInsert(usuarios, true);        
        return usuarios;
    }

    
    public List<yUsuarios> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<yUsuarios> query = manager.createQuery("SELECT m FROM yUsuarios m",yUsuarios.class);
        List<yUsuarios> listaUsutarios = query.getResultList();

        view.View.msg("Usuario:");
        for (yUsuarios m : listaUsutarios) {
            view.View.msg(m.getId()+ "-" + m.getPessoa()+ "-" + m.getIdUsuario()+ "-" + m.getSenha());
        }

        manager.close();

        return (listaUsutarios);
    }

    
    public yUsuarios buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        yUsuarios usuarios = manager.find(yUsuarios.class, id);
        manager.close();
        return usuarios;        
    }
    
    
    public yUsuarios atualizar(Long id, yUsuarios usuarios) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        yUsuarios usuarioAtual = manager.find(yUsuarios.class,id);
        
        if(usuarioAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
     
        if(usuarios.getPessoa()!=null && !usuarios.getPessoa().equals(id))
            usuarioAtual.setPessoa(usuarios.getPessoa());
           
        usuarios.setSenha(usuarios.getSenha());
       
        
        manager.getTransaction().commit();
        
        manager.close();
        
        return usuarioAtual;    }
    
    
    public yUsuarios deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        yUsuarios usuariosAtual = manager.find(yUsuarios.class,id);
        new JPAUtil().execDelete(manager, usuariosAtual, true);
        
        return (usuariosAtual);

    }

}