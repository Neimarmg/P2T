
package entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javafx.print.Collation;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neimarmoises
 */
@Entity
@XmlRootElement
public class aModalidadecurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModalidade;
    private String descricao;
    
  
    public aModalidadecurso() {
    }

    public aModalidadecurso(Long idModalidade, String descricao) {
        this.idModalidade = idModalidade;
        this.descricao = descricao;
    }
    
    public Long getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(Long idModalidade) {
        this.idModalidade = idModalidade;
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
        hash += (idModalidade != null ? idModalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idModalidade fields are not set
        if (!(object instanceof aModalidadecurso)) {
            return false;
        }
        aModalidadecurso other = (aModalidadecurso) object;
        if ((this.idModalidade == null && other.idModalidade != null) || (this.idModalidade != null && !this.idModalidade.equals(other.idModalidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Modalidadecurso[idModalidade=" + idModalidade + " ]";
    }
    
    
    
}
