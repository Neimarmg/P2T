/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entidade.Motor;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author 181100053
 */
@Path("motorws")
public class Motorws {
    static ArrayList<Object> listaMotores = new ArrayList<>();
    Motorws motorws;
    @Context
    
    private UriInfo context;

    /**
     * Creates a new instance of MotorwsResource
     */
    public Motorws() {
        for (int i = 0; i < 2; i++) { 
            listaMotores.add(new Motor(i, "sas"+i, "Fuscas"+i, "kombi"));
        }        
    }

    /**
     * Retrieves representation of an instance of ws.MotorwsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Object> getJson() {
        //TODO return proper representation object
        return listaMotores;//throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of MotorwsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Object> inserirMotor(Motor motor) {
        listaMotores.add(motor);
        return listaMotores;
    }
    
}
