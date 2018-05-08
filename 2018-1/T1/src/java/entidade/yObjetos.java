
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
public class yObjetos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObjetos;
    private Long idAplicacao;
    private Long idTipoUtilirario;
    private Long idSubGrupo;
    private String aplicacao;
    private String nomeObjeto;
    private String detalhesObjeto;
    private String descTipoObjeto;

    public yObjetos() {
    }

    public yObjetos(Long idObjetos, Long idAplicacao, Long idTipoUtilirario, Long idSubGrupo, String aplicacao, String nomeObjeto, String detalhesObjeto, String descTipoObjeto) {
        this.idObjetos = idObjetos;
        this.idAplicacao = idAplicacao;
        this.idTipoUtilirario = idTipoUtilirario;
        this.idSubGrupo = idSubGrupo;
        this.aplicacao = aplicacao;
        this.nomeObjeto = nomeObjeto;
        this.detalhesObjeto = detalhesObjeto;
        this.descTipoObjeto = descTipoObjeto;
    }

    public Long getIdObjetos() {
        return idObjetos;
    }

    public void setIdObjetos(Long idObjetos) {
        this.idObjetos = idObjetos;
    }

    public Long getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(Long idAplicacao) {
        this.idAplicacao = idAplicacao;
    }

    public Long getIdTipoUtilirario() {
        return idTipoUtilirario;
    }

    public void setIdTipoUtilirario(Long idTipoUtilirario) {
        this.idTipoUtilirario = idTipoUtilirario;
    }

    public Long getIdSubGrupo() {
        return idSubGrupo;
    }

    public void setIdSubGrupo(Long idSubGrupo) {
        this.idSubGrupo = idSubGrupo;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public String getNomeObjeto() {
        return nomeObjeto;
    }

    public void setNomeObjeto(String nomeObjeto) {
        this.nomeObjeto = nomeObjeto;
    }

    public String getDetalhesObjeto() {
        return detalhesObjeto;
    }

    public void setDetalhesObjeto(String detalhesObjeto) {
        this.detalhesObjeto = detalhesObjeto;
    }

    public String getDescTipoObjeto() {
        return descTipoObjeto;
    }

    public void setDescTipoObjeto(String descTipoObjeto) {
        this.descTipoObjeto = descTipoObjeto;
    }
    
    


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjetos != null ? idObjetos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idObjetos fields are not set
        if (!(object instanceof yObjetos)) {
            return false;
        }
        yObjetos other = (yObjetos) object;
        if ((this.idObjetos == null && other.idObjetos != null) || (this.idObjetos != null && !this.idObjetos.equals(other.idObjetos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.yObjetos[idObjetos=" + idObjetos + " ]";
    }
    
    
    
}
