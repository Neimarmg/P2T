/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ws;



import Model.TipoUtilitarios;
import com.google.gson.Gson;
import java.io.IOException;
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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import Service.UtilitariosService;
import Model.Utilitarios;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * REST Web Service
 *
 * @author neimarmoises
 */
@Path("Utilitarios")
public class UtilitariosWS {

    UtilitariosService utilitariosService1 = lookupUtilitariosServiceBean();
   @EJB
    private UtilitariosService utilitariosService ;
   
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TipoUtilitario
     */
    public  UtilitariosWS() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilitarios> getTipoUtilitario(){
        return utilitariosService.listar();
    }
    
    @GET
    @Path("Buscar/get/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilitarios getTipoUtilitario(@PathParam("codigo") int cod){
           return utilitariosService.buscarPorCodigo(cod);
    }
    
    
    @GET
    @Path("InsetManual/Set")
    @Produces(MediaType.APPLICATION_JSON)
    public String InsereTipoUtilitario() {
        UtilitariosService lista = new UtilitariosService();
        
        Utilitarios tu = new Utilitarios(0,null);
        tu.setCodTipoUtilitario(1);
        tu.setDescUtilitario("Tipo de pessoa");
        tu.setCodUtilitario(1);
        
        lista.insere(tu);
        
        tu.setCodTipoUtilitario(2);
        tu.setDescUtilitario("Tipo de banco");
        tu.setCodUtilitario(1);
        
        lista.insere(tu);

        Gson g = new Gson();
        return g.toJson(lista);
    }
    
    
       
    @POST   
    @Path("Adiconar/set")
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionaUtlilitario(Utilitarios tu, @Context final HttpServletResponse response){
        utilitariosService.insere(tu);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw  new InternalServerErrorException();
        }      
    }
        
    
    @PUT
    @Path("/Alaterar/set/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void aterarUtilitario(@PathParam("codigo") int cod, Utilitarios tipoUtilitarios){
        Utilitarios t = utilitariosService.buscarPorCodigo(cod);
        
        //t.setDescUtilitario(Utilitarios.getDescUtilitario());
        //t.setCodTipoUtilitario( cod));
        utilitariosService.atualiza(t);
    }
    
    
    @DELETE
    @Path("Remver/set/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removerUtilitario(@PathParam("codigo") int cod){
        Utilitarios t = utilitariosService.buscarPorCodigo(cod);
        utilitariosService.exclui(t);
    }

    private UtilitariosService lookupUtilitariosServiceBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (UtilitariosService) c.lookup("java:global/SGcWs/UtilitariosService!Service.UtilitariosService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
