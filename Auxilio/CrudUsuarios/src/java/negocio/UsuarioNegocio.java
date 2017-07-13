package negocio;

import dao.UsuarioDao;
import dao.impl_BD.UsuarioDaoBd;
import java.util.List;
import javax.ejb.Stateless;
import model.Usuario;

@Stateless
public class UsuarioNegocio {
    UsuarioDao usuarioDao;
    
    public UsuarioNegocio(){
        usuarioDao = new UsuarioDaoBd();
    }
    
    public void inserir(Usuario u) throws Exception{
        if(u.getNome() == null || u.getNome().isEmpty())
            throw new Exception("Nome invalido!");
        usuarioDao.salvar(u);
    }
    
    public List<Usuario> listar(){
        return usuarioDao.listar();
    }
    
    public Usuario buscarPorId(int id){
        return usuarioDao.procurarPorId(id);
    }
    
    public Usuario deletar(int id) throws Exception{
        Usuario usuario = usuarioDao.procurarPorId(id);
        if (usuario==null){
            throw new Exception("Usuario nao encontrado");
        }
        usuarioDao.deletar(id);
        return usuario;
    }
    
    public void atualizar(Usuario u, int id) throws Exception{
        Usuario usuario = usuarioDao.procurarPorId(id);
        if(usuario == null){
            throw new Exception("Usuario nao encontrado");
        }
        if(u.getNome()!=null && !u.getNome().isEmpty()){
            usuario.setNome(u.getNome());
        }
        if(u.getEmail()!=null && !u.getEmail().isEmpty()){
        usuario.setEmail(u.getEmail());
        }
        if(u.getUsername()!=null && !u.getUsername().isEmpty()){
            usuario.setUsername(u.getUsername());
        }
        usuarioDao.atualizar(usuario);
    }

}
