package Dao.Jdbc;

import static Dao.Jdbc.bdConect.getSENHA;
import static Dao.Jdbc.bdConect.getURL;
import static Dao.Jdbc.bdConect.getUSUARIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import view.View;

/**
 *
 * @author lhries
 */
public class ConnectionFactory {
    static String sql;
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conexao = DriverManager.getConnection(getURL(), getUSUARIO(), getSENHA());
            
        } catch (ClassNotFoundException ex) {
            View.msg("Erro de Sistema - Classe do Driver Nao Encontrada!");
            throw new BDException(ex);
        } catch (SQLException ex) {
            View.msg("Erro de Sistema - Problema na conexão do banco de dados");
            throw new BDException(ex);
        }
        return(conexao);
    }
        
    
    public static boolean fechaConexao(Connection connection,PreparedStatement prepara, boolean fechaConexao) throws SQLException{
        if(fechaConexao == true){
            prepara.close();
            return  true;           
        }
         return  false;        
    }
    
    
    public  static  void executaSql(String acao,boolean  status,boolean fecharConexao)throws SQLException{
    
        if (status == false){
            View.msgcr("Registro "+acao +"do com sucesso!");
        }else{
            View.msgcr("Não foi possível  "+acao +"r registro!");
        }
     }

    
    public static String getSql() {
        return sql;
    }

    
    public static void setSql(String sql) {
        ConnectionFactory.sql = sql;
    }  

    public static void fechaConexao(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
