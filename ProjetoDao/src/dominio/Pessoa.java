package dominio;

/**
 * @author lhries
 */
public class Pessoa {
    private int id, codCidade, codTipoPessoa;
    private String nome, telefone, email;

    public Pessoa() {
    }

    public Pessoa(int codCidade, int codTipoPessoa, String nome, String telefone, String email) {
        this.codCidade = codCidade;
        this.codTipoPessoa = codTipoPessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
           
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    

}
