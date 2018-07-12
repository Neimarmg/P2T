package ws;

import entidade.yUsuarios;
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
import rn.UsuarioRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("usuario")
public class UsuarioWS {
    private UsuarioRN usuarioRN;
    @Context
    private UriInfo context;

    public UsuarioWS() {
        usuarioRN = new UsuarioRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<yUsuarios> getListaUsuario() {
       /* try {*/
            return usuarioRN.listar();
            
        /*} catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;*/
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public yUsuarios getUsuarioPorId(@PathParam("id") Long id){
        yUsuarios usuarios = usuarioRN.buscarPorId(id);
        if(usuarios == null) throw new NotFoundException();
        return usuarios;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public yUsuarios adiciona(yUsuarios usuarios, @Context HttpServletResponse response) throws Exception {
        yUsuarios usuarioGerado = usuarioRN.inserir(usuarios);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return usuarioGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public yUsuarios atualiza(@PathParam("id") Long id, yUsuarios usuarios) {
        yUsuarios usuarioGerado;
       
        try {
            usuarioGerado = usuarioRN.atualizar(id, usuarios);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return usuarioGerado;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public yUsuarios deleta(@PathParam("id") Long id){
        yUsuarios usuarios = null;
        try{
            usuarios = usuarioRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return usuarios;
    }
}