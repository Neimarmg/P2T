
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
public class aTurmaDisciplinas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurmaDisciplina;
    
    @OneToOne
    Collection<gTurnos>turno;
    
    @OneToOne
    Collection<aDisciplinas>disciplina;
    
    @OneToOne
    Collection<aUnidadeHabiltacao>unidadeHabiltacao;
    
    private String dataInicio;
    
    private String dataFim;
    
    private boolean ativa;
      
    public aTurmaDisciplinas() {
        
    }

    public aTurmaDisciplinas(Long idTurmaDisciplina, Collection<gTurnos> turno, Collection<aDisciplinas> disciplina, Collection<aUnidadeHabiltacao> unidadeHabiltacao, String dataInicio, String dataFim, boolean ativa) {
        this.idTurmaDisciplina = idTurmaDisciplina;
        this.turno = turno;
        this.disciplina = disciplina;
        this.unidadeHabiltacao = unidadeHabiltacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativa = ativa;
    }

    public Long getIdTurmaDisciplina() {
        return idTurmaDisciplina;
    }

    public void setIdTurmaDisciplina(Long idTurmaDisciplina) {
        this.idTurmaDisciplina = idTurmaDisciplina;
    }

    public Collection<gTurnos> getTurno() {
        return turno;
    }

    public void setTurno(Collection<gTurnos> turno) {
        this.turno = turno;
    }

    public Collection<aDisciplinas> getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Collection<aDisciplinas> disciplina) {
        this.disciplina = disciplina;
    }

    public Collection<aUnidadeHabiltacao> getUnidadeHabiltacao() {
        return unidadeHabiltacao;
    }

    public void setUnidadeHabiltacao(Collection<aUnidadeHabiltacao> unidadeHabiltacao) {
        this.unidadeHabiltacao = unidadeHabiltacao;
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

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurmaDisciplina != null ? idTurmaDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idCurso fields are not set
        if (!(object instanceof aTurmaDisciplinas)) {
            return false;
        }
        aTurmaDisciplinas other = (aTurmaDisciplinas) object;
        if ((this.idTurmaDisciplina == null && other.idTurmaDisciplina != null) || (this.idTurmaDisciplina != null && !this.idTurmaDisciplina.equals(other.idTurmaDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aTurmaDisciplinas[idTurmaDisciplina=" + idTurmaDisciplina + " ]";
    }

 }
