/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.Jdbc;

/**
 *
 * @author neimarmoises
 */
public class bdConect {
    private final static String HOST = "localhost";
    private final static String PORT = "3306";
    private final static String BD = "id3695164_corpore";
    private final static String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+BD;
    private final static String USUARIO = "root";
    private final static String SENHA = "";

    public static String getHOST() {
        return HOST;
    }

    public static String getPORT() {
        return PORT;
    }

    public static String getBD() {
        return BD;
    }

    public static String getURL() {
        return URL;
    }

    public static String getUSUARIO() {
        return USUARIO;
    }

    public static String getSENHA() {
        return SENHA;
    }
    
    
}
