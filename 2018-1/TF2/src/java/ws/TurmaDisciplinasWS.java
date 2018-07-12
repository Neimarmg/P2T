package ws;

import entidade.aTurmaDisciplinas;
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
import rn.TurmaDisciplinasRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("turmadisciplinas")
public class TurmaDisciplinasWS {
    private TurmaDisciplinasRN turmaDisciplinasRN;
    @Context
    private UriInfo context;

    public TurmaDisciplinasWS() {
        turmaDisciplinasRN = new TurmaDisciplinasRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<aTurmaDisciplinas> getTuromaDisciplina() {
        try {
            return turmaDisciplinasRN.listar();
            
       } catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aTurmaDisciplinas getTuromaDisciplinaPorId(@PathParam("id") Long id){
        aTurmaDisciplinas turmaDisciplinas = turmaDisciplinasRN.buscarPorId(id);
        if(turmaDisciplinas == null) throw new NotFoundException();
        return turmaDisciplinas;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aTurmaDisciplinas adiciona(aTurmaDisciplinas turmaDisciplinas, @Context HttpServletResponse response) throws Exception {
        aTurmaDisciplinas turmaDisciplinasGerada = turmaDisciplinasRN.inserir(turmaDisciplinas);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return turmaDisciplinasGerada;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aTurmaDisciplinas atualiza(@PathParam("id") Long id, aTurmaDisciplinas curso) {
        aTurmaDisciplinas turmaDisciplinasGerada;
       
        try {
            turmaDisciplinasGerada = turmaDisciplinasRN.atualizar(id, curso);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return turmaDisciplinasGerada;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aTurmaDisciplinas deleta(@PathParam("id") Long id){
        aTurmaDisciplinas turmaDisciplinas = null;
        try{
            turmaDisciplinas = turmaDisciplinasRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return turmaDisciplinas;
    }
}