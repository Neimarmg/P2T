/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entidade.Motor;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author lhries
 */
@Path("motor")
public class MotorWS {
    
    static List<Motor> listaMotores;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MotorWS
     */
    public MotorWS() {
        if(listaMotores==null){
            listaMotores = new ArrayList<>();
            listaMotores.add(new Motor(1,"Motor1","Descricao1","uso1"));
            listaMotores.add(new Motor(2,"Motor2","Descricao2","uso2"));
            listaMotores.add(new Motor(3,"Motor3","Descricao3","uso3"));
        }
    }

    /**
     * Retrieves representation of an instance of ws.MotorWS
     * @return an instance of entidade.Motor
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Motor> getMotores() {
        return listaMotores;
    }

    /**
     * PUT method for updating or creating an instance of MotorWS
     * @param motor representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void inserirMotor(Motor motor) {
        listaMotores.add(motor);
    }
}
