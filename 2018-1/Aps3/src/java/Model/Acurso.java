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
import javax.persistence.Entity;
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
@Entity
@MappedSuperclass
@Table(name = "acurso")
@XmlRootElement
public class Acurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCurso")
    private Integer idCurso;
    @Basic(optional = false)
    @Column(name = "idModalidade")
    private int idModalidade;
    @Basic(optional = false)
    @Column(name = "idProjtoCurso")
    private int idProjtoCurso;
    @Basic(optional = false)
    @Column(name = "nomeCurso")
    private String nomeCurso;
    @Column(name = "datacreate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacreate;
    @Column(name = "usuarioCreate")
    private String usuarioCreate;
    @Column(name = "dataUpdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUpdate;
    @Column(name = "usuarioUpdate")
    private String usuarioUpdate;

    public Acurso() {
    }

    public Acurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Acurso(Integer idCurso, int idModalidade, int idProjtoCurso, String nomeCurso) {
        this.idCurso = idCurso;
        this.idModalidade = idModalidade;
        this.idProjtoCurso = idProjtoCurso;
        this.nomeCurso = nomeCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(int idModalidade) {
        this.idModalidade = idModalidade;
    }

    public int getIdProjtoCurso() {
        return idProjtoCurso;
    }

    public void setIdProjtoCurso(int idProjtoCurso) {
        this.idProjtoCurso = idProjtoCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Date getDatacreate() {
        return datacreate;
    }

    public void setDatacreate(Date datacreate) {
        this.datacreate = datacreate;
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
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acurso)) {
            return false;
        }
        Acurso other = (Acurso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Acurso[ idCurso=" + idCurso + " ]";
    }
    
}
