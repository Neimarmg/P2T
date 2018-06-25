package ws;

import entidade.Motor;
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
import rn.MotorRN;

/**
 * REST Web Service
 *
 * @author LHRIES
 */
@Path("motor")
public class MotorWS {
    private MotorRN motorRN;
    @Context
    private UriInfo context;

    public MotorWS() {
        motorRN = new MotorRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Motor> getMotores() {
        return motorRN.listar();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Motor getMotorPorId(@PathParam("id") Long id){
        Motor motor = motorRN.buscarPorId(id);
        if(motor == null) throw new NotFoundException();
        return motor;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Motor adiciona(Motor motor, @Context HttpServletResponse response) {
        Motor motorGerado = motorRN.inserir(motor);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new InternalServerErrorException();
        }
        return motorGerado;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Motor atualiza(@PathParam("id") Long id, Motor motor) {
        
        
        Motor motorGerado;
       
        try {
            motorGerado = motorRN.atualizar(id, motor);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        
        return motorGerado;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Motor deletar(@PathParam("id") Long id){
        Motor motor = null;
        try{
            motor = motorRN.deletar(id);
        }
        catch(Exception e){
            throw new NotFoundException();
        }
        return motor;
    }
}
