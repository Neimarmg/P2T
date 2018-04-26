
package entidade;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LHRIES
 */
@Entity
@XmlRootElement
public class aCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCurso;
    
    @OneToOne
    aModalidadecurso modalidadecurso ;             
    private Long idModalidade;
    
    private Long idProjeto;
    private String descricao;
    
 
    public aCurso() {
    }

    public aCurso(Long idCurso, Long idModalidade, Long idProjeto, String descricao) {
        this.idCurso = idCurso;
        this.idModalidade = idModalidade;
        this.idProjeto = idProjeto;
        this.descricao = descricao;
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

    public Long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idModalidade fields are not set
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
        return "entidade.Modalidadecurso[idModalidade=" + idCurso + " ]";
    }
    
}
