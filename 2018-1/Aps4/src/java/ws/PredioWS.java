package ws;

import entidade.gPredio;
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
import rn.PredioRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("predio")
public class PredioWS {
    private PredioRN predioRN;
    @Context
    private UriInfo context;

    public PredioWS() {
        predioRN = new PredioRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<gPredio> getPredio() {
        try {
            return predioRN.listar();
            
        } catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gPredio getPredioPorId(@PathParam("id") Long id){
        gPredio predio = predioRN.buscarPorId(id);
        if(predio == null) throw new NotFoundException();
        return predio;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gPredio adiciona(gPredio predio, @Context HttpServletResponse response) throws Exception {
        gPredio predioGerado = predioRN.inserir(predio);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return predioGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gPredio atualiza(@PathParam("id") Long id, gPredio predio) {
        gPredio predioGerado;
       
        try {
            predioGerado = predioRN.atualizar(id, predio);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return predioGerado;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gPredio deleta(@PathParam("id") Long id){
        gPredio predio = null;
        try{
            predio = predioRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return predio;
    }
}