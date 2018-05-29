
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
public class gPessoa implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;
    
    @OneToOne           
     
    //@JoinColumn(columnDefinition="idProfissao", referencedColumnName="idUtilitario")
    Collection<gUtilitarios> profissao;
    
    @OneToOne
    //@JoinColumn(columnDefinition ="tipoPessoa", referencedColumnName="idUtilitario")
    Collection<gUtilitarios> tipoPessoa;
    
    
    private String nome;
    private String cpf;
    private String rg;
    private boolean ativa;
    private String cref;
    
    //@Temporal(TemporalType.DATE)
    //private Date dataNascimento; 
    
    public gPessoa() {
    }

    public gPessoa(Long idPessoa, Collection<gUtilitarios> profissao, Collection<gUtilitarios> tipoPessoa, String nome, String cpf, String rg, boolean ativa, String cref) {
        this.idPessoa = idPessoa;
        this.profissao = profissao;
        this.tipoPessoa = tipoPessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.ativa = ativa;
        this.cref = cref;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Collection<gUtilitarios> getProfissao() {
        return profissao;
    }

    public void setProfissao(Collection<gUtilitarios> profissao) {
        this.profissao = profissao;
    }

    public Collection<gUtilitarios> getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Collection<gUtilitarios> tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
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
        hash += (idPessoa != null ? idPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPessoa fields are not set
        if (!(object instanceof gPessoa)) {
            return false;
        }
        gPessoa other = (gPessoa) object;
        if ((this.idPessoa == null && other.idPessoa != null) || (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aPessoa[iPessoa=" + idPessoa + " ]";
    }
    
    
    
}
