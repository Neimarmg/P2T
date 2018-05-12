
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;




/**
 *
 * @author neimarmoises
 */
@Entity
@XmlRootElement
public class aProjetocurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjetoCurso;
 
    @OneToMany
    //@Column(name="idModalidade")
    Collection<aModalidadecurso>modalidadecurso;
    
    @OneToMany
    //@Column(name="filial")
    Collection<gFiliais>filial;
     
    private String descricaoProjeto;
    private double valorCurso ;
    

    public aProjetocurso() {
    }

    public aProjetocurso(Long idProjetoCurso, Collection<aModalidadecurso> modalidadecurso, Collection<gFiliais> filial, String descricaoProjeto, double valorCurso) {
        this.idProjetoCurso = idProjetoCurso;
        this.modalidadecurso = modalidadecurso;
        this.filial = filial;
        this.descricaoProjeto = descricaoProjeto;
        this.valorCurso = valorCurso;
    }

    public Long getIdProjetoCurso() {
        return idProjetoCurso;
    }

    public void setIdProjetoCurso(Long idProjetoCurso) {
        this.idProjetoCurso = idProjetoCurso;
    }

    public Collection<aModalidadecurso> getModalidadecurso() {
        return modalidadecurso;
    }

    public void setModalidadecurso(Collection<aModalidadecurso> modalidadecurso) {
        this.modalidadecurso = modalidadecurso;
    }

    public Collection<gFiliais> getFilial() {
        return filial;
    }

    public void setFilial(Collection<gFiliais> filial) {
        this.filial = filial;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public double getValorCurso() {
        return valorCurso;
    }

    public void setValorCurso(double valorCurso) {
        this.valorCurso = valorCurso;
    }
    

 
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProjetoCurso != null ? idProjetoCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idProjetoCurso fields are not set
        if (!(object instanceof aProjetocurso)) {
            return false;
        }
        aProjetocurso other = (aProjetocurso) object;
        if ((this.idProjetoCurso == null && other.idProjetoCurso != null) || (this.idProjetoCurso != null && !this.idProjetoCurso.equals(other.idProjetoCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aProjetocurso[idProjetoCurso=" + idProjetoCurso + " ]";
    }
           
}
