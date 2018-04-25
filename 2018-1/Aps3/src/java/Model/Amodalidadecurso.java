/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neimarmoises
 */
@MappedSuperclass
@Table(name = "amodalidadecurso")
@XmlRootElement
public class Amodalidadecurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idModalidade")
    private Short idModalidade;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "dataCreate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCreate;
    @Basic(optional = false)
    @Column(name = "usuarioCreate")
    private String usuarioCreate;
    @Basic(optional = false)
    @Column(name = "dataUpdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUpdate;
    @Basic(optional = false)
    @Column(name = "usuarioUpdate")
    private String usuarioUpdate;

    public Amodalidadecurso() {
    }

    public Amodalidadecurso(Short idModalidade) {
        this.idModalidade = idModalidade;
    }

    public Amodalidadecurso(Short idModalidade, String descricao, Date dataCreate, String usuarioCreate, Date dataUpdate, String usuarioUpdate) {
        this.idModalidade = idModalidade;
        this.descricao = descricao;
        this.dataCreate = dataCreate;
        this.usuarioCreate = usuarioCreate;
        this.dataUpdate = dataUpdate;
        this.usuarioUpdate = usuarioUpdate;
    }

    public Short getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(Short idModalidade) {
        this.idModalidade = idModalidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(Date dataCreate) {
        this.dataCreate = dataCreate;
    }

    public String getUsuarioCreate() {
        return usuarioCreate;
    }

    public void setUsuarioCreate(String usuarioCreate) {
        this.usuarioCreate = usuarioCreate;
    }

    public Date getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(Date dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public String getUsuarioUpdate() {
        return usuarioUpdate;
    }

    public void setUsuarioUpdate(String usuarioUpdate) {
        this.usuarioUpdate = usuarioUpdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModalidade != null ? idModalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amodalidadecurso)) {
            return false;
        }
        Amodalidadecurso other = (Amodalidadecurso) object;
        if ((this.idModalidade == null && other.idModalidade != null) || (this.idModalidade != null && !this.idModalidade.equals(other.idModalidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Amodalidadecurso[ idModalidade=" + idModalidade + " ]";
    }
    
}
