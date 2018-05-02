
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
public class gMatriz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatriz;
    private String descricao;
    private String cnpj;

    public gMatriz() {
        
    }

    public gMatriz(Long idMatriz, String descricao, String cnpj) {
        this.idMatriz = idMatriz;
        this.descricao = descricao;
        this.cnpj = cnpj;
    }

    public Long getIdMatriz() {
        return idMatriz;
    }

    public void setIdMatriz(Long idMatriz) {
        this.idMatriz = idMatriz;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

           
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatriz != null ? idMatriz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idMatriz fields are not set
        if (!(object instanceof gMatriz)) {
            return false;
        }
        gMatriz other = (gMatriz) object;
        if ((this.idMatriz == null && other.idMatriz != null) || (this.idMatriz != null && !this.idMatriz.equals(other.idMatriz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.gMatriz[idMatriz=" + idMatriz + " ]";
    }
    
    
    
}
