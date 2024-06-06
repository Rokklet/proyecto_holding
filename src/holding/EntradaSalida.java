
package holding;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class EntradaSalida {
    
    static Scanner leerInt = new Scanner(System.in);
    static Scanner leerString = new Scanner(System.in);
    static Scanner leerBool = new Scanner(System.in);

    public static String leerString(String texto) {
        System.out.print(texto);
        String st = leerString.nextLine();
        return (st == null ? "" : st);
    }

    public static boolean leerBoolean(String texto) {
        System.out.println(texto);
       boolean i; // = leerBool.hasNext();
       String s=leerString("");
       if("1".equals(s)){
           i = true;
           return i;
       }else{
           i = false;
           return i;
       }
       //return i;
    }
    
    public static void mostrarInt(int i){
        System.out.println(i);
    }
    
    public static void mostrarString(String s) {
        System.out.println(s);
    }
    
    public static void mostrarError(String s) {
        System.err.println(s);
    }

    public static int leerInt(String texto) {
        System.out.println(texto);
        int i = leerInt.nextInt();
        return i;
    }
    
}
