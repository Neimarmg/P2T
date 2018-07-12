
package entidade;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neimarmoises
 */
@Entity
@XmlRootElement
public class gTipomenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoMenu;
    private String nomeMenu;

    public gTipomenu() {
    }

    public gTipomenu(Long idTipoMenu, String nomeMenu) {
        this.idTipoMenu = idTipoMenu;
        this.nomeMenu = nomeMenu;
    }

    public Long getIdTipoMenu() {
        return idTipoMenu;
    }

    public void setIdTipoMenu(Long idTipoMenu) {
        this.idTipoMenu = idTipoMenu;
    }

    public String getNomeMenu() {
        return nomeMenu;
    }

    public void setNomeMenu(String nomeMenu) {
        this.nomeMenu = nomeMenu;
    }

    
       
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMenu != null ? idTipoMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idTipoMenu fields are not set
        if (!(object instanceof gTipomenu)) {
            return false;
        }
        gTipomenu other = (gTipomenu) object;
        if ((this.idTipoMenu == null && other.idTipoMenu != null) || (this.idTipoMenu != null && !this.idTipoMenu.equals(other.idTipoMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.gTipomenu[idTipoMenu=" + idTipoMenu + " ]";
    }
    
    
    
}
