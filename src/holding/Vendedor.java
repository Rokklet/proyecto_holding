package holding;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;

public class Vendedor extends Usuario implements Serializable {
    
    private String nombre;
    private LocalDate fechaEntrada;
    private int cod;
    private Empresa empresas;
    private Vendedor lider;
    private ArrayList<Vendedor> vendedores;
    
    public Vendedor(int cd, String u, String p, Empresa em, String nom) {
        setCod(cd);
        setId(u);
        nombre = nom;
        setContraseña(p);
        setFechaEntrada();
        empresas = em;
    }
    
    //Contructor vendedor en base a su lider
    public Vendedor(int cd, String us, String pas, Vendedor lid, String nom){
        setCod(cd);
        setId(us);
        setContraseña(pas);
        setFechaEntrada();
        this.empresas = lid.getEmpresas();
        this.lider = lid;
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
        EntradaSalida.mostrarString("Su codigo es: " + getCod());
        EntradaSalida.mostrarString("Nombre: " + getNombre());
        if(lider!=null){
            EntradaSalida.mostrarString("Su team leader es el vendedor: " + lider.getNombre());
        }
        if(!vendedores.isEmpty()){
            EntradaSalida.mostrarString("Usted tiene a su cargo a: ");
            for(int j=0; j<vendedores.size(); j++){
                EntradaSalida.mostrarString(vendedores.get(j).getNombre());
            }
        }
        EntradaSalida.mostrarString("Trabaja para la empresa: " + empresas.getNombre());
        EntradaSalida.mostrarString("Empezo el: " + fechaEntrada);
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
    
    public Empresa getEmpresas(){
        return empresas;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    
}
