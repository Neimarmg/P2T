package view;

import Api.Dao.PessoaBd.PessoaDaoBd;
import util.Console;
import view.menu.pessoaMenu;
import java.util.InputMismatchException;
import dominio.Pessoa;
import Api.Dao.PessoaBd.PessoaDao;
import java.util.List;

/**
 *
 * @author @Neimar
 */
public class PessoaUI {

    private final PessoaDao pessoaDao;

    public PessoaUI() {
        pessoaDao = new PessoaDaoBd();
    }

    public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println(pessoaMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case pessoaMenu.OP_CADASTRAR:
                        cadastrarPessoa();
                        break;
                    case pessoaMenu.OP_DELETAR:
                        deletarPessoa();
                        break;
                    case pessoaMenu.OP_ATUALIZAR:
                        atualizarPessoa();
                        break;
                    case pessoaMenu.OP_LISTAR:
                        mostrarPessoa();
                        break;
                    case pessoaMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != pessoaMenu.OP_SAIR);
    }

    private void cadastrarPessoa() {
        String nome = Console.scanString("Nome: ");
        String codCidade = Console.scanString("cod cidade: ");
        String codTipoPessoa = Console.scanString("Cod tipo pessoa: ");
        String telefone = Console.scanString("Telefone: ");
        String email = Console.scanString("Email: ");

        
        pessoaDao.salvar(new Pessoa(codCidade , codTipoPessoa, nome, telefone ,email));

        System.out.println("Pessoa " + nome+codCidade+codTipoPessoa+telefone+email + " cadastrado com sucesso!");
    }

  
    public void mostrarPessoa() {
        List<Pessoa> listaPessoa = pessoaDao.listar();
        this.mostrarPessoa(listaPessoa);
    }

    private void deletarPessoa() {
        int id = Console.scanInt("Id do psuario a ser deletado: ");
        Pessoa pessoa = pessoaDao.procurarPorId(id);
        this.mostrarPessoa(pessoa);
        if (UIUtil.getConfirmacao("Realmente deseja excluir esse uaciente?")) {
            pessoaDao.deletar(pessoa);
            System.out.println("Usuario deletado com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        }
    }

    private void atualizarPessoa() {
        int id = Console.scanInt("Id do usuario a ser deletado: ");
        Pessoa pessoa = pessoaDao.procurarPorId(id);
        this.mostrarPessoa(pessoa);

        System.out.println("Digite os dados do usuario que quer alterar [Vazio caso nao queira]");
        String nome = Console.scanString("Nome: ");
        String codCidade = Console.scanString("Cod cidade: ");
        String CodTipoPessoa = Console.scanString("Cod tipo pesssoa: ");
        String telefone = Console.scanString("Telefone: ");
        String email = Console.scanString("Email: ");
        if (!nome.isEmpty()) {
            pessoa.setNome(nome);
        }
        if (!codCidade.isEmpty()) {
            pessoa.setNome(codCidade);
        }        
        if (!CodTipoPessoa.isEmpty()) {
            pessoa.setNome(CodTipoPessoa);
        }
        if (!telefone.isEmpty()) {
            pessoa.setEmail(telefone);
        }
        if (!email.isEmpty()) {
            pessoa.setEmail(email);
        }

        pessoaDao.atualizar(pessoa);
        System.out.println("Pessoa " + nome + " atualizado com sucesso!");

    }


    private void mostrarPessoa(Pessoa pessoa) {
        System.out.println("-----------------------------");
        System.out.println("Pessoa");
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Cod cidade: " + pessoa.getCodCidade());
        System.out.println("Cod tipo pessoa: " + pessoa.getCodTipoPessoa());
        System.out.println("Telefone: " + pessoa.getTelefone());
        System.out.println("Email: " + pessoa.getEmail());
        
        System.out.println("-----------------------------");
    }

    private void mostrarPessoa(List<Pessoa> listaPessoa) {
        if (listaPessoa.isEmpty()) {
            System.out.println("Pessoa não encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "NOME") + "\t"
                    + String.format("%-20s", "|Cod cidade") + "\t"
                    + String.format("%-20s", "|Cod tipo pessoa") + "\t"                    
                    + String.format("%-20s", "|Telefone") + "\t"
                    + String.format("%-20s", "|Email"));
            for (Pessoa pessoa : listaPessoa) {
                System.out.println(String.format("%-10s", pessoa.getNome()) + "\t"
                        + String.format("%-20s", "|" + pessoa.getCodCidade()) + "\t"
                        + String.format("%-20s", "|" + pessoa.getCodTipoPessoa()) + "\t"
                        + String.format("%-20s", "|" + pessoa.getTelefone()) + "\t"
                        + String.format("%-20s", "|" + pessoa.getEmail()));           
            }
        }
    }
}
