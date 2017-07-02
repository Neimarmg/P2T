package view;

import Api.Dao.PessoaBd.PessoaDaoBd;
import util.Console;
import view.menu.UsuarioMenu;
import java.util.InputMismatchException;
import java.util.List;
import dominio.Pessoa;
import Api.Dao.PessoaBd.PessoaDao;

/**
 *
 * @author @Neimar
 */
public class PessoaUI {

    private PessoaDao pessoaDao;

    public PessoaUI() {
        pessoaDao = new PessoaDaoBd();
    }

    public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println(UsuarioMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case UsuarioMenu.OP_CADASTRAR:
                        cadastrarPessoa();
                        break;
                    case UsuarioMenu.OP_DELETAR:
                        //deletarUuario();
                        break;
                    case UsuarioMenu.OP_ATUALIZAR:
                        //atualizarUsuario();
                        break;
                    case UsuarioMenu.OP_LISTAR:
                        //mostrarUsuarios();
                        break;
                    case UsuarioMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != UsuarioMenu.OP_SAIR);
    }

    private void cadastrarPessoa() {
        String nome = "Neimar moises "; //Console.scanString("Nome: ");
        int codCidade = 1 ; //Console.scanInt("cod cidade: ");
        int codTipoPessoa = 2; //Console.scanInt("Cod tipo pessoa: ");
        String telefone = "512659841" ; //Console.scanString("Telefone: ");
        String email = "Nairm@gd.com" ; //Console.scanString("Email: ");

        
        pessoaDao.salvar(new Pessoa(codCidade, codTipoPessoa, nome, telefone, email));

        System.out.println("Pessoa " + nome + " cadastrado com sucesso!");
    }
/*
    public void mostrarUsuarios() {
        List<Pessoa> listaUsuarios = pessoaDao.listar();
        this.mostrarUsuarios(listaUsuarios);
    }

    private void deletarUuario() {
        int id = Console.scanInt("Id do psuario a ser deletado: ");
        Pessoa usu = pessoaDao.procurarPorId(id);
        this.mostrarUsuario(usu);
        if (UIUtil.getConfirmacao("Realmente deseja excluir esse uaciente?")) {
            pessoaDao.deletar(usu);
            System.out.println("Usuario deletado com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        }
    }*/
/*
    private void atualizarUsuario() {
        int id = Console.scanInt("Id do usuario a ser deletado: ");
        Pessoa usu = pessoaDao.procurarPorId(id);
        this.mostrarUsuario(usu);

        System.out.println("Digite os dados do usuario que quer alterar [Vazio caso nao queira]");
        String nome = Console.scanString("Nome: ");
        String email = Console.scanString("Email: ");
        String username = Console.scanString("Username: ");
        if (!nome.isEmpty()) {
            usu.setNome(nome);
        }
        if (!email.isEmpty()) {
            usu.setEmail(email);
        }
        if (!username.isEmpty()) {
            usu.setUsername(username);
        }

        pessoaDao.atualizar(usu);
        System.out.println("Usuario " + nome + " atualizado com sucesso!");

    }


    private void mostrarUsuario(Pessoa u) {
        System.out.println("-----------------------------");
        System.out.println("Usuario");
        System.out.println("Nome: " + u.getNome());
        System.out.println("Email: " + u.getEmail());
        System.out.println("Username: " + u.getUsername());
        System.out.println("-----------------------------");
    }

    private void mostrarUsuarios(List<Pessoa> listaUsuarios) {
        if (listaUsuarios.isEmpty()) {
            System.out.println("Usuarios nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "NOME") + "\t"
                    + String.format("%-20s", "|EMAIL") + "\t"
                    + String.format("%-20s", "|USERNAME"));
            for (Pessoa usuario : listaUsuarios) {
                System.out.println(String.format("%-10s", usuario.getNome()) + "\t"
                        + String.format("%-20s", "|" + usuario.getEmail()) + "\t"
                        + String.format("%-20s", "|" + usuario.getUsername()));
            }
        }
    }*/
}
