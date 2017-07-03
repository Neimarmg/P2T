package Api.Dao.TipoUtilitarioBd;

import Api.Dao.PessoaBd.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Api.conexao.BDException;
import Api.conexao.ConnectionFactory;
import dominio.Pessoa;
import dominio.TipoUtilitarios;

public class TipoUtilitarioDaoBd implements TipoUtilitarioDao {
    private Connection conexao;
    private PreparedStatement comando;

    public Connection conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }

    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Erro ao encerrar a conexao");
            throw new BDException(ex);

        }

    }  

    //Metodo salvar:recebe o id auto-increment e já relaciona 
    //no objeto paciente (recebido por parâmetro)
    //Caso queira retornar, só retornar id.
    @Override
    public void salvar(TipoUtilitarios tipoUtilitarios) {
        int id = 0;
       
        try {
            String sql = "INSERT INTO tipoutilitario(descTipoUtilitario) "
                    + "VALUES (?,)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            
            comando.setString(1, tipoUtilitarios.getDescTipoUtilitario());
          
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                tipoUtilitarios.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(TipoUtilitarios tipoUtilitario) {
        try {
            String sql = "DELETE FROM tipoutilitario WHERE codTipoUtilitario = ?";

            conectar(sql);
            comando.setInt(1, tipoUtilitario.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(int id) {
        try {
            String sql = "DELETE FROM tipoutilitario WHERE codTipoUtilitario = ?";

            conectar(sql);
            comando.setInt(1, id);
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
     @Override
    public void atualizar(TipoUtilitarios tipoUtilitarios)   {
        try {
            String sql = "UPDATE `tipoutilitario` SET descTipoUtilitario`=?] "
                    + "WHERE codTipoUtilitario=?";

            conectar(sql);
            comando.setString(1, tipoUtilitarios.getDescTipoUtilitario());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<TipoUtilitarios> listar() {
       List<TipoUtilitarios> listaTipoUtilitarios = new ArrayList<>();

        String sql = "SELECT codPessoa, nome, codCidade, codTipoPessoa, telefone, email FROM Pessoa";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("Nome");
                            
                TipoUtilitarios tipoUtilitarios = new TipoUtilitarios(nome);

                listaTipoUtilitarios.add(tipoUtilitarios);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os usuarios do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaTipoUtilitarios);
    }

    @Override
    public TipoUtilitarios procurarPorId(int id) {
       String sql = "SELECT codTipoUtilitario,descTipoUtilitario FROM tipoutilitario WHERE codTipoUtilitario=?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                

                TipoUtilitarios tipoUtilitarios = new TipoUtilitarios(nome);

                return tipoUtilitarios;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o usuario pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        
        return (null);
    }

}
