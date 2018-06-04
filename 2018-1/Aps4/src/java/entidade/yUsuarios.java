
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neimarmoises
 */
@Entity
@XmlRootElement
public class yUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    Collection<gPessoa>pessoa;
    
    private String idUsuario;
    private int senha;
    private boolean status;

    public yUsuarios() {
    }

    public yUsuarios(Long id, Collection<gPessoa> pessoas, String idUsuario, int senha, boolean status) {
        this.id = id;
        this.pessoa = pessoas;
        this.idUsuario = idUsuario;
        this.senha = senha;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<gPessoa> getPessoa() {
        return pessoa;
    }

    public void setPessoa(Collection<gPessoa> pessoa) {
        this.pessoa = pessoa;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    

    


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof yUsuarios)) {
            return false;
        }
        yUsuarios other = (yUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.yUsuarios[id=" + id + " ]";
    }
    
    
    
}
