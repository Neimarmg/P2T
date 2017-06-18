/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import Service.TipoUtilitarioService;
import Model.TipoUtilitarios;
import com.google.gson.Gson;
import java.util.List;

/**
 * REST Web Service
 *
 * @author neimarmoises
 */
@Path("tipoUtilitario")
public class TipoUtilitarioWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of tipoUtilitario
     */
    public TipoUtilitarioWS() {
    }

    /**
     * Retrieves representation of an instance of ws.tipoUtilitario
     * @return an instance of java.lang.String
     */
    @GET
    @Path("tipoUtilitario/Set")
    @Produces(MediaType.APPLICATION_JSON)
    public String InsereTipoUtilitario() {
        TipoUtilitarioService lista = new TipoUtilitarioService();
        
        TipoUtilitarios tu = new TipoUtilitarios(0,null);
        tu.setCodTipoUtilitario(1);
        tu.setDescTipoUtilitario("Tipo de pessoa");
        
        lista.insere(tu);
        
        tu.setCodTipoUtilitario(2);
        tu.setDescTipoUtilitario("Tipo de banco");
        
        lista.insere(tu);

        Gson g = new Gson();
        return g.toJson(lista);
    }

    
    
    /**
     * PUT method for updating or creating an instance of tipoUtilitario
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        
        
    }
}
