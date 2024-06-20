
package holding;

import java.io.Serializable;


public class Area implements Serializable {
    
    private String nombre;
    private String descripcion;
    
    public Area(String nombre, String des){
        this.nombre = nombre;
        descripcion = des;
    }
    
    public void mostrar(){
        EntradaSalida.mostrarString("___________________________________________________________________");
        EntradaSalida.mostrarString(nombre);
        EntradaSalida.mostrarString("Descripcion: " + descripcion);
        EntradaSalida.mostrarString("___________________________________________________________________");
        
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
}
