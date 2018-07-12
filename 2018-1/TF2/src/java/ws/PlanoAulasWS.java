package ws;

import entidade.aPlanoDeAula;
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
import rn.PlanoAulaRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("planodeaula")
public class PlanoAulasWS {
    private PlanoAulaRN planoAulaRN;
    @Context
    private UriInfo context;

    public PlanoAulasWS() {
        planoAulaRN = new PlanoAulaRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<aPlanoDeAula> getPlanoDeAulas() {
        try {
            return planoAulaRN.listar();
            
        } catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aPlanoDeAula getPlanoDeAulasPorId(@PathParam("id") Long id){
        aPlanoDeAula planoDeAula = planoAulaRN.buscarPorId(id);
        if(planoDeAula == null) throw new NotFoundException();
        return planoDeAula;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aPlanoDeAula adiciona(aPlanoDeAula planoDeAula, @Context HttpServletResponse response) throws Exception {
        aPlanoDeAula planoDeAulaGerado = planoAulaRN.inserir(planoDeAula);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return planoDeAulaGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aPlanoDeAula atualiza(@PathParam("id") Long id, aPlanoDeAula planoDeAula) {
        aPlanoDeAula planoDeAulaGerado;
       
        try {
            planoDeAulaGerado = planoAulaRN.atualizar(id, planoDeAula);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return planoDeAulaGerado;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aPlanoDeAula deleta(@PathParam("id") Long id){
        aPlanoDeAula planoDeAula = null;
        try{
            planoDeAula = planoAulaRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return planoDeAula;
    }
}