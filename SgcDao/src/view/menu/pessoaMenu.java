package view.menu;

public class pessoaMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_DELETAR = 2;
    public static final int OP_ATUALIZAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar \n"
                + "2- Deletar \n"
                + "3- Atualizar \n"
                + "4- Listar \n"
                + "0- Sair"
                + "\n--------------------------------------");
    }    
}
