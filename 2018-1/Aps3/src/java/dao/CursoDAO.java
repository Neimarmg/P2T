package Dao;

import Dao.Jdbc.ConnectionFactory;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import model.Globais;
import model.Curso;



public class CursoDAO implements Serializable{
    ArrayList<Curso> listaDeCursos;
   
    public List<Curso> getListaDeProdutos() {
        return listaDeCursos;
    }
    
    private Connection con = ConnectionFactory.getConnection();
    
    public void carregaPessoa(Connection connection,PreparedStatement prepara, Curso curso) throws SQLException{
        prepara.setInt(Globais.getContador(true, false),curso.getIdModalidade());        
        prepara.setInt(Globais.getContador(true, false),curso.getIdProjtoCurso());
        prepara.setString(Globais.getContador(true, false),curso.getNomeCurso());     
    }
    
        
    public void inserir (Curso curso){
 
        ConnectionFactory.setSql("INSERT INTO acurso(idCurso, idModalidade, idProjtoCurso, nomeCurso) VALUES (?,?,?,?)");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(Globais.getContador(true, true),0);           
            carregaPessoa(con, prepara, curso); 
            ConnectionFactory.executaSql("Salva", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));
          
        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        }
    }
    
/*
    public void atualiza (Curso curso){

        ConnectionFactory.setSql("UPDATE produtos SET codProduto=?, descProduto=?, codMarca=?, valorNotacao=?, codNotacao=?, preco=? WHERE codProduto=?");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());           
            prepara.setInt(Globais.getContador(true, true), produtos.getCodProduto()); //Pula primeira posição da tabela 
            carregaPessoa(con, prepara, produtos);                   
            prepara.setInt(Globais.getContador(true, false),produtos.getCodProduto());             
            ConnectionFactory.executaSql("Altera", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));

        } catch(SQLException e){ 
            //se comando sql nao estiver correto ira imprimir o erro gerado
            e.printStackTrace();
        }
    }
    
    
    public void exclui (Produtos produtos){
        ConnectionFactory.setSql("DELETE FROM pessoa WHERE codPessoa=?");
        try{
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(1,produtos.getCodProduto()); //deletando pelo id que eh inteiro
            ConnectionFactory.executaSql("apaga", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true ));

        } catch(SQLException e){ 
            e.printStackTrace();
        }
    }
    
    
    public List<Produtos> listarTodos(String idProduto){ //procurar todos nao tem parametr00o
        ConnectionFactory.setSql("CALL cProdutos("+idProduto+");");

        try{
            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet

            while(resultado.next()){ //buscando valor das colunas, registro por registro
                Produtos produtos  = new Produtos();
                
                    produtos.setCodProduto(resultado.getInt("codProduto"));
                    produtos.setDescProruto(resultado.getString("descProduto"));
                    produtos.setCodMarca(resultado.getInt("codMarca"));
                    produtos.setNomeMarca(resultado.getString("marca"));                        
                    produtos.setValorNotacao(resultado.getFloat("valorNotacao"));
                    produtos.setNotacao(resultado.getString("notacao"));
                    produtos.setPreco(resultado.getFloat("preco"));        
 
                listaDeProdutos.add(produtos);
            }
            ConnectionFactory.fechaConexao(con, prepara, true );

            //View.msgr("Listando Todos os Registros");
            
            } catch(SQLException e){ 
                    //se comando sql nao estiver correto ira imprimir o erro gerado
                    e.printStackTrace();
            }

            return listaDeProdutos;*/
    }