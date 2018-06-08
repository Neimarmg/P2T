
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neimarmoises
 */
@Entity
@XmlRootElement
public class yPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfil;
    @OneToMany
    Collection<gFiliais>filial;
    
    @OneToMany
    Collection<gUtilitarios>tipoPerfil;
    private String descricaoPerfil;

    public yPerfil() {
    }

    public yPerfil(Long idPerfil, Collection<gFiliais> filial, Collection<gUtilitarios> tipoPerfil, String descricaoPerfil) {
        this.idPerfil = idPerfil;
        this.filial = filial;
        this.tipoPerfil = tipoPerfil;
        this.descricaoPerfil = descricaoPerfil;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Collection<gFiliais> getFilial() {
        return filial;
    }

    public void setFilial(Collection<gFiliais> filial) {
        this.filial = filial;
    }

    public Collection<gUtilitarios> getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(Collection<gUtilitarios> tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public String getDescricaoPerfil() {
        return descricaoPerfil;
    }

    public void setDescricaoPerfil(String descricaoPerfil) {
        this.descricaoPerfil = descricaoPerfil;
    }

   
    
    
    


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPerfil fields are not set
        if (!(object instanceof yPerfil)) {
            return false;
        }
        yPerfil other = (yPerfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.yPerfil[idPerfil=" + idPerfil + " ]";
    }
    
    
    
}
