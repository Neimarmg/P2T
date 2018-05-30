package ws;

import entidade.gMenus;
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
import rn.MemusRN;

/**
 * REST Web Service
 *
 *  @author neimarmoises
 */
@Path("menus")
public class MenusWS {
    private MemusRN memusRN;
    @Context
    private UriInfo context;

    public MenusWS() {
        memusRN = new MemusRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<gMenus> getListaMenus() {
       /* try {*/
            return memusRN.listar();
            
        /*} catch (IllegalArgumentException e) {
               e.getMessage();
        }
        return null;*/
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gMenus getModalidadePorId(@PathParam("id") Long id){
        gMenus menus = memusRN.buscarPorId(id);
        if(menus == null) throw new NotFoundException();
        return menus;
    }

    @POST
    @Path("/novo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gMenus adiciona(gMenus menus, @Context HttpServletResponse response) throws Exception {
        gMenus menusGerado = memusRN.inserir(menus);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
        return menusGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gMenus atualiza(@PathParam("id") Long id, gMenus menus) {
        gMenus menusGerado;
       
        try {
            menusGerado = memusRN.atualizar(id, menus);
        } catch (Exception e) {
            throw new NotFoundException();
        }
        
        return menusGerado;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public gMenus deleta(@PathParam("id") Long id){
        gMenus menus = null;
        try{
            menus = memusRN.deletar(id);
        }
        catch(Exception ex){
            throw new NotFoundException();
        }
        return menus;
    }
}