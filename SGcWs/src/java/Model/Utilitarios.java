/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author neimarmoises
 */
public class Utilitarios {
    int codUtilitario;
    int codTipoUtilitario;
    String descUtilitario;

    public Utilitarios(int codUtilitario, int codTipoUtilitario, String descUtilitario) {
        this.codUtilitario = codUtilitario;
        this.codTipoUtilitario = codTipoUtilitario;
        this.descUtilitario = descUtilitario;
    }

    public Utilitarios(int codUtilitario, String descUtilitario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCodUtilitario() {
        return codUtilitario;
    }

    public void setCodUtilitario(int codUtilitario) {
        this.codUtilitario = codUtilitario;
    }

    public int getCodTipoUtilitario() {
        return codTipoUtilitario;
    }

    public void setCodTipoUtilitario(int codTipoUtilitario) {
        this.codTipoUtilitario = codTipoUtilitario;
    }

    public String getDescUtilitario() {
        return descUtilitario;
    }

    public void setDescUtilitario(String descUtilitario) {
        this.descUtilitario = descUtilitario;
    }
    
    
    
}
