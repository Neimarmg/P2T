/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Curso;

/**
 * REST Web Service
 *
 * @author neimarmoises
 */
@Path("curso")
public class cursows {
    
    static List<Curso> listaCursos;
    static Curso curso = new Curso();   
    static Gson gson = new Gson();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of cursows
     */
    public cursows() {
       
    }
    
    public void insere(Curso curso){
        listaCursos = new ArrayList<Curso>();
        for (int i = 0; i <=5; i++) {
           cursows.listaCursos.add(new Curso(i, i+6, i+2, "Direito "+i));
        }
    }

    /**
     * Retrieves representation of an instance of ws.cursows
     * @return an instance of java.lang.String
     * @throws java.lang.Exception
     */
    @POST   
    @Path("/novo")
    @Produces(MediaType.APPLICATION_JSON)  
    public void insereCurso() throws Exception{       
        insere(curso);     
    }

    @PUT   
    @Produces(MediaType.APPLICATION_JSON)    
    @Path("/lista")
    public String listaCurso() {  
        return gson.toJson(listaCursos);     
    }
    
    @GET
    @Path("{id}")
    public String buscaCurso(@PathParam("id") int idCurso)throws Exception{
        try {
            return gson.toJson(listaCursos.get(idCurso)); 
        
        } catch (IndexOutOfBoundsException e) {
            return "Elemento inexitente";
        }catch (NullPointerException e){
            return "Lista vazia";
        }             
    }
       
    @DELETE
    @Path("{id}")
    public String deletarCuCuso(@PathParam("id") int idCurso) {
        try {
           return gson.toJson(listaCursos.remove(idCurso));
           
        } catch (IndexOutOfBoundsException e) {
            return "Elemento inexitente";
        }catch (NullPointerException e){
            return "Lista vazia";
        }
    } 
}
