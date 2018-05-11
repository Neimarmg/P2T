package util;

import entidade.aModalidadecurso;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author LHRIES
 */
public class JPAUtil {
    private static String PUNAME = "tfPU";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
   
    public static EntityManager getManager(){
        return (factory.createEntityManager());
    }
    
    
    public void execInsert(Object classe, boolean fecharConexao){
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        manager.persist(classe);
        manager.getTransaction().commit();
        fecharConexao(manager, fecharConexao);

    }
    
       
    public Object execDelete(EntityManager manager,Object intenAtual, boolean fecharConexao) throws Exception{
        if(intenAtual == null) throw new Exception();        
        
        manager.getTransaction().begin();
        manager.remove(intenAtual);
        manager.getTransaction().commit();
        
        fecharConexao(manager, fecharConexao);
        
        return intenAtual;
    }
    
    
    public void fecharConexao(EntityManager manager, boolean fecharConexao){
            if (fecharConexao = true){
            manager.close();
        }
    }
}






/*
phpmyadmin: mysql.pep.kinghost.net
Usuário: pep
Senha: 84d235g4r8h
*/