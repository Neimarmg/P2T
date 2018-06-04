
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
public class aPlanoDeAula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlanoDeAula;
    
    @OneToMany
    Collection<gTurnos>turno;
    
    @OneToMany
    Collection<aHorariosAulas>horariosAulas;
       
    private String dataAula;
    private boolean confirmada;
      private String conteudo;
    
    public aPlanoDeAula() {
        
    }

    public aPlanoDeAula(Long idPlanoDeAula, Collection<gTurnos> turno, Collection<aHorariosAulas> horariosAulases, String dataAula, boolean confirmada, String conteudo) {
        this.idPlanoDeAula = idPlanoDeAula;
        this.turno = turno;
        this.horariosAulas = horariosAulases;
        this.dataAula = dataAula;
        this.confirmada = confirmada;
        this.conteudo = conteudo;
    }

    public Long getIdPlanoDeAula() {
        return idPlanoDeAula;
    }

    public void setIdPlanoDeAula(Long idPlanoDeAula) {
        this.idPlanoDeAula = idPlanoDeAula;
    }

    public Collection<gTurnos> getTurno() {
        return turno;
    }

    public void setTurno(Collection<gTurnos> turno) {
        this.turno = turno;
    }

    public Collection<aHorariosAulas> getHorariosAulas() {
        return horariosAulas;
    }

    public void setHorariosAulas(Collection<aHorariosAulas> horariosAulas) {
        this.horariosAulas = horariosAulas;
    }

    public String getDataAula() {
        return dataAula;
    }

    public void setDataAula(String dataAula) {
        this.dataAula = dataAula;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanoDeAula != null ? idPlanoDeAula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idCurso fields are not set
        if (!(object instanceof aPlanoDeAula)) {
            return false;
        }
        aPlanoDeAula other = (aPlanoDeAula) object;
        if ((this.idPlanoDeAula == null && other.idPlanoDeAula != null) || (this.idPlanoDeAula != null && !this.idPlanoDeAula.equals(other.idPlanoDeAula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aPlanoDeAula[idPlanoDeAula=" + idPlanoDeAula + " ]";
    }    
    
}
