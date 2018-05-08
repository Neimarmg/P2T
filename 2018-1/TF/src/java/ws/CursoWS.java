package ws;

import entidade.aCurso;
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
import rn.CursoRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("curso")
public class CursoWS {
    private CursoRN cursoRN;
    @Context
    private UriInfo context;

    public CursoWS() {
        cursoRN = new CursoRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<aCurso> getModalidadeCurso() {
       /* try {*/
            return cursoRN.listar();
            
        /*} catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;*/
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aCurso getModalidadePorId(@PathParam("id") Long id){
        aCurso curso = cursoRN.buscarPorId(id);
        if(curso == null) throw new NotFoundException();
        return curso;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aCurso adiciona(aCurso curso, @Context HttpServletResponse response) throws Exception {
        aCurso cursoGerado = cursoRN.inserir(curso);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return cursoGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aCurso atualiza(@PathParam("id") Long id, aCurso curso) {
        aCurso cursoGerado;
       
        try {
            cursoGerado = cursoRN.atualizar(id, curso);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return cursoGerado;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aCurso deleta(@PathParam("id") Long id){
        aCurso curso = null;
        try{
            curso = cursoRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return curso;
    }
}