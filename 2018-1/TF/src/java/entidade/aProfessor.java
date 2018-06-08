
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
public class aProfessor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfessor;

    @OneToMany
    Collection<gPessoa>pessoas;      
    private boolean ativo;
    
    
    public aProfessor() {
        
    }

    public aProfessor(Long idProfessor, Collection<gPessoa> pessoas, boolean ativo) {
        this.idProfessor = idProfessor;
        this.pessoas = pessoas;
        this.ativo = ativo;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Collection<gPessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(Collection<gPessoa> pessoas) {
        this.pessoas = pessoas;
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
        hash += (idProfessor != null ? idProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idProfessor fields are not set
        if (!(object instanceof aProfessor)) {
            return false;
        }
        aProfessor other = (aProfessor) object;
        if ((this.idProfessor == null && other.idProfessor != null) || (this.idProfessor != null && !this.idProfessor.equals(other.idProfessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aProfessor[idProfessor=" + idProfessor + " ]";
    }
    
    
    
}
