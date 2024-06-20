package holding;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;

public class Vendedor extends Usuario implements Serializable {
    
    private String nombre;
    private LocalDate fechaEntrada;
    private int cod;
    private String direccion;
    private Empresa empresa;
    private Vendedor lider;
    private ArrayList<Vendedor> vendedores;
    
    public Vendedor(int cd, String u, String p, Empresa em, String nom, String dir) {
        setCod(cd);
        setId(u);
        nombre = nom;
        direccion = dir;
        setContraseña(p);
        setFechaEntrada();
        empresa = em;
        this.vendedores = new ArrayList<Vendedor>();
    }
    
    //Contructor vendedor en base a su lider
    public Vendedor(int cd, String us, String pas, Vendedor lid, String nom, String dir){
        setCod(cd);
        setId(us);
        nombre = nom;
        direccion = dir;
        setContraseña(pas);
        setFechaEntrada();
        empresa = lid.getEmpresa();
        lider = lid;
        this.vendedores = new ArrayList<Vendedor>();
    }
    
    @Override
    public boolean proceder(Sistema sistema){
        EntradaSalida.mostrarString("Hola vendedor " + getId());
        mostrar();
        int i = 0;
        do{
            i = EntradaSalida.leerInt("Precione 2 para salir al menu o 1 para salir del sistema");
            switch(i){
            case 1:
                return false;
            case 2:
                return true;
            default:
                EntradaSalida.mostrarString("Opcion no valida");
                break;
            }
        
        }while(i!=1 && i!=2);
        
        return true;
    }
    
    @Override
    public void mostrar(){
        EntradaSalida.mostrarString("___________________________________________________________________");
        EntradaSalida.mostrarString("Su codigo es: " + getCod());
        EntradaSalida.mostrarString("Nombre: " + getNombre());
        EntradaSalida.mostrarString("Direccion: " + direccion);
        if(lider!=null){
            EntradaSalida.mostrarString("Su team leader es el vendedor: " + lider.getNombre());
        }
        
        if(!vendedores.isEmpty()){
            EntradaSalida.mostrarString("Usted tiene a su cargo a: ");
            for(int j=0; j<vendedores.size(); j++){
                EntradaSalida.mostrarString(vendedores.get(j).getNombre());
            }
        }
        
        EntradaSalida.mostrarString("Trabaja para la empresa: " + empresa.getNombre());
        EntradaSalida.mostrarString("Empezo el: " + fechaEntrada);
        EntradaSalida.mostrarString("___________________________________________________________________");
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada() {
        this.fechaEntrada= LocalDate.now();
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    
    public Empresa getEmpresa(){
        return empresa;
    }
    
    public String getNombre(){
        return nombre;
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }
    
    
}
