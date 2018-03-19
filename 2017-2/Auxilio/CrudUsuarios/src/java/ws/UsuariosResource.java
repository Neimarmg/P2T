package ws;

import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Usuario;
import negocio.UsuarioNegocio;

/**
 * REST Web Service
 *
 * @author lhries
 */
@Path("usuario")
public class UsuariosResource {

    @Context
    private UriInfo context;
    
    @EJB
    private UsuarioNegocio usuarioNegocio;
    
    /**
     * Creates a new instance of UsuariosResource
     */
    public UsuariosResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getListaUsuarios(@Context final HttpServletResponse response) {
        response.setHeader("Acess-Control-Allow-Origin", "*");
        return usuarioNegocio.listar();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarUsuario(Usuario u, @Context final HttpServletResponse response){
        try {
            usuarioNegocio.inserir(u);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.flushBuffer();
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuariosPorId(@PathParam("id") int id){
        return (usuarioNegocio.buscarPorId(id));
    }
    
    @DELETE
    @Path("{id}")
    public void deletarUsuario(@PathParam("id") int idUsuario) {
        try{
            usuarioNegocio.deletar(idUsuario);
        }catch(Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterarUsuario(Usuario u, @PathParam("id") int id){
        try{
            usuarioNegocio.atualizar(u, id);
        }
        catch(Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }
            
}

    

