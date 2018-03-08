package view;

import util.Console;
import view.menu.pessoaMenu;
import java.util.InputMismatchException;

import Api.Dao.TipoUtilitarioBd.TipoUtilitarioDao;
import Api.Dao.TipoUtilitarioBd.TipoUtilitarioDaoBd;
import dominio.TipoUtilitarios;
import java.util.List;


/**
 *
 * @author @Neimar
 */
public class TipoUtulitarioUI {

    private final TipoUtilitarioDao tipoUtilitarioDao;

    public TipoUtulitarioUI() {
        tipoUtilitarioDao = new TipoUtilitarioDaoBd();
    }

    public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println(pessoaMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case pessoaMenu.OP_CADASTRAR:
                        cadastrarTipoUtilitario();
                        break;
                    case pessoaMenu.OP_DELETAR:
                        deletarTipoUtilitario();
                        break;
                    case pessoaMenu.OP_ATUALIZAR:
                        atualizarTipoUtilitario();
                        break;
                    case pessoaMenu.OP_LISTAR:
                        TipoUtulitarioUI.this.mostrarTipoUtilitario();
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

    private void cadastrarTipoUtilitario() {
        String nome = Console.scanString("Nome: ");
       

        
        tipoUtilitarioDao.salvar(new TipoUtilitarios(nome));

        System.out.println("Pessoa " + nome + " cadastrado com sucesso!");
    }

  
    public void mostrarTipoUtilitario() {
      /*  List<TipoUtilitarios> listaTipoUtilitario = new TipoUtilitarioDao.lstar();
        this.mostrarTipoUtilitario(listaTipoUtilitario);*/
    }

    private void deletarTipoUtilitario() {
    /*    int id = Console.scanInt("Id do psuario a ser deletado: ");
        TipoUtilitarios tipoUtilitarios = new t;
        this.mostrarTipoUtilitario(TipoUtilitarios);
        if (UIUtil.getConfirmacao("Realmente deseja excluir esse uaciente?")) {
            pessoaDao.deletar(pessoa);
            System.out.println("Usuario deletado com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        }*/
    }

    private void atualizarTipoUtilitario() {
        int id = Console.scanInt("Id do usuario a ser deletado: ");
        TipoUtilitarios tipoUtilitarios = tipoUtilitarioDao.procurarPorId(id);
        this.mostrarTipoUtilitario(tipoUtilitarios);

        System.out.println("Digite os dados do usuario que quer alterar [Vazio caso nao queira]");
        String nome = Console.scanString("Nome: ");
        
        if (!nome.isEmpty()) {
            tipoUtilitarios.setDescTipoUtilitario(nome);
        }
        
        
        tipoUtilitarios.atualizar(tipoUtilitarios);
        System.out.println("Tipo " + nome + " atualizado com sucesso!");
    }


    private void mostrarTipoUtilitario(TipoUtilitarios tipoUtilitarios) {
        System.out.println("-----------------------------");
        System.out.println("Pessoa");
        System.out.println("Nome: " );
       
        
        System.out.println("-----------------------------");
    }

    private void mostrarTipoUtilitario(List<TipoUtilitarios> listaTipoUtilitario) {
        if (listaTipoUtilitario.isEmpty()) {
            System.out.println("Tipo não encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "NOME") + "\t");
            
            for (TipoUtilitarios tipoUtilitarios : listaTipoUtilitario) {
                System.out.println(String.format("%-10s", tipoUtilitarios.getDescTipoUtilitario()) + "\t");           
            }
        }
    }
}
