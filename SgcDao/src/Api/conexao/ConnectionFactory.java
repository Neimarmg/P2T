package Api.conexao;


import Api.conexao.BDException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author lhries
 */
public class ConnectionFactory {
    private final static String HOST = "localhost";
    private final static String PORT = "3306";
    private final static String BD = "bdsgc";
    private final static String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+BD;
    private final static String USUARIO = "root";
    private final static String SENHA = "";
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro de Sistema - Classe do Driver Nao Encontrada!");
            throw new BDException(ex);
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema na conexão do banco de dados");
            throw new BDException(ex);
        }
        return(conexao);
    }
    
}
