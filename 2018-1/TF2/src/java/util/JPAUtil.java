package util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author LHRIES
 */
public class JPAUtil {
    private static String PUNAME = "TFPU";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
   
    public static EntityManager getManager(){
        return (factory.createEntityManager());
    }
    
    
    public void execInsert(Object classe, boolean fecharConexao){
        EntityManager manager = JPAUtil.getManager();
        try {
            manager.getTransaction().begin();
            manager.persist(classe);
            manager.getTransaction().commit();
        } catch (Exception e) {
           manager.getTransaction().rollback();
        }finally{
            fecharConexao(manager, fecharConexao);
           
        }
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
    
    
    public Date convertToDatabaseColumn(LocalDate attribute) {
	Instant instant = attribute.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	return Date.from(instant);
    }
    
    
    
    
    
    
    
}






/*
phpmyadmin: mysql.pep.kinghost.net
Usuário: pep
Senha: 84d235g4r8h

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date data = format.parse(tuaVariavelString);


     <property name="javax.persistence.jdbc.url" value="jdbc:mysql://bdacademico.mysql.uhserver.com:3306/bdacademico?zeroDateTimeBehavior=convertToNull"/>
      <property name="javax.persistence.jdbc.user" value="bdacd"/>
      <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
      <property name="javax.persistence.jdbc.password" value="masns4@54541E"/>
      <property name="javax.persistence.schema-generation.database.action" value="create"/>
*/