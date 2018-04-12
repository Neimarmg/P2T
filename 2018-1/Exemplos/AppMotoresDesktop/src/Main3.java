


import entidade.Motor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * @author lhries
 * Buscar todos / listar
 */
public class Main3 {

    public static void main(String[] args) {
        EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory("AppMotoresDesktopPU");

        EntityManager manager = factory.createEntityManager();
        
        Query query = manager.createQuery("SELECT m FROM Motor m");
        List<Motor> listaMotores = query.getResultList();
        
        System.out.println("Motores:");
        for(Motor m: listaMotores){
            System.out.println(m.getNome()+"-"+m.getDescricao()+"-"+m.getUso());
        }
        
      
        manager.close();
        factory.close();
    }
}
