
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.OneToOne;
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

    @OneToMany
    Collection<gTipomenu>tipomenu;  
    
    private String nomeMenu;
    private boolean favorito;
    
    public gMenus() {
    }

    public gMenus(Long idMenu, Collection<gTipomenu> tipomenu, String nomeMenu, boolean favorito) {
        this.idMenu = idMenu;
        this.tipomenu = tipomenu;
        this.nomeMenu = nomeMenu;
        this.favorito = favorito;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public Collection<gTipomenu> getTipomenu() {
        return tipomenu;
    }

    public void setTipomenu(Collection<gTipomenu> tipomenu) {
        this.tipomenu = tipomenu;
    }

    public String getNomeMenu() {
        return nomeMenu;
    }

    public void setNomeMenu(String nomeMenu) {
        this.nomeMenu = nomeMenu;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
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
