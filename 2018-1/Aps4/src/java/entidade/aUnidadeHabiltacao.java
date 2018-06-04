
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
public class aUnidadeHabiltacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidadeHabiltacao;

    @OneToOne
    Collection<gMatriz>matriz;    

    @OneToMany
    Collection<gFiliais>filial;    
        
    @OneToMany
    Collection<aCurso>curso;    
    
    @OneToMany
    Collection<aProjetocurso>projetocursos;    
    
    private String nomeHabilitacao;
    
    
    public aUnidadeHabiltacao() {
        
    }

    public aUnidadeHabiltacao(Long ididUnidadeHabiltacao, Collection<gMatriz> matriz, Collection<gFiliais> filial, Collection<aCurso> curso, Collection<aProjetocurso> projetocursos, String nomeHabilitacao) {
        this.idUnidadeHabiltacao = ididUnidadeHabiltacao;
        this.matriz = matriz;
        this.filial = filial;
        this.curso = curso;
        this.projetocursos = projetocursos;
        this.nomeHabilitacao = nomeHabilitacao;
    }

    public Long getIdUnidadeHabiltacao() {
        return idUnidadeHabiltacao;
    }

    public void setIdUnidadeHabiltacao(Long idUnidadeHabiltacao) {
        this.idUnidadeHabiltacao = idUnidadeHabiltacao;
    }

    public Collection<gMatriz> getMatriz() {
        return matriz;
    }

    public void setMatriz(Collection<gMatriz> matriz) {
        this.matriz = matriz;
    }

    public Collection<gFiliais> getFilial() {
        return filial;
    }

    public void setFilial(Collection<gFiliais> filial) {
        this.filial = filial;
    }

    public Collection<aCurso> getCurso() {
        return curso;
    }

    public void setCurso(Collection<aCurso> curso) {
        this.curso = curso;
    }

    public Collection<aProjetocurso> getProjetocursos() {
        return projetocursos;
    }

    public void setProjetocursos(Collection<aProjetocurso> projetocursos) {
        this.projetocursos = projetocursos;
    }

    public String getNomeHabilitacao() {
        return nomeHabilitacao;
    }

    public void setNomeHabilitacao(String nomeHabilitacao) {
        this.nomeHabilitacao = nomeHabilitacao;
    }

        
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadeHabiltacao != null ? idUnidadeHabiltacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idUnidadeHabiltacao fields are not set
        if (!(object instanceof aUnidadeHabiltacao)) {
            return false;
        }
        aUnidadeHabiltacao other = (aUnidadeHabiltacao) object;
        if ((this.idUnidadeHabiltacao == null && other.idUnidadeHabiltacao != null) || (this.idUnidadeHabiltacao != null && !this.idUnidadeHabiltacao.equals(other.idUnidadeHabiltacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aUnidadeHabiltacao[idUnidadeHabiltacao=" + idUnidadeHabiltacao + " ]";
    }
    
    
    
}
