import entidade.Motor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author lhries
 * Buscar por Id
 */
public class Main2 {

    public static void main(String[] args) {
        EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory("AppMotoresDesktopPU");
        EntityManager manager = factory.createEntityManager();
        Motor motor = manager.find(Motor.class, 1L);
        
        System.out.println("Motor");
        System.out.println(motor.getNome()+"-"+motor.getDescricao()+"-"+motor.getUso());
        
        manager.close();
        factory.close();
    }
}
