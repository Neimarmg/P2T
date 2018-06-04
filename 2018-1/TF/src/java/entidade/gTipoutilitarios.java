
package entidade;
import java.io.Serializable;
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
public class gTipoutilitarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoUtilitario;
    
    private String descricao;

    public gTipoutilitarios() {
    }

    public gTipoutilitarios(Long idTipoUtilitario, String descricao) {
        this.idTipoUtilitario = idTipoUtilitario;
        this.descricao = descricao;
    }

    public Long getIdTipoUtilitario() {
        return idTipoUtilitario;
    }

    public void setIdTipoUtilitario(Long idTipoUtilitario) {
        this.idTipoUtilitario = idTipoUtilitario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    

           
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUtilitario != null ? idTipoUtilitario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idTipoMenu fields are not set
        if (!(object instanceof gTipoutilitarios)) {
            return false;
        }
        gTipoutilitarios other = (gTipoutilitarios) object;
        if ((this.idTipoUtilitario == null && other.idTipoUtilitario != null) || (this.idTipoUtilitario != null && !this.idTipoUtilitario.equals(other.idTipoUtilitario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.gTipoutilitarios[idTipoUtilitario=" + idTipoUtilitario + " ]";
    }
    
    
    
}
