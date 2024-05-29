
package holding;

import java.util.ArrayList;
import java.io.Serializable;

public class Asesor extends Usuario implements Serializable {
    
    private int codigo;
    private String nombre;
    private String direccion;
    private ArrayList<Integer> fechaCom;
    private ArrayList<Empresa> empresas;
    
    public Asesor(String us, String pas /*, int cod*/){
        //setCodigo(cod);
        setId(us);
        setContraseña(pas);
    }
    
    private void setCodigo(int cod){
        codigo = cod;
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public ArrayList<Empresa> getEmpresas(){
        return empresas;
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
