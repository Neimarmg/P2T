
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
public class gMenus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMenu;
    private Long idTipoMenu;
    private String nomeMenu;

    public gMenus() {
    }

    public gMenus(Long idMenu, Long idTipoMenu, String nomeMenu) {
        this.idMenu = idMenu;
        this.idTipoMenu = idTipoMenu;
        this.nomeMenu = nomeMenu;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
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
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idMenu fields are not set
        if (!(object instanceof gMenus)) {
            return false;
        }
        gMenus other = (gMenus) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aGmenu[idMenu=" + idMenu + " ]";
    }
    
    
    
}
