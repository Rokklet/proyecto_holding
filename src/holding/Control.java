
package holding;

import java.io.IOException;
        
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
       
