
package holding;

import java.util.ArrayList;
import java.io.Serializable;

public class Asesor extends Usuario implements Serializable {
    
    private int codigo;
    ArrayList<Empresa> empresas;
    
    public Asesor(String us, String pas){
        setId(us);
        setContraseña(pas);
    }
    
    @Override
    public boolean proceder(Sistema sistema){
        EntradaSalida.mostrarString("Hola asesor " + getId());
        return true;
    }

    @Override
    public void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
