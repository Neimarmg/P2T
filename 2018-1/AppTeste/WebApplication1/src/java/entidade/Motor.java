package entidade;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lhries
 */
@XmlRootElement
public class Motor implements Serializable{
    private int id;
    private String nome, descricao, uso;
  
    
    public Motor() {
    }

    public Motor(int id, String nome, String descricao, String uso) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.uso = uso;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUso() {
        return uso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }
    
    
}
