/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebSevice;

import Dao.CursoDAO;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import model.Curso;

/**
 * REST Web Service
 *
 * @author neimarmoises
 */
@Path("curso")
public class CursoWs {
    CursoDAO cursoDAO =  new CursoDAO();
   
    @Context
    private UriInfo context;

    public CursoWs() {
    }
    
     @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarCurso(Curso curso, @Context final HttpServletResponse response){
        try {
            curso.setIdModalidade(1);
            curso.setIdProjtoCurso(3);
            curso.setNomeCurso("Graduação em direito");            
            cursoDAO.inserir(curso);
            
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.flushBuffer();
            
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
