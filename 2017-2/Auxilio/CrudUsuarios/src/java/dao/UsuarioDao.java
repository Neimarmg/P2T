package dao;

import model.Usuario;
import java.util.List;

public interface UsuarioDao {
    public void salvar(Usuario usuario);
    public void deletar(Usuario usuario);
    public void deletar(int id);
    public void atualizar(Usuario usuario);
    public List<Usuario> listar();
    public Usuario procurarPorId(int id);
    
}
