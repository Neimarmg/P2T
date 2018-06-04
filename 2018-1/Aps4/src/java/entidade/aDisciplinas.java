
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
public class aDisciplinas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDisciplina;

    @OneToMany
    Collection<aCurso>cursos;   
    
    @OneToMany
    Collection<aProjetocurso>projetocurso; 
    
    private String nomeDisciplina;
    private String ementa;

    public aDisciplinas() {
    }

    public aDisciplinas(Long idDisciplina, Collection<aCurso> cursos, Collection<aProjetocurso> projetocursos, String nomeDisciplina, String ementa) {
        this.idDisciplina = idDisciplina;
        this.cursos = cursos;
        this.projetocurso = projetocursos;
        this.nomeDisciplina = nomeDisciplina;
        this.ementa = ementa;
    }

    public Long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Collection<aCurso> getCursos() {
        return cursos;
    }

    public void setCursos(Collection<aCurso> cursos) {
        this.cursos = cursos;
    }

    public Collection<aProjetocurso> getProjetocurso() {
        return projetocurso;
    }

    public void setProjetocurso(Collection<aProjetocurso> projetocurso) {
        this.projetocurso = projetocurso;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDisciplina != null ? idDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idDisciplina fields are not set
        if (!(object instanceof aDisciplinas)) {
            return false;
        }
        aDisciplinas other = (aDisciplinas) object;
        if ((this.idDisciplina == null && other.idDisciplina != null) || (this.idDisciplina != null && !this.idDisciplina.equals(other.idDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.aDisciplinas[idDisciplina=" + idDisciplina + " ]";
    }
    
    
    
}
