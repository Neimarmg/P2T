
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CollectionTable;
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
public class aCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    @OneToMany
    Collection<aModalidadecurso>modalidadecursos; 
    private Long idModalidade;
    
    
    @OneToMany
    Collection<aProjetocurso>projetocursos; 
    private Long idProjetoCurso;
    
    private String nomeCurso;
    public aCurso() {
        
    }

    public aCurso(Long idCurso, Long idModalidade, Long idProjtoCurso, String nomeCurso) {
        this.idCurso = idCurso;
        this.idModalidade = idModalidade;
        this.idProjetoCurso = idProjtoCurso;
        this.nomeCurso = nomeCurso;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public Long getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(Long idModalidade) {
        this.idModalidade = idModalidade;
    }

    public Long getIdProjetoCurso() {
        return idProjetoCurso;
    }

    public void setIdProjetoCurso(Long idProjetoCurso) {
        this.idProjetoCurso = idProjetoCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

       
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idCurso fields are not set
        if (!(object instanceof aCurso)) {
            return false;
        }
        aCurso other = (aCurso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aCurso[idCurso=" + idCurso + " ]";
    }
    
    
    
}
