
package holding;

import java.io.IOException;
import javax.swing.*;
//import java.awt.event.*;
        
public class Control {
    
    public void ejecutar(){
    
    Sistema sistema = new Sistema();

        boolean seguir;
        try {
            sistema = sistema.deSerializar("holding.bin");
            seguir = EntradaSalida.leerBoolean("Holding de empresas\nDesea ingresar?\n"
                    + "1-Si\n"
                    + "0-No\n");
        } catch (Exception e) {
            Vista vista = new Vista();
            vista.setBounds(0, 0, 500, 500);
            vista.setResizable(false);
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
            JLabel label1 = new JLabel("Arranque Incial del sistema");
            label1.setBounds(160, 20, 300, 30);
            vista.add(label1);
            JLabel label2 = new JLabel("Usuario");
            label2.setBounds(160, 30, 300, 30);
            vista.add(label2);
            JLabel label3 = new JLabel("Usuario");
            label3.setBounds(160, 50, 300, 30);
            vista.add(label3);
            JTextField cajaTexto1 = new JTextField();
            cajaTexto1.setBounds(160, 30, 300, 30);
            String usuario = EntradaSalida.leerString("Arranque inicial del sistema.\n"
                    + "Sr(a) Admisnitrador(a), ingrese su nombre de usuario:");
            if (usuario.equals("")) {
                throw new NullPointerException("ERROR: El usuario no puede ser nulo.");
            }
            String contraseña = EntradaSalida.leerString("Ingrese su password:");
            if (contraseña.equals("")) {
                throw new NullPointerException("ERROR: La password no puede ser nula.");
            }
            sistema.getUsuarios().add(new Administrador(usuario, contraseña));
            try {
                sistema.serializar("holding.bin");
                EntradaSalida.mostrarString("El arranque ha sido exitoso. Ahora se debe reiniciar el sistema...");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            seguir = false;
        }

        while (seguir) {
            String usuario = EntradaSalida.leerString("Ingrese el usuario:");
            String contraseña = EntradaSalida.leerString("Ingrese la password:");

            Usuario u = sistema.buscarUsuario(usuario + ":" + contraseña);

            if (u == null) {
                EntradaSalida.mostrarString("ERROR: La combinacion usuario/password ingresada no es valida.");
            } else {
                seguir = u.proceder(sistema);  // POLIMORFISMO!!!!
            }
        }
    }
}
       
