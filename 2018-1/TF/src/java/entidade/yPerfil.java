
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
public class yPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfil;
    private Long idAplicacao;
    private Long IdTipoPerfil;
    private String descricaoPerfil;

    public yPerfil() {
    }

    public yPerfil(Long idPerfil, Long idAplicacao, Long IdTipoPerfil, String descricaoPerfil) {
        this.idPerfil = idPerfil;
        this.idAplicacao = idAplicacao;
        this.IdTipoPerfil = IdTipoPerfil;
        this.descricaoPerfil = descricaoPerfil;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Long getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(Long idAplicacao) {
        this.idAplicacao = idAplicacao;
    }

    public Long getIdTipoPerfil() {
        return IdTipoPerfil;
    }

    public void setIdTipoPerfil(Long IdTipoPerfil) {
        this.IdTipoPerfil = IdTipoPerfil;
    }

    public String getDescricaoPerfil() {
        return descricaoPerfil;
    }

    public void setDescricaoPerfil(String descricaoPerfil) {
        this.descricaoPerfil = descricaoPerfil;
    }

    
    
    


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPerfil fields are not set
        if (!(object instanceof yPerfil)) {
            return false;
        }
        yPerfil other = (yPerfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.yPerfil[idPerfil=" + idPerfil + " ]";
    }
    
    
    
}
