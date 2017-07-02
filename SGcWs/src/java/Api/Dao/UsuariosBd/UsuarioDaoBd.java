package Api.Dao.UsuariosBd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Api.conexao.BDException;
import Api.conexao.ConnectionFactory;
import Model.Usuario;

public class UsuarioDaoBd implements UsuarioDao {
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
    public void salvar(Usuario usuario) {
        int id = 0;
        try {
            String sql = "INSERT INTO usuario (nome, email, username) "
                    + "VALUES (?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.setString(3, usuario.getUsername());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                usuario.setId(id);
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
    public void deletar(Usuario usuario) {
        try {
            String sql = "DELETE FROM usuario WHERE id = ?";

            conectar(sql);
            comando.setInt(1, usuario.getId());
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
            String sql = "DELETE FROM usuario WHERE id = ?";

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
    public void atualizar(Usuario usuario) {
        try {
            String sql = "UPDATE usuario SET nome=?, email=?, username=? "
                    + "WHERE id=?";

            conectar(sql);
            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.setString(3, usuario.getUsername());
            comando.setInt(4, usuario.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT id, nome, email, username FROM usuario";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");
                String username = resultado.getString("username");

                Usuario usu = new Usuario(id, nome, email, username);

                listaUsuarios.add(usu);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os usuarios do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaUsuarios);
    }

    @Override
    public Usuario procurarPorId(int id) {
        String sql = "SELECT id, nome, email, username FROM usuario WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");
                String username = resultado.getString("username");

                Usuario usu = new Usuario(id, nome, email, username);

                return usu;
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
