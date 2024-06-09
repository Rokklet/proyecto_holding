
package holding;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


public class Empresa implements Serializable {
    
    private int codigo;
    private String nombre;
    private int facturacion;
    private int cantVendedores;
    private LocalDate fechaDeEntrada;
    private ArrayList<Area> areas;
    private ArrayList<Pais> paises;
    private Pais sedeCentral;

    public Empresa(int codigo, String nombre, Pais sedeCentral) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sedeCentral = sedeCentral;
        this.fechaDeEntrada = LocalDate.now();

        this.paises = new ArrayList<Pais>();
        this.areas = new ArrayList<Area>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(int facturacion) {
        this.facturacion = facturacion;
    }

    public int getCantVendedores() {
        return cantVendedores;
    }

    public void setCantVendedores(int cantVendedores) {
        this.cantVendedores = cantVendedores;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public ArrayList<Pais> getPaises() {
        return paises;
    }

    public void setPaises(ArrayList<Pais> paises) {
        this.paises = paises;
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
    
    public boolean coincidePais(Pais pais){
        Pais pAux;
        for(int i = 0; i < paises.size() ;i++){
            pAux = paises.get(i);
            if(pais.getNombre().equals(pAux.getNombre())){
                return false;
            }
        }
        return true;
    }
    
    public void mostrar(){
        EntradaSalida.mostrarString("Empresa: " + nombre);
        EntradaSalida.mostrarString("Codigo: " + codigo);
        EntradaSalida.mostrarString("Fecha de ingreso en el holding: " + fechaDeEntrada);
        EntradaSalida.mostrarString("Pais cede de la empresa: ");
        sedeCentral.mostrar();
        EntradaSalida.mostrarString("Paises donde la empresa desarrolla actividades: ");
        for(int i=0;i<paises.size();i++){
            Pais pais;
            pais = paises.get(i);
            pais.mostrar();
        }
        EntradaSalida.mostrarString("Areas que cubre la empresa: ");
        for(int i=0;i<areas.size();i++){
            Area area;
            area = areas.get(i);
            area.mostrar();
        }
        EntradaSalida.mostrarString("La facturacion de la empresa es: " + facturacion);
        
    }
}
