
package holding;

import java.io.Serializable;

public class Pais implements Serializable {
    
    private String nombre;
    private String capital;
    private int pbi;
    private int cantidadHabitantes;
    
    public Pais(String nombre, String capital, int pbi, int cantidadHabitantes){
        this.nombre = nombre;
        this.capital = capital;
        this.pbi = pbi;
        this.cantidadHabitantes = cantidadHabitantes;
    }
    
    public void mostrar(){
        EntradaSalida.mostrarString("" + nombre + "\t" + capital + "\t" + pbi + "\t" + cantidadHabitantes);
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
