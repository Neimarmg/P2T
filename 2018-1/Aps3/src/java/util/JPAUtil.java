package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author LHRIES
 */
public class JPAUtil {
    private static String PUNAME = "Aps3PU";
    private static EntityManagerFactory factory = 
            Persistence.createEntityManagerFactory(PUNAME);
   
    public static EntityManager getManager(){
        return (factory.createEntityManager());
    }

}
