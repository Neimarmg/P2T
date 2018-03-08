package Api.Dao.PessoaBd;

import dominio.Pessoa;
import java.util.List;

public interface PessoaDao {
    public void salvar(Pessoa usuario);
    public void deletar(Pessoa usuario);
    public void deletar(int id);
    public void atualizar(Pessoa usuario);
    public List<Pessoa> listar();
    public Pessoa procurarPorId(int id);
    
}
