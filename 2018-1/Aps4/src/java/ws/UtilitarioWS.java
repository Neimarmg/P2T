package ws;

import entidade.aCurso;
import entidade.gUtilitarios;
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
import rn.UtilitarioRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("utilitarios")
public class UtilitarioWS {
    private UtilitarioRN utilitarioRN;
    @Context
    private UriInfo context;

    public UtilitarioWS() {
        utilitarioRN = new UtilitarioRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<gUtilitarios> getUtilitario() {
       /* try {*/
            return utilitarioRN.listar();
            
        /*} catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;*/
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gUtilitarios getModalidadePorId(@PathParam("id") Long id){
        gUtilitarios utilitarios = utilitarioRN.buscarPorId(id);
        if(utilitarios == null) throw new NotFoundException();
        return utilitarios;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gUtilitarios adiciona(gUtilitarios utilitarios, @Context HttpServletResponse response) throws Exception {
        gUtilitarios utilitarioGerado = utilitarioRN.inserir(utilitarios);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return utilitarioGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gUtilitarios atualiza(@PathParam("id") Long id, gUtilitarios utilitarios) {
        gUtilitarios utilitarioGerado;
       
        try {
            utilitarioGerado = utilitarioRN.atualizar(id, utilitarios);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return utilitarioGerado;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gUtilitarios deleta(@PathParam("id") Long id){
        gUtilitarios utilitarios = null;
        try{
            utilitarios = utilitarioRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return utilitarios;
    }
}