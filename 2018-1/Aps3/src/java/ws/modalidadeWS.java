package ws;

import entidade.aModalidadecurso;
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
import rn.ModalidadeCursoRN;

/**
 * REST Web Service
 *
 * @author LHRIES
 */
@Path("modalidadecurso")
public class modalidadeWS {
    private ModalidadeCursoRN modalidadeCursoRN;
    @Context
    private UriInfo context;

    public modalidadeWS() {
        modalidadeCursoRN = new ModalidadeCursoRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<aModalidadecurso> getModalidadeCurso() {
       /* try {*/
            return modalidadeCursoRN.listar();
            
        /*} catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;*/
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aModalidadecurso getModalidadePorId(@PathParam("id") Long id){
        aModalidadecurso modalidadecurso = modalidadeCursoRN.buscarPorId(id);
        if(modalidadecurso == null) throw new NotFoundException();
        return modalidadecurso;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aModalidadecurso adiciona(aModalidadecurso modalidadecurso, @Context HttpServletResponse response) {
        aModalidadecurso modalidadeGerada = modalidadeCursoRN.inserir(modalidadecurso);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return modalidadeGerada;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aModalidadecurso atualiza(@PathParam("id") Long id, aModalidadecurso modalidadecurso) {
        
        
        aModalidadecurso modalidadeGerada;
       
        try {
            modalidadeGerada = modalidadeCursoRN.atualizar(id, modalidadecurso);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return modalidadeGerada;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aModalidadecurso deleta(@PathParam("id") Long id){
        aModalidadecurso modalidadecurso = null;
        try{
            modalidadecurso = modalidadeCursoRN.deletar(id);
        }
        catch(Exception e){
            throw new NotFoundException();
        }
        return modalidadecurso;
    }
}
