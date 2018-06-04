package ws;

import entidade.aHorarioProfessor;
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
import rn.HorarioProfessorRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("ahorarioprofessor")
public class HorarioProfessorWS {
    private HorarioProfessorRN horarioProfessorRN;
    @Context
    private UriInfo context;

    public HorarioProfessorWS() {
        horarioProfessorRN = new HorarioProfessorRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<aHorarioProfessor> getaHorarioProfessor() {
       try {
            return horarioProfessorRN.listar();
            
        } catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aHorarioProfessor getaHorarioProfessorPorId(@PathParam("id") Long id){
        aHorarioProfessor horarioProfessor = horarioProfessorRN.buscarPorId(id);
        if(horarioProfessor == null) throw new NotFoundException();
        return horarioProfessor;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aHorarioProfessor adiciona(aHorarioProfessor horarioProfessor, @Context HttpServletResponse response) throws Exception {
        aHorarioProfessor horarioProfessorGerada = horarioProfessorRN.inserir(horarioProfessor);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return horarioProfessorGerada;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aHorarioProfessor atualiza(@PathParam("id") Long id, aHorarioProfessor horarioProfessor) {
        aHorarioProfessor horarioProfessorGerada;
       
        try {
            horarioProfessorGerada = horarioProfessorRN.atualizar(id, horarioProfessor);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return horarioProfessorGerada;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aHorarioProfessor deleta(@PathParam("id") Long id){
        aHorarioProfessor horarioProfessor = null;
        try{
            horarioProfessor = horarioProfessorRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return horarioProfessor;
    }
}