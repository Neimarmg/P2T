package Model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neimarmoises
 */
@XmlRootElement
public class TipoUtilitarios implements Serializable {
    private int codTipoUtilitario;
    private String descTipoUtilitario;

    public TipoUtilitarios() {
    }

      
    public TipoUtilitarios(int codTipoUtilitario, String descTipoUtilitario) {
        this.codTipoUtilitario = codTipoUtilitario;
        this.descTipoUtilitario = descTipoUtilitario;
    }

    public int getCodTipoUtilitario() {
        return codTipoUtilitario;
    }

    public void setCodTipoUtilitario(int codTipoUtilitario) {
        this.codTipoUtilitario = codTipoUtilitario;
    }

    public String getDescTipoUtilitario() {
        return descTipoUtilitario;
    }

    public void setDescTipoUtilitario(String descTipoUtilitario) {
        this.descTipoUtilitario = descTipoUtilitario;
    }
    
}
