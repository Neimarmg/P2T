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
public class Bancos {
    int codbanco;
    String nroBanco;
    String nemeBanco;
    String webPage;

    public Bancos(int codbanco, String nroBanco, String nemeBanco, String webPage) {
        this.codbanco = codbanco;
        this.nroBanco = nroBanco;
        this.nemeBanco = nemeBanco;
        this.webPage = webPage;
    }

    public int getCodbanco() {
        return codbanco;
    }

    public void setCodbanco(int codbanco) {
        this.codbanco = codbanco;
    }

    public String getNroBanco() {
        return nroBanco;
    }

    public void setNroBanco(String nroBanco) {
        this.nroBanco = nroBanco;
    }

    public String getNemeBanco() {
        return nemeBanco;
    }

    public void setNemeBanco(String nemeBanco) {
        this.nemeBanco = nemeBanco;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }
    
    
}
