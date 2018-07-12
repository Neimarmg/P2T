package ws;

import entidade.aProfessor;
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
import rn.ProfessorRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("professor")
public class ProfessorWS {
    private ProfessorRN professorRN;
    @Context
    private UriInfo context;

    public ProfessorWS() {
        professorRN = new ProfessorRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<aProfessor> getProfessor() {
       try {
            return professorRN.listar();
            
        } catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aProfessor getProfessorPorId(@PathParam("id") Long id){
        aProfessor professor = professorRN.buscarPorId(id);
        if(professor == null) throw new NotFoundException();
        return professor;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aProfessor adiciona(aProfessor professor, @Context HttpServletResponse response) throws Exception {
        aProfessor professorGerada = professorRN.inserir(professor);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return professorGerada;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aProfessor atualiza(@PathParam("id") Long id, aProfessor professor) {
        aProfessor professorGerada;
       
        try {
            professorGerada = professorRN.atualizar(id, professor);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return professorGerada;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aProfessor deleta(@PathParam("id") Long id){
        aProfessor professor = null;
        try{
            professor = professorRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return professor;
    }
}