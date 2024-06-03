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
    private boolean lider;
    private ArrayList<Vendedor> vendedores;
    
    public Vendedor(String u, String p) {
        setId(u);
        setContraseña(p);
        setFechaEntrada();
    }
    
    @Override
    public boolean proceder(Sistema sistema){
        EntradaSalida.mostrarString("Hola vendedor " + getId());
        int i=EntradaSalida.leerInt("Presione '1' para ver sus datos o cualquier otra tecla para salir :");
        switch(i){
            case 1:
                EntradaSalida.mostrarString("Su codigo es: " + cod);
                EntradaSalida.mostrarString("Nombre: " + nombre);
                if(lider){
                    EntradaSalida.mostrarString("Usted tiene a su cargo a: ");
                    for(int j=0; i<vendedores.size(); i++){
                        EntradaSalida.mostrarString(vendedores.get(i).nombre);
                    }
                }
                EntradaSalida.mostrarString("Trabaja para la empresa: " + empresas.getNombre());
                EntradaSalida.mostrarString("Empezo el: " + fechaEntrada);
        }
        return true;
    }
    
    @Override
    public void mostrar(){
        
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
    
    
}
