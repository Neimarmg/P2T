/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebSevice;

import Dao.CursoDAO;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Curso;

/**
 * REST Web Service
 *
 * @author neimarmoises
 */
@Path("curso")
public class CursoWs {
    static ArrayList<Object> listaMotores = new ArrayList<>();
    
    @Context
    
    private UriInfo context;

    /**
     * Creates a new instance of MotorwsResource
     */
    public CursoWs() {
        for (int i = 0; i < 2; i++) { 
            listaMotores.add(new Curso(i, i+2 ,i+9, "Direito"));
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
    public String listar(String content) {
        return listaMotores.L;
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Object> inserirMotor(Curso curso) {
        listaMotores.add(curso);
        return listaMotores;
    }
    
    @DELETE
    
   
}
