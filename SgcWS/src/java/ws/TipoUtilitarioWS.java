/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ws;

import Model.TipoUtilitarios;
import Service.TipoUtilitarioService;
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

/**
 * REST Web Service
 *
 * @author neimarmoises
 */
@Path("TipoUtilitario/Crud")
public class TipoUtilitarioWS {
   
    @EJB
    private TipoUtilitarioService tipoUtilitarioService ;
  
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TipoUtilitario
     */
    public TipoUtilitarioWS() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUtilitarios> getTipoUtilitario(){
        return tipoUtilitarioService.listar();
    }
    
    @GET
    @Path("Buscar/get/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public TipoUtilitarios getTipoUtilitario(@PathParam("codigo") int cod){
           return tipoUtilitarioService.buscarPorCodigo(cod);
    }
    
    
    @GET
    @Path("Insert Manual/Set")
    @Produces(MediaType.APPLICATION_JSON)
    public String InsereTipoUtilitario() {
        TipoUtilitarioService lista = new TipoUtilitarioService();
        
        TipoUtilitarios tu = new TipoUtilitarios();
        tu.setCodTipoUtilitario(1);
        tu.setDescTipoUtilitario("Tipo de pessoa");
        
        lista.insere(tu);
        
        tu.setCodTipoUtilitario(2);
        tu.setDescTipoUtilitario("Tipo de banco");
        
        lista.insere(tu);

        Gson g = new Gson();
        return g.toJson(lista);
    }
    
       
    @POST   
    @Path("Adiconar/set")
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionaTipoUtlilitario(TipoUtilitarios tu, @Context final HttpServletResponse response){
        tipoUtilitarioService.insere(tu);
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
    public void aterarTipoUtilitario(@PathParam("codigo") int cod, TipoUtilitarios tipoUtilitarios){
        TipoUtilitarios t = tipoUtilitarioService.buscarPorCodigo(cod);
        t.setCodTipoUtilitario(tipoUtilitarios.getCodTipoUtilitario());
        t.setDescTipoUtilitario(tipoUtilitarios.getDescTipoUtilitario());
        tipoUtilitarioService.atualiza(t);
    }
    
    
    @DELETE
    @Path("Remover/set/{codigo}")
    public void removerTipoUtilitario(@PathParam("codigo") int cod){
        TipoUtilitarios t = tipoUtilitarioService.buscarPorCodigo(cod);
        tipoUtilitarioService.exclui(t);
    }
}
