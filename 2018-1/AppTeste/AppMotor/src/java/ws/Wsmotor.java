/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.Motor;

/**
 * REST Web Service
 *
 * @author 181100053
 */
@Path("motor")
public class Wsmotor {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Wsmotor
     */
    public Wsmotor() {
    }

    /**
     * Retrieves representation of an instance of App.Wsmotor
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void inseireMotor(Motor motor) {
        
        EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory("AppMotor");
        EntityManager manager = factory.createEntityManager();
        
        /*
        motor.setNome("motor");
        motor.setDescricao("descricao");
        motor.setUso("uso");
        */
        manager.getTransaction().begin();
        manager.persist(motor);
        manager.getTransaction().commit();
        
        manager.close();
        factory.close();
        
    }

    /**
     * PUT method for updating or creating an instance of Wsmotor
     * @param content representation for the resource
     */
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
