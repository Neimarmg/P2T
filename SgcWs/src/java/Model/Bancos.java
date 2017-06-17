package Model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author neimarmoises
 */
@XmlRootElement
public class Bancos implements Serializable {
    int codBanco;
    String nroBanco;
    String descricao;
    String webPage;
    
   
    public Bancos(int codBanco, String nroBanco, String descricao, String webPage ) {
        this.codBanco = codBanco;
        this.nroBanco = nroBanco;
        this.descricao = descricao;
        this.webPage = webPage;
    }
    
    public int getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(int codBanco) {
        this.codBanco = codBanco;
    }

    public String getNroBanco() {
        return nroBanco;
    }

    public void setNroBanco(String nroBanco) {
        this.nroBanco = nroBanco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }   
}
