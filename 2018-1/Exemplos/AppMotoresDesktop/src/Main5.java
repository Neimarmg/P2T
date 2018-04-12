
import entidade.Motor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author LHRIES
 * Deletar / Remover
 */
public class Main5 {
    public static void main(String[] args) {
        EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory("AppMotoresDesktopPU");
        EntityManager manager = factory.createEntityManager();
        
        //1- Buscar o motor (e imprime)
        Motor motor = manager.find(Motor.class, 1L);
        System.out.println("Motor");
        System.out.println(motor.getNome()+"-"+motor.getDescricao()+"-"+motor.getUso());
        
        //2- Transacao e remove o motor
        manager.getTransaction().begin();
        manager.remove(motor);
        manager.getTransaction().commit();
        
        
        manager.close();
        factory.close();
        
    }
}
