
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
public class gPessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iPessoa;
    private Long idTipoPessoa;
    private Long idProfissao;    
    private String nome;
    private String cpf;
    private String RG;
    private boolean Ativa;
    private String cref;

    public gPessoa() {
    }

    public gPessoa(Long iPessoa, Long idTipoPessoa, Long idProfissao, String nome, String cpf, String RG, boolean Ativa, String cref) {
        this.iPessoa = iPessoa;
        this.idTipoPessoa = idTipoPessoa;
        this.idProfissao = idProfissao;
        this.nome = nome;
        this.cpf = cpf;
        this.RG = RG;
        this.Ativa = Ativa;
        this.cref = cref;
    }

    public Long getiPessoa() {
        return iPessoa;
    }

    public void setiPessoa(Long iPessoa) {
        this.iPessoa = iPessoa;
    }

    public Long getIdTipoPessoa() {
        return idTipoPessoa;
    }

    public void setIdTipoPessoa(Long idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    public Long getIdProfissao() {
        return idProfissao;
    }

    public void setIdProfissao(Long idProfissao) {
        this.idProfissao = idProfissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public boolean isAtiva() {
        return Ativa;
    }

    public void setAtiva(boolean Ativa) {
        this.Ativa = Ativa;
    }

    public String getCref() {
        return cref;
    }

    public void setCref(String cref) {
        this.cref = cref;
    }
 
 
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iPessoa != null ? iPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the iPessoa fields are not set
        if (!(object instanceof gPessoa)) {
            return false;
        }
        gPessoa other = (gPessoa) object;
        if ((this.iPessoa == null && other.iPessoa != null) || (this.iPessoa != null && !this.iPessoa.equals(other.iPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aPessoa[iPessoa=" + iPessoa + " ]";
    }
    
    
    
}
