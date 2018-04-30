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
    private static String PUNAME = "Aps3PU";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
   
    public static EntityManager getManager(){
        return (factory.createEntityManager());
    }
    
    
    public void executaInsert(Object classe, boolean fecharConexao){
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        manager.persist(classe);
        manager.getTransaction().commit();
        fecharConexao(manager, fecharConexao);

    }
    
       
    public Object deletar(EntityManager manager,Object intenAtual, boolean fecharConexao) throws Exception{
        //EntityManager manager = JPAUtil.getManager();
        //aModalidadecurso itenAtual = intenAtual;

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