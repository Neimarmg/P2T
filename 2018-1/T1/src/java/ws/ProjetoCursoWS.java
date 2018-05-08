package ws;

import entidade.aCurso;
import entidade.aProjetocurso;
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
import rn.ProjetocursoRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("projetocurso")
public class ProjetoCursoWS {
    private ProjetocursoRN projetocursoRN;
    @Context
    private UriInfo context;

    public ProjetoCursoWS() {
        projetocursoRN = new ProjetocursoRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<aProjetocurso> getProjetoCurso() {
       /* try {*/
            return projetocursoRN.listar();
            
        /*} catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;*/
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aProjetocurso getProjetoPorId(@PathParam("id") Long id){
        aProjetocurso projetocurso = projetocursoRN.buscarPorId(id);
        if(projetocurso == null) throw new NotFoundException();
        return projetocurso;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aProjetocurso adiciona(aProjetocurso projetocurso, @Context HttpServletResponse response) throws Exception {
        aProjetocurso projetoGerado = projetocursoRN.inserir(projetocurso);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return projetoGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aProjetocurso atualiza(@PathParam("id") Long id, aProjetocurso projetocurso) {
        aProjetocurso projetoGerado;
       
        try {
            projetoGerado = projetocursoRN.atualizar(id, projetocurso);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return projetoGerado;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aProjetocurso deleta(@PathParam("id") Long id){
        aProjetocurso projetocurso = null;
        try{
            projetocurso = projetocursoRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return projetocurso;
    }
}