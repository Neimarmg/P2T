
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
public class gTipoPessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoPessoa;

    private String desctipoPessoa;  

    public gTipoPessoa() {
    }

    public gTipoPessoa(Long idTipoPessoa, String desctipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
        this.desctipoPessoa = desctipoPessoa;
    }

    public Long getIdTipoPessoa() {
        return idTipoPessoa;
    }

    public void setIdTipoPessoa(Long idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    public String getDesctipoPessoa() {
        return desctipoPessoa;
    }

    public void setDesctipoPessoa(String desctipoPessoa) {
        this.desctipoPessoa = desctipoPessoa;
    }
   
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPessoa != null ? idTipoPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idMenu fields are not set
        if (!(object instanceof gTipoPessoa)) {
            return false;
        }
        gTipoPessoa other = (gTipoPessoa) object;
        if ((this.idTipoPessoa == null && other.idTipoPessoa != null) || (this.idTipoPessoa != null && !this.idTipoPessoa.equals(other.idTipoPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aTipoPessoa[idTipoPessoa=" + idTipoPessoa + " ]";
    }
    
    
    
}
