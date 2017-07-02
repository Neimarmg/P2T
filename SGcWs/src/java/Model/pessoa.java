/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neimarmoises
 */
@XmlRootElement
public class pessoa implements Serializable{
    int codPessoa;
    String nome;
    String telefone;
    String email;
    int codCidade;
    int codTipoPessoa;

    public pessoa(){}   
    
      
    public pessoa(int codPessoa, String nome, String telefone, String email, int codCidade, int codTipoPessoa) {
        this.codPessoa = codPessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.codCidade = codCidade;
        this.codTipoPessoa = codTipoPessoa;
    }

        
    public int getCodPessoa() {
        return codPessoa;
    }

    
    public void setCodPessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    
    public String getNome() {
        return nome;
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public String getTelefone() {
        return telefone;
    }

    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public int getCodCidade() {
        return codCidade;
    }

    
    public void setCodCidade(int codCidade) {
        this.codCidade = codCidade;
    }

    
    public int getCodTipoPessoa() {
        return codTipoPessoa;
    }

    
    public void setCodTipoPessoa(int codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }
    
    
    
    
    
    
}
