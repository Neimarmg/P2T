
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author neimarmoises
 */
@Entity
@XmlRootElement
public class aProjetocurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjetoCurso;
 
    @OneToMany
    Collection<aModalidadecurso>modalidadecursos;
    private Long idModalidade;
    
    @OneToMany
    Collection<gFiliais>filiaises;
    private long idFilial; 
    
    private double valorCurso ;


    public aProjetocurso() {
    }

    public aProjetocurso(Long idProjetoCurso, Long idModalidade, long idFilial, double valorCurso) {
        this.idProjetoCurso = idProjetoCurso;
        this.idModalidade = idModalidade;
        this.idFilial = idFilial;
        this.valorCurso = valorCurso;
       
    }

    public Long getIdProjetoCurso() {
        return idProjetoCurso;
    }

    public void setIdProjetoCurso(Long idProjetoCurso) {
        this.idProjetoCurso = idProjetoCurso;
    }

    public Long getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(Long idModalidade) {
        this.idModalidade = idModalidade;
    }

    public long getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(long idFilial) {
        this.idFilial = idFilial;
    }

    public double getValorCurso() {
        return valorCurso;
    }

    public void setValorCurso(double valorCurso) {
        this.valorCurso = valorCurso;
    }
 
    
      
       
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProjetoCurso != null ? idProjetoCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idProjetoCurso fields are not set
        if (!(object instanceof aProjetocurso)) {
            return false;
        }
        aProjetocurso other = (aProjetocurso) object;
        if ((this.idProjetoCurso == null && other.idProjetoCurso != null) || (this.idProjetoCurso != null && !this.idProjetoCurso.equals(other.idProjetoCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aProjetocurso[idProjetoCurso=" + idProjetoCurso + " ]";
    }
  
       
}
