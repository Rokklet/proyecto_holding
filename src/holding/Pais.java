
package holding;

public class Pais {
    
    private String nombre;
    private int pbi;
    private String capital;
    
    public Pais(String nombre, int pbi,String capital){
        this.nombre = nombre;
        this.pbi = pbi;
        this.capital = capital;
    }
    
    public void mostrarPais(){
        EntradaSalida.mostrarString("" + nombre );
}
}
