
package holding;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Administrador extends Usuario implements Serializable {
    
    public Administrador(String u, String p) {
        setId(u);
        setContraseña(p);
        System.out.println("Cargados!!!");
    }
    
    @Override
    public boolean proceder(Sistema sistema){
        System.out.println("Hola Administrador " + getId());
        
        return true;
    }
    
    @Override
    public void mostrar(){
        System.out.println("Coordinador - Usuario: " + this.getId());
        System.out.println("Password: " + this.getContraseña());
        
    }
}
