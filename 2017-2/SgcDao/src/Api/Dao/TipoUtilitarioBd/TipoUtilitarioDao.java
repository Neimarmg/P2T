package Api.Dao.TipoUtilitarioBd;


import dominio.TipoUtilitarios;
import java.util.List;

public interface TipoUtilitarioDao {
    public void salvar(TipoUtilitarios tipoUtilitarios);
    public void deletar(TipoUtilitarios tipoUtilitarios);
    public void deletar(int id);
    public void atualizar(TipoUtilitarios tipoUtilitario);
    public List<TipoUtilitarios> listar();
    public TipoUtilitarios procurarPorId(int id);
    
}
