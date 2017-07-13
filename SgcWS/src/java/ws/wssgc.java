/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import Model.Pessoa;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author neimarmoises
 */
@Path("gereric")
public class wssgc {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of wssgc
     */
    public wssgc() {
    }

    /**
     * Retrieves representation of an instance of ws.wssgc
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void inserirNovo(){
        Pessoa pessoa = new Pessoa(1, "Neimar",1, 1);
       
    }


    /**
     * PUT method for updating or creating an instance of wssgc
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
