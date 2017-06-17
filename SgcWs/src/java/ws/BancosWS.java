package ws;

import Model.Bancos;
import Service.BancoService;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 * @author neimarmoises
 */
@Path("Bancos")
public class BancosWS {
    
    @EJB
    private BancoService bancoService;
    
    
    @Context
    private UriInfo context;

    
    /** Creates a new instance of BancosWS*/
    public BancosWS() {
        
    }

    
    /**
     * Retrieves representation of an instance of ws.BancosWS
     * @return an instance of Model.Bancos
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bancos> getBanco(){
        return bancoService.listar();
    }
    
    /*
    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bancos getBanco(@PathParam("codigo")int cod){
        return bancoService.buscarPorCodigo(cod);
    }
    */
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionaProduto(Bancos banco, @Context final HttpServletResponse response){
       
        bancoService.insere(banco);        
        response.setStatus(HttpServletResponse.SC_CREATED);
        
        try {
            response.flushBuffer();
            
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
    }
    
       
    /**
     * PUT method for updating or creating an instance of BancosWS
     * @param cod
     * @param banco
     * @param content representation for the resource
     */    
    @PUT
    @Path("/codigo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alteraBanco(@PathParam("codigo")int cod, Bancos banco){
        
        Bancos b = bancoService.buscarPorCodigo(cod);
        b.setDescricao(banco.getDescricao());
        b.setNroBanco(banco.getNroBanco());
        b.setWebPage(banco.getWebPage());
    }
    
    
    /**
     *
     * @param cod
     * @return
     */
    @DELETE
    @Path("/codigo")
    @Produces(MediaType.APPLICATION_JSON)
    public Bancos removeBanco(@PathParam("codigo") int cod){
        Bancos b = bancoService.buscarPorCodigo(cod);
        bancoService.exclui(b);
        return b;
    }   
}
