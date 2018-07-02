package rn;

import entidade.Motor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author LHRIES
 */
public class MotorRN {

    public Motor inserir(Motor motor) {
        EntityManager manager = JPAUtil.getManager();
        
        manager.getTransaction().begin();
        manager.persist(motor);
        manager.getTransaction().commit();
        
        manager.close();
        
        return motor;

    }

    public List<Motor> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<Motor> query = manager.createQuery("SELECT m FROM Motor m",Motor.class);
        List<Motor> listaMotores = query.getResultList();

        System.out.println("Motores:");
        for (Motor m : listaMotores) {
            System.out.println(m.getNome() + "-" + m.getDescricao() + "-" + m.getUso());
        }

        manager.close();

        return (listaMotores);

    }

    public Motor buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        Motor motor = manager.find(Motor.class, id);
        manager.close();
        return motor;
        
    }
    
    
    public Motor atualizar(Long id, Motor motor) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        
        Motor motorAtual = manager.find(Motor.class,id);
        
        if(motorAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
        if(motor.getNome()!=null && !motor.getNome().isEmpty())
            motorAtual.setNome(motor.getNome());
        if(motor.getDescricao()!=null && !motor.getDescricao().isEmpty())
            motorAtual.setDescricao(motor.getDescricao());
        if(motor.getUso()!=null && !motor.getUso().isEmpty())
            motorAtual.setUso(motor.getUso());

        manager.getTransaction().commit();
        
        manager.close();
        
        return motorAtual;
    }
    
    public Motor deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        Motor motorAtual = manager.find(Motor.class,id);

        if(motorAtual == null) throw new Exception();
        
        manager.getTransaction().begin();
        manager.remove(motorAtual);
        manager.getTransaction().commit();
        
        manager.close();
        
        return (motorAtual);
        

    }

}
