
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
    private Pais sedeCentral;

    public Empresa(int codigo, String nombre, Pais sedeCentral) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sedeCentral = sedeCentral;
        this.fechaDeEntrada = LocalDate.now();
        paises = null;
        areas = null;
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
    
    /*0
    public void inscribirVendedor(Vendedor v){
        v.getEmpresas().add(this);
        cantVendedores++;
    }
    */
    public void mostrar(){
        System.out.println("Empresa: " + nombre);
        System.out.println("Codigo: " + codigo);
        //System.out.println("Cantidad de Vendedores: " + cantVendedores);
        //System.out.println("Paises: " + paises.size());
        /* Muestra de paises (no habilitar hasta la carga de algun pais)
        for(int i = 0; i<paises.size(); i++){
            System.out.println(paises.get(i));
        }
        */
        //System.out.println("Areas: " + areas.size());
        /* Muestra de areas (no habilitar hasta la carga de algun area)
        for(int i = 0; i<areas.size(); i++){
            System.out.println(areas.get(i));
        }
        */
        
    }
}
