
package holding;

import java.time.LocalDate;
import java.util.ArrayList;


public class Empresa {
    
    private int codigo;
    private String nombre;
    private int facturacion;
    private int cantVendedores;
    private LocalDate fechaDeEntrada;
    private ArrayList<Area> areas;
    private ArrayList<Pais> paises;

    public Empresa(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaDeEntrada = LocalDate.now();
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
    
    
    
}
