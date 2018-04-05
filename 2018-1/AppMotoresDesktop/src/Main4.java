
import entidade.Motor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author LHRIES
 * Atualizar
 */
public class Main4 {
    public static void main(String[] args) {
        EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory("AppMotoresDesktopPU");
        EntityManager manager = factory.createEntityManager();
        
        //1- Buscar o motor (e imprime) - cuidado com id
        Motor motor = manager.find(Motor.class, 1L);
        System.out.println("Motor");
        System.out.println(motor.getNome()+"-"+motor.getDescricao()+"-"+motor.getUso());
        
        //2- Inicia a Transacao, atualiza o objeto e atualiza no BD (commit)
        manager.getTransaction().begin();
        motor.setNome("MotorX");
        manager.getTransaction().commit();
        
        
        manager.close();
        factory.close();
        
    }
}
