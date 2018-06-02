package ws;

import entidade.aUnidadeHabiltacao;
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
import rn.UnidadeHabilitacaoRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("unidadeabilitacao")
public class UnidadeHabilitacaoWS {
    private UnidadeHabilitacaoRN unidadeHabilitacaoRN;
    @Context
    private UriInfo context;

    public UnidadeHabilitacaoWS() {
        unidadeHabilitacaoRN = new UnidadeHabilitacaoRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<aUnidadeHabiltacao> getModalidadeCurso() {
        try {
            return unidadeHabilitacaoRN.listar();
            
        } catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aUnidadeHabiltacao getModalidadePorId(@PathParam("id") Long id){
        aUnidadeHabiltacao unidadeHabiltacao = unidadeHabilitacaoRN.buscarPorId(id);
        if(unidadeHabiltacao == null) throw new NotFoundException();
        return unidadeHabiltacao;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aUnidadeHabiltacao adiciona(aUnidadeHabiltacao unidadeHabiltacao, @Context HttpServletResponse response) throws Exception {
        aUnidadeHabiltacao unidadeHabiltacaoGerado = unidadeHabilitacaoRN.inserir(unidadeHabiltacao);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return unidadeHabiltacaoGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public aUnidadeHabiltacao atualiza(@PathParam("id") Long id, aUnidadeHabiltacao unidadeHabiltacao) {
        aUnidadeHabiltacao unidadeHabiltacaoGerado;
       
        try {
            unidadeHabiltacaoGerado = unidadeHabilitacaoRN.atualizar(id, unidadeHabiltacao);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return unidadeHabiltacaoGerado;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public aUnidadeHabiltacao deleta(@PathParam("id") Long id){
        aUnidadeHabiltacao unidadeHabiltacao = null;
        try{
            unidadeHabiltacao = unidadeHabilitacaoRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return unidadeHabiltacao;
    }
}