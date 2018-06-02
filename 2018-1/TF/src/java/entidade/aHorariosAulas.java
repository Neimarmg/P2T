
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neimarmoises
 */
@Entity
@XmlRootElement
public class aHorariosAulas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorariosAulas;
    
    @OneToOne
    Collection<gTurnos>turno;
    
    @OneToOne
    Collection<aTurmaDisciplinas>turmaDisciplinas;
    
  
    
    private String dataInicio;    
    private String dataFim;
  
    
    public aHorariosAulas() {
        
    }

    public aHorariosAulas(Long idHorariosAulas, Collection<gTurnos> turno, Collection<aTurmaDisciplinas> turmaDisciplinas, String dataInicio, String dataFim) {
        this.idHorariosAulas = idHorariosAulas;
        this.turno = turno;
        this.turmaDisciplinas = turmaDisciplinas;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getIdHorariosAulas() {
        return idHorariosAulas;
    }

    public void setIdHorariosAulas(Long idHorariosAulas) {
        this.idHorariosAulas = idHorariosAulas;
    }

    public Collection<gTurnos> getTurno() {
        return turno;
    }

    public void setTurno(Collection<gTurnos> turno) {
        this.turno = turno;
    }

    public Collection<aTurmaDisciplinas> getTurmaDisciplinas() {
        return turmaDisciplinas;
    }

    public void setTurmaDisciplinas(Collection<aTurmaDisciplinas> turmaDisciplinas) {
        this.turmaDisciplinas = turmaDisciplinas;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorariosAulas != null ? idHorariosAulas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idCurso fields are not set
        if (!(object instanceof aHorariosAulas)) {
            return false;
        }
        aHorariosAulas other = (aHorariosAulas) object;
        if ((this.idHorariosAulas == null && other.idHorariosAulas != null) || (this.idHorariosAulas != null && !this.idHorariosAulas.equals(other.idHorariosAulas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aHorariosAulas[idHorariosAulas=" + idHorariosAulas + " ]";
    }    
    
}
