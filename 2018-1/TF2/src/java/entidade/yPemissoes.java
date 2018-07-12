
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
public class yPemissoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermissoes;
    private Long idPerfil;
    private Long idAplicacao;
    private Long idObjeto;
    private String nomeObjeto;
    private int ler;
    private int criar;
    private int atualizar;
    private int deletar;
    private int statusObjeto;

    public yPemissoes() {
    }

    public yPemissoes(Long idPermissoes, Long idPerfil, Long idAplicacao, Long idObjeto, String nomeObjeto, int ler, int criar, int atualizar, int deletar, int statusObjeto) {
        this.idPermissoes = idPermissoes;
        this.idPerfil = idPerfil;
        this.idAplicacao = idAplicacao;
        this.idObjeto = idObjeto;
        this.nomeObjeto = nomeObjeto;
        this.ler = ler;
        this.criar = criar;
        this.atualizar = atualizar;
        this.deletar = deletar;
        this.statusObjeto = statusObjeto;
    }

    public Long getIdPermissoes() {
        return idPermissoes;
    }

    public void setIdPermissoes(Long idPermissoes) {
        this.idPermissoes = idPermissoes;
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

    public Long getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Long idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNomeObjeto() {
        return nomeObjeto;
    }

    public void setNomeObjeto(String nomeObjeto) {
        this.nomeObjeto = nomeObjeto;
    }

    public int getLer() {
        return ler;
    }

    public void setLer(int ler) {
        this.ler = ler;
    }

    public int getCriar() {
        return criar;
    }

    public void setCriar(int criar) {
        this.criar = criar;
    }

    public int getAtualizar() {
        return atualizar;
    }

    public void setAtualizar(int atualizar) {
        this.atualizar = atualizar;
    }

    public int getDeletar() {
        return deletar;
    }

    public void setDeletar(int deletar) {
        this.deletar = deletar;
    }

    public int getStatusObjeto() {
        return statusObjeto;
    }

    public void setStatusObjeto(int statusObjeto) {
        this.statusObjeto = statusObjeto;
    }

    


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermissoes != null ? idPermissoes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPermissoes fields are not set
        if (!(object instanceof yPemissoes)) {
            return false;
        }
        yPemissoes other = (yPemissoes) object;
        if ((this.idPermissoes == null && other.idPermissoes != null) || (this.idPermissoes != null && !this.idPermissoes.equals(other.idPermissoes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.yPemissoes[idPermissoes=" + idPermissoes + " ]";
    }
    
    
    
}
