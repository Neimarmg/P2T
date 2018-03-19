/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author neimarmoises
 */
public class Curso implements  Serializable{
    int idCurso, idModalidade,idProjtoCurso;
    String nomeCurso;

    public Curso() {
    }

    public Curso(int idCurso, int idModalidade, int idProjtoCurso, String nomeCurso) {
        this.idCurso = idCurso;
        this.idModalidade = idModalidade;
        this.idProjtoCurso = idProjtoCurso;
        this.nomeCurso = nomeCurso;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
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
    
    
    
}
