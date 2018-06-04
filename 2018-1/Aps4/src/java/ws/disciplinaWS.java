package ws;

import entidade.aDisciplinas;
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
import rn.DisciplinaRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("disciplina")
public class disciplinaWS {
    private DisciplinaRN disciplinaRN;
    @Context
    private UriInfo context;

    public disciplinaWS() {
        disciplinaRN = new DisciplinaRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<aDisciplinas> getDisciplinas() {
       /* try {*/
            return disciplinaRN.listar();
            
        /*} catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;*/
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aDisciplinas getDisciplinaPorId(@PathParam("id") Long id){
        aDisciplinas disciplinas = disciplinaRN.buscarPorId(id);
        if(disciplinas == null) throw new NotFoundException();
        return disciplinas;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aDisciplinas adiciona(aDisciplinas disciplinas, @Context HttpServletResponse response) throws Exception {
        aDisciplinas disciplinaGerada = disciplinaRN.inserir(disciplinas);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return disciplinaGerada;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aDisciplinas atualiza(@PathParam("id") Long id, aDisciplinas disciplinas) {
        aDisciplinas disciplinaGerada;
       
        try {
            disciplinaGerada = disciplinaRN.atualizar(id, disciplinas);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return disciplinaGerada;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aDisciplinas deleta(@PathParam("id") Long id){
        aDisciplinas disciplinas = null;
        try{
            disciplinas = disciplinaRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return disciplinas;
    }
}