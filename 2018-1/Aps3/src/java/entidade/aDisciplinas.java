
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javafx.print.Collation;
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
public class aDisciplinas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDisciplina;

    @OneToMany
    Collection<aCurso>cursos;   
    private Long idCurso;
    
    private String nomeDisciplina;

    
    public aDisciplinas(Long idDisciplina, Long idCurso, String nomeDisciplina) {
        this.idDisciplina = idDisciplina;
        this.idCurso = idCurso;
        this.nomeDisciplina = nomeDisciplina;
    }

    public aDisciplinas() {
    }

    public Long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }
    
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDisciplina != null ? idDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idDisciplina fields are not set
        if (!(object instanceof aDisciplinas)) {
            return false;
        }
        aDisciplinas other = (aDisciplinas) object;
        if ((this.idDisciplina == null && other.idDisciplina != null) || (this.idDisciplina != null && !this.idDisciplina.equals(other.idDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aDisciplinas[idDisciplina=" + idDisciplina + " ]";
    }
    
    
    
}
