
package holding;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;

public class Asesor extends Usuario implements Serializable {
    
    private int codigo;
    private String nombre;
    private String direccion;
    private ArrayList<LocalDate> fechaEntrada;
    private ArrayList<Empresa> empresas;
    private ArrayList<Area> areas;
    
    public Asesor(String us, String pas , int cod){
        setId(us);
        setContraseña(pas);
        codigo = cod;
        this.areas = new ArrayList<Area>();
        this.empresas = new ArrayList<Empresa>();
        this.fechaEntrada = new ArrayList<LocalDate>();
    }
    
    public void setCodigo(int cod){
        codigo = cod;
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public ArrayList<Empresa> getEmpresas(){
        return empresas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<LocalDate> getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(ArrayList<LocalDate> fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }
    
    
    
    public boolean coincideEmpresa(Empresa empresa){
        
        if(empresas.contains(empresa)){
            return true;
        }
        return false;
    }
    
    public boolean coincideArea(Area a){
        if (areas.contains(a)){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public boolean proceder(Sistema sistema){
        EntradaSalida.mostrarString("\n\nHola asesor " + getId() + "\n");
        mostrar();
        EntradaSalida.mostrarString("\n\nAdios " + getId() + "\n");
        return true;
    }

    @Override
    public void mostrar() {
        EntradaSalida.mostrarString("___________________________________________________________________");
        EntradaSalida.mostrarString("Su codigo es: " + codigo);
        EntradaSalida.mostrarString("Nombre: " + nombre);
        EntradaSalida.mostrarString("Su direccion en : " + direccion);
        EntradaSalida.mostrarString("___________________________________________________________________");
        EntradaSalida.mostrarString("Asesora en las empresas: ");
        for (int i=0; i<empresas.size(); i++) {
            Empresa empresa;
            empresa =  empresas.get(i);
            EntradaSalida.mostrarString(empresa.getNombre());
        }
        EntradaSalida.mostrarString("___________________________________________________________________");
        EntradaSalida.mostrarString("Asesora en las areas:");
        for (int i=0; i<areas.size(); i++) {
            EntradaSalida.mostrarString(areas.get(i).getNombre() + "\t|Descripcion: " + areas.get(i).getDescripcion() + "\t|Fecha de Inicio en el Area: " + fechaEntrada.get(i));
        }
        EntradaSalida.mostrarString("___________________________________________________________________");
        }
}
