/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 181100053
 */
public class jpaUtil {
    EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory("AppMotor");
        EntityManager manager = factory.createEntityManager();      

            
    public void meneger(boolean conectar) {
        
        manager.getTransaction().begin();
        manager.getTransaction().commit();
        
        if (conectar == false){
            manager.close();
            factory.close();
        }
    }
    
    
   

}
