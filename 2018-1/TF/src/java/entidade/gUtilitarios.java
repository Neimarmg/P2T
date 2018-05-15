
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neimarmoises
 */
@Entity
@XmlRootElement
public class gUtilitarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilitario;

    @OneToMany
    Collection<gAplicacao>aplicacaos;
    
    @OneToOne
    Collection<gTipoutilitarios>tipoutilitarios;    
    
    private String utilitario;
    private String Obs;
    private boolean favorita;

    public gUtilitarios() {
    }

    public gUtilitarios(Long idUtilitario, Collection<gAplicacao> aplicacaos, Collection<gTipoutilitarios> tipoutilitarios, String utilitario, String Obs, boolean favorita) {
        this.idUtilitario = idUtilitario;
        this.aplicacaos = aplicacaos;
        this.tipoutilitarios = tipoutilitarios;
        this.utilitario = utilitario;
        this.Obs = Obs;
        this.favorita = favorita;
    }

    public Long getIdUtilitario() {
        return idUtilitario;
    }

    public void setIdUtilitario(Long idUtilitario) {
        this.idUtilitario = idUtilitario;
    }

    public Collection<gAplicacao> getAplicacaos() {
        return aplicacaos;
    }

    public void setAplicacaos(Collection<gAplicacao> aplicacaos) {
        this.aplicacaos = aplicacaos;
    }

    public Collection<gTipoutilitarios> getTipoutilitarios() {
        return tipoutilitarios;
    }

    public void setTipoutilitarios(Collection<gTipoutilitarios> tipoutilitarios) {
        this.tipoutilitarios = tipoutilitarios;
    }

    public String getUtilitario() {
        return utilitario;
    }

    public void setUtilitario(String utilitario) {
        this.utilitario = utilitario;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String Obs) {
        this.Obs = Obs;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

   
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilitario != null ? idUtilitario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idUtilitario fields are not set
        if (!(object instanceof gUtilitarios)) {
            return false;
        }
        gUtilitarios other = (gUtilitarios) object;
        if ((this.idUtilitario == null && other.idUtilitario != null) || (this.idUtilitario != null && !this.idUtilitario.equals(other.idUtilitario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.gUtilitarios[idUtilitario=" + idUtilitario + " ]";
    }
    
    
    
}
