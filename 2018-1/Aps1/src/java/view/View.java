package view;



import java.sql.Date;
import java.util.Scanner;

public class View {	

	static Scanner var = new Scanner(System.in);
	
	public static void msg(Object desc) {   
		System .out.print(desc);
	}
	
	
	public static void msge(Object desc) {   
		System .err.print(desc);
	}
	
	
	/**
	 * @param desc
	 */
	public static void msgcr(Object desc) {
		System .out.print(
			  "\n==========================================================================\n"
			+ desc
			+ "\n==========================================================================\n");
	}
	
	
	public static void msgc(Object desc) {
		System .out.print(
			"\n==========================================================================\n"
			+ desc);
	}


	public static void msgr(Object desc) {
		System .out.print(
			desc
			+ "\n==========================================================================\n");
	}	
	

	public static void msgl() {
		System .out.print(
			"\n==========================================================================\n");
	}
	
		
	public static void opcaoInvalida() {
		System .err.println("\nComando inválido. Tente novamente.");
	}
	
	
	public static void objetoNaoImplementado() {
		System .err.print("\nNão implementado.\n");
	}
	

	/**
	 * M�todo de label dos comandos do sistema
	 * @param nomeLabel
	 */
	public static void nomePaleta(Object nomeLabel) {
		System .out.println();
		if (nomeLabel.equals("")) {
			System .out.print("COMANDO: ");
		} else {
			System .out.print(nomeLabel + ": ");
		}
	}

	
	/**
	 * Valores "booleans"
	 * @param nomelabel
	 * @return
	 */
	public static int digitaNumero(String nomelabel) {
		nomePaleta(nomelabel);
		return var.nextInt();		
	}
	
	/**
	 * Valores "booleans"
	 * @param nomelabel
	 * @return
	 */
	public static float digitaFloat(String nomelabel) {
		nomePaleta(nomelabel);
		return var.nextFloat();		
	}
	
	/**
	 * M�todo de retorno que recebe a label com par�mento, e retorna
	 * uma string para filtros
	 * @param label
	 * @return
	 */
	public static String digitaString(String label) {
		nomePaleta(label);
		return var.next().toLowerCase();
	}
	
	
        public static String digitaData(String label) {
		nomePaleta(label);
		return var.nextLine();
	}
        
	public static String digitaObjeto(Object label) {
		nomePaleta(label);
		return var.next().toLowerCase();
	}
	
	
	public static void sair() {
		System.err.print("\nVocê optou por sair. Obrigado.");
	}

}