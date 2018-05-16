package ws;

import entidade.gPessoa;
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
import rn.PessoaRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("pessoa")
public class PessoaWS {
    private PessoaRN pessoaRN;
    @Context
    private UriInfo context;

    public PessoaWS() {
        pessoaRN = new PessoaRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<gPessoa> getPessoa() {
       /* try {*/
            return pessoaRN.listar();
            
        /*} catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;*/
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gPessoa getModalidadePorId(@PathParam("id") Long id){
        gPessoa pessoa = pessoaRN.buscarPorId(id);
        if(pessoa == null) throw new NotFoundException();
        return pessoa;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gPessoa adiciona(gPessoa pessoa, @Context HttpServletResponse response) throws Exception {
        gPessoa pessoaGerada = pessoaRN.inserir(pessoa);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return pessoaGerada;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gPessoa atualiza(@PathParam("id") Long id, gPessoa pessoa) {
        gPessoa pessoaGerada;
       
        try {
            pessoaGerada = pessoaRN.atualizar(id, pessoa);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return pessoaGerada;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gPessoa deleta(@PathParam("id") Long id){
        gPessoa pessoa = null;
        try{
            pessoa = pessoaRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return pessoa;
    }
}