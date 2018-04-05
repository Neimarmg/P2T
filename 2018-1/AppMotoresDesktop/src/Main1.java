
import entidade.Motor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author LHRIES
 * Inserir
 */
public class Main1 {
    public static void main(String[] args) {
        EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory("AppMotoresDesktopPU");
        EntityManager manager = factory.createEntityManager();
        
        Motor motor1 = new Motor();
        motor1.setNome("motor1");
        motor1.setDescricao("descricao1");
        motor1.setUso("uso1");
        
        manager.getTransaction().begin();
        manager.persist(motor1);
        manager.getTransaction().commit();
        
        manager.close();
        factory.close();
    }
}
