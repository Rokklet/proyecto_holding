package holding;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;

public class Vendedor extends Usuario implements Serializable {
    
    LocalDate fechaEntrada;
    int cod;
    
    public Vendedor(String u, String p) {
        setId(u);
        setContraseña(p);
        setFechaEntrada();
    }
    
    @Override
    public boolean proceder(Sistema sistema){
        EntradaSalida.mostrarString("Hola vendedor " + getId());
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
    
    
}
