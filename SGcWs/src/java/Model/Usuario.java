package Model;

/**
 * @author lhries
 */
public class Usuario {
    private int id;
    private String nome, email, username;

    public Usuario() {
    }

    
    public Usuario(String nome, String email, String username) {
        this.nome = nome;
        this.email = email;
        this.username = username;
    }

    public Usuario(int id, String nome, String email, String username) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
