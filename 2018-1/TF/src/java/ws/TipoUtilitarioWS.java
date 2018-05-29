package ws;

import entidade.gTipoutilitarios;
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
import rn.TipoUtilitarioRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("tipoutilitarios")
public class TipoUtilitarioWS {
    private TipoUtilitarioRN tipoUtilitariosRN;
    @Context
    private UriInfo context;

    public TipoUtilitarioWS() {
        tipoUtilitariosRN = new TipoUtilitarioRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<gTipoutilitarios> getTipoUtilitarios() {
       /* try {*/
            return tipoUtilitariosRN.listar();
            
        /*} catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;*/
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gTipoutilitarios getTipoUtilitariosPorId(@PathParam("id") Long id){
        gTipoutilitarios tipoUtilitarios = tipoUtilitariosRN.buscarPorId(id);
        if(tipoUtilitarios == null) throw new NotFoundException();
        return tipoUtilitarios;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gTipoutilitarios adiciona(gTipoutilitarios tipoutilitarios, @Context HttpServletResponse response) throws Exception {
        gTipoutilitarios tipoUtilitariosGerado = tipoUtilitariosRN.inserir(tipoutilitarios);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return tipoUtilitariosGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gTipoutilitarios atualiza(@PathParam("id") Long id, gTipoutilitarios cursoTipoutilitarios) {
        gTipoutilitarios tipoUtilitariosGerado;
       
        try {
            tipoUtilitariosGerado = tipoUtilitariosRN.atualizar(id, cursoTipoutilitarios);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return tipoUtilitariosGerado;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gTipoutilitarios deleta(@PathParam("id") Long id){
        gTipoutilitarios tipoutilitarios = null;
        try{
            tipoutilitarios = tipoUtilitariosRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return tipoutilitarios;
    }
}