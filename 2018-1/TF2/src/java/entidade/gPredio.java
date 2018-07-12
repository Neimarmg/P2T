
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
public class gPredio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPredio;
    
    @OneToMany
    Collection<gFiliais>filial;    
    private String nomePredio;
    private int qtPavimentos;
    private boolean ativo;
    
    public gPredio() {
    }

    public gPredio(Long idPredio, Collection<gFiliais> filial, String nomePredio, int qtPavimentos, boolean ativo) {
        this.idPredio = idPredio;
        this.filial = filial;
        this.nomePredio = nomePredio;
        this.qtPavimentos = qtPavimentos;
        this.ativo = ativo;
    }

    public Long getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(Long idPredio) {
        this.idPredio = idPredio;
    }

    public Collection<gFiliais> getFilial() {
        return filial;
    }

    public void setFilial(Collection<gFiliais> filial) {
        this.filial = filial;
    }

    public String getNomePredio() {
        return nomePredio;
    }

    public void setNomePredio(String nomePredio) {
        this.nomePredio = nomePredio;
    }

    public int getQtPavimentos() {
        return qtPavimentos;
    }

    public void setQtPavimentos(int qtPavimentos) {
        this.qtPavimentos = qtPavimentos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    

     
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPredio != null ? idPredio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPredio fields are not set
        if (!(object instanceof gPredio)) {
            return false;
        }
        gPredio other = (gPredio) object;
        if ((this.idPredio == null && other.idPredio != null) || (this.idPredio != null && !this.idPredio.equals(other.idPredio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.gPredio[idPredio=" + idPredio + " ]";
    }
    
    
    
}
