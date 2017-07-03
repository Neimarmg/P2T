package dominio;

/**
 * @author lhries
 */
public class Pessoa {
    int id;
    String codCidade;
    String codTipoPessoa;
    String nome;
    String telefone;
    String email;

    public Pessoa() {
    }

    public Pessoa(String codCidade, String codTipoPessoa, String nome, String telefone, String email) {
        this.codCidade = codCidade;
        this.codTipoPessoa = codTipoPessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Pessoa(int id, String codCidade, String CodaTipoPesssoa, String nome, String telefone, String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(String codCidade) {
        this.codCidade = codCidade;
    }

    public String getCodTipoPessoa() {
        return codTipoPessoa;
    }

    public void setCodTipoPessoa(String codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }
 
    
}