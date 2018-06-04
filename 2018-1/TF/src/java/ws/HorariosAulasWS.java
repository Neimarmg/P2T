package ws;

import entidade.aHorariosAulas;
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
import rn.HorariosAulasRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("horariosaulas")
public class HorariosAulasWS {
    private HorariosAulasRN horariosAulasRN;
    @Context
    private UriInfo context;

    public HorariosAulasWS() {
        horariosAulasRN = new HorariosAulasRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<aHorariosAulas> getHorariosAulas () {
        try {
            return horariosAulasRN.listar();
            
        } catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aHorariosAulas getHorariosAulasPorId(@PathParam("id") Long id){
        aHorariosAulas horariosAulas = horariosAulasRN.buscarPorId(id);
        if(horariosAulas == null) throw new NotFoundException();
        return horariosAulas;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aHorariosAulas adiciona(aHorariosAulas horariosAulas, @Context HttpServletResponse response) throws Exception {
        aHorariosAulas horariosAulasGerado = horariosAulasRN.inserir(horariosAulas);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return horariosAulasGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aHorariosAulas atualiza(@PathParam("id") Long id, aHorariosAulas horariosAulas) {
        aHorariosAulas horariosAulasGerado;
       
        try {
            horariosAulasGerado = horariosAulasRN.atualizar(id, horariosAulas);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return horariosAulasGerado;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aHorariosAulas deleta(@PathParam("id") Long id){
        aHorariosAulas horariosAulas = null;
        try{
            horariosAulas = horariosAulasRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return horariosAulas;
    }
}