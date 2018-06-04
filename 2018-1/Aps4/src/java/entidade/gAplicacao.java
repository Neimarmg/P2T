
package entidade;

import java.io.Serializable;
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
public class gAplicacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAplicacao;
    private String descricao;
    private String simbolo;

    public gAplicacao() {
    }

    public gAplicacao(Long idAplicacao, String descricao, String simbolo) {
        this.idAplicacao = idAplicacao;
        this.descricao = descricao;
        this.simbolo = simbolo;
    }

    public Long getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(Long idAplicacao) {
        this.idAplicacao = idAplicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

   
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAplicacao != null ? idAplicacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idAplicacao fields are not set
        if (!(object instanceof gAplicacao)) {
            return false;
        }
        gAplicacao other = (gAplicacao) object;
        if ((this.idAplicacao == null && other.idAplicacao != null) || (this.idAplicacao != null && !this.idAplicacao.equals(other.idAplicacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.gAplicaca[idAplicacao=" + idAplicacao + " ]";
    }
    
    
    
}
