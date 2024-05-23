
package holding;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    
    private String usuario;
    private String contraseña;
    
    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }
    
    public String getContraseña(){
        return contraseña;
    }
    
    public void setId(String usuario){
        this.usuario = usuario;
    }
    
    public String getId(){
        return usuario;
    }
    
    public abstract void mostrar();
    
    public abstract boolean proceder(Sistema sitema);
}
