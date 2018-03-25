/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import javafx.scene.control.DatePicker;


/**
 *
 * @author neimarmoises
 */
public class Globais {    
    private static int contador=0;
    
    public static int getContador ( boolean  contar, boolean limparAntes) {
        
        if (contar ==  true ) {
            if(limparAntes == true){
               Globais.contador = 0 ;
               Globais.contador = 1 ;
            }else{
                Globais.contador ++ ; // Conta ações em tempo de execução
            }        
        } else {
                 Globais.contador =  0 ; // Limpa variável contadora
        }        
      
        return Globais.contador;
    }

    public static int getContador() {
        return contador;
    }
    
    
    public static String dataSql(String data){
        String dia,mes,ano;
        dia = data.substring(0, 2);
        mes = data.substring(3, 5);
        ano = data.substring(6);
        
        return ano+"-"+mes+"-"+dia;
    }
    
    
    public static String dataJava(String data){
        String dia,mes,ano;
        dia = data.substring(0, 2);
        mes = data.substring(3, 5);
        ano = data.substring(6);
        
        return dia+"/"+mes+"/"+ano;
    }
   
    
}
