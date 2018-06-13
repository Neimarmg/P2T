
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
public class gFiliais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilial;
    
    @OneToMany
    Collection<gMatriz>matrizs;
    
    @OneToMany
    Collection<gUtilitarios>utilitarios;        
    private String descricao;
    private String cnpj;

    public gFiliais() {
    }

    public gFiliais(Long idFilial, Collection<gMatriz> matrizs, Collection<gUtilitarios> utilitarios, String descricao, String cnpj) {
        this.idFilial = idFilial;
        this.matrizs = matrizs;
        this.utilitarios = utilitarios;
        this.descricao = descricao;
        this.cnpj = cnpj;
    }

    public Long getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(Long idFilial) {
        this.idFilial = idFilial;
    }

    public Collection<gMatriz> getMatrizs() {
        return matrizs;
    }

    public void setMatrizs(Collection<gMatriz> matrizs) {
        this.matrizs = matrizs;
    }

    public Collection<gUtilitarios> getUtilitarios() {
        return utilitarios;
    }

    public void setUtilitarios(Collection<gUtilitarios> utilitarios) {
        this.utilitarios = utilitarios;
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
        hash += (idFilial != null ? idFilial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idFilial fields are not set
        if (!(object instanceof gFiliais)) {
            return false;
        }
        gFiliais other = (gFiliais) object;
        if ((this.idFilial == null && other.idFilial != null) || (this.idFilial != null && !this.idFilial.equals(other.idFilial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.gFiliais[idFilial=" + idFilial + " ]";
    }
    
    
    
}
