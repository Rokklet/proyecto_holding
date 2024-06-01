
package holding;


public class Area {
    
    private String nombre;
    
    public Area(String nombre){
        this.nombre = nombre;
    }
    
    public void mostrar(){
        EntradaSalida.mostrarString("" + nombre);
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
