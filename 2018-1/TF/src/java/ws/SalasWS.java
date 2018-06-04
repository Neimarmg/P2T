package ws;

import entidade.gSalas;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import rn.SalasRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("salas")
public class SalasWS {
    private SalasRN salasRN;
    @Context
    private UriInfo context;

    public SalasWS() {
        salasRN = new SalasRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<gSalas> getSala() {
        try {
            return salasRN.listar();
            
        } catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gSalas getSalaPorId(@PathParam("id") Long id){
        gSalas salas = salasRN.buscarPorId(id);
        if(salas == null) throw new NotFoundException();
        return salas;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gSalas adiciona(gSalas salas, @Context HttpServletResponse response) throws Exception {
        gSalas salasGerada = salasRN.inserir(salas);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return salasGerada;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gSalas atualiza(@PathParam("id") Long id, gSalas salas) {
        gSalas salasGerada;
       
        try {
            salasGerada = salasRN.atualizar(id, salas);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return salasGerada;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gSalas deleta(@PathParam("id") Long id){
        gSalas salas = null;
        try{
            salas = salasRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return salas;
    }
}