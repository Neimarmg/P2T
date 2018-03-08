package dominio;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neimarmoises
 */
@XmlRootElement
public class TipoUtilitarios implements Serializable {
    private int id;
    private String descTipoUtilitario;

    public TipoUtilitarios() {
    }

      
    public TipoUtilitarios(String descTipoUtilitario) {
        this.descTipoUtilitario = descTipoUtilitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescTipoUtilitario() {
        return descTipoUtilitario;
    }

    public void setDescTipoUtilitario(String descTipoUtilitario) {
        this.descTipoUtilitario = descTipoUtilitario;
    }

    public void atualizar(TipoUtilitarios tipoUtilitarios) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
