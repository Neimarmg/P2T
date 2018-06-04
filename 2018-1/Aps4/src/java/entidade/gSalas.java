
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
public class gSalas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSala;
    
    @OneToMany
    Collection<gPredio>predios;

    @OneToMany 
    Collection<gUtilitarios>tipoSala;
    
    private String sala;
    private String pavimento;
    private int capacidade;
    private boolean ativa;
    
    public gSalas() {
    }

    public gSalas(Long idSala, Collection<gPredio> predios, Collection<gUtilitarios> utilitarios, String sala, String pavimento, int capacidade, boolean ativa) {
        this.idSala = idSala;
        this.predios = predios;
        this.tipoSala = utilitarios;
        this.sala = sala;
        this.pavimento = pavimento;
        this.capacidade = capacidade;
        this.ativa = ativa;
    }

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    public Collection<gPredio> getPredios() {
        return predios;
    }

    public void setPredios(Collection<gPredio> predios) {
        this.predios = predios;
    }

    public Collection<gUtilitarios> getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(Collection<gUtilitarios> tipoSala) {
        this.tipoSala = tipoSala;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getPavimento() {
        return pavimento;
    }

    public void setPavimento(String pavimento) {
        this.pavimento = pavimento;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

      
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSala != null ? idSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idSala fields are not set
        if (!(object instanceof gSalas)) {
            return false;
        }
        gSalas other = (gSalas) object;
        if ((this.idSala == null && other.idSala != null) || (this.idSala != null && !this.idSala.equals(other.idSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.gSalas[idSalas=" + idSala + " ]";
    }
    
    
    
}
