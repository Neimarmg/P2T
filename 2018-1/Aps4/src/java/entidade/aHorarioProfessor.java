
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
public class aHorarioProfessor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idaHorarioProfessor;

    @OneToMany
    Collection<aPlanoDeAula>planoDeAulas;
    
    @OneToMany
    Collection<aProfessor>professor;
    private double valorAula;
    
    public aHorarioProfessor() {
        
    }

    public aHorarioProfessor(Long idaHorarioProfessor, Collection<aPlanoDeAula> planoDeAulas, Collection<aProfessor> professor, double valorAula) {
        this.idaHorarioProfessor = idaHorarioProfessor;
        this.planoDeAulas = planoDeAulas;
        this.professor = professor;
        this.valorAula = valorAula;
    }

    public Long getIdaHorarioProfessor() {
        return idaHorarioProfessor;
    }

    public void setIdaHorarioProfessor(Long idaHorarioProfessor) {
        this.idaHorarioProfessor = idaHorarioProfessor;
    }

    public Collection<aPlanoDeAula> getPlanoDeAulas() {
        return planoDeAulas;
    }

    public void setPlanoDeAulas(Collection<aPlanoDeAula> planoDeAulas) {
        this.planoDeAulas = planoDeAulas;
    }

    public Collection<aProfessor> getProfessor() {
        return professor;
    }

    public void setProfessor(Collection<aProfessor> professor) {
        this.professor = professor;
    }

    public double getValorAula() {
        return valorAula;
    }

    public void setValorAula(double valorAula) {
        this.valorAula = valorAula;
    }


    
    
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaHorarioProfessor != null ? idaHorarioProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idaHorarioProfessor fields are not set
        if (!(object instanceof aHorarioProfessor)) {
            return false;
        }
        aHorarioProfessor other = (aHorarioProfessor) object;
        if ((this.idaHorarioProfessor == null && other.idaHorarioProfessor != null) || (this.idaHorarioProfessor != null && !this.idaHorarioProfessor.equals(other.idaHorarioProfessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aHorarioProfessor[idCurso=" + idaHorarioProfessor + " ]";
    }
    
    
    
}
