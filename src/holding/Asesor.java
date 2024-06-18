
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
    
    public Asesor(String us, String pas /*, int cod*/){
        //setCodigo(cod);
        setId(us);
        setContraseña(pas);
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
    
    public boolean coincideArea(Area area){
        Area aAux;
        for(int i = 0; i < areas.size() ;i++){
            aAux = areas.get(i);
            if(area.getNombre().equals(aAux.getNombre())){
                return true;
            }
        }
        return false;
    }
    
    public boolean coincideEmpresa(Empresa empresa){
        Empresa eAux;
        for(int i = 0; i < empresas.size() ;i++){
            eAux = empresas.get(i);
            if(empresa.getNombre().equals(eAux.getNombre())){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean proceder(Sistema sistema){
        EntradaSalida.mostrarString("Hola asesor " + getId());
        int i=EntradaSalida.leerInt("Presione '1' para ver sus datos o cualquier otra tecla para salir :");
        int j;
        switch(i){
            case 1:
                EntradaSalida.mostrarString("Su codigo es: " + codigo);
                EntradaSalida.mostrarString("Nombre: " + nombre);
                EntradaSalida.mostrarString("Vive en : " + direccion);
                EntradaSalida.mostrarString("Trabaja en las Empresas: ");
            for (j=0; j<empresas.size(); j++) {
                EntradaSalida.mostrarString(empresas.get(j).getNombre());
            }
            for (j=0; j<empresas.size(); j++) {
                EntradaSalida.mostrarString(areas.get(j).getNombre());
            }

                
        }
        return true;
    }

    @Override
    public void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
