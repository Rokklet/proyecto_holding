
package holding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Sistema implements Serializable {
    
    private ArrayList<Empresa> empresas;
    private ArrayList<Usuario> usuarios;
    
    public Sistema(){
        this.empresas = new ArrayList<Empresa>();
        this.usuarios = new ArrayList<Usuario>();
    }
    
    public Sistema deSerializar(String a) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(a);
        ObjectInputStream o = new ObjectInputStream(f);
        Sistema s = (Sistema) o.readObject();
        o.close();
        f.close();
        return s;
    }
    
    public void serializar(String a) throws IOException {
        FileOutputStream f = new FileOutputStream(a);
        System.out.println("LLegue");
        ObjectOutputStream o = new ObjectOutputStream(f);
        System.out.println("LLegue2");
        o.writeObject(this);
        System.out.println("LLegue3");
        o.close();
        f.close();
    }
    
    public Usuario buscarUsuario(String datos) {
        int i = 0;
        boolean encontrado = false;
        Usuario u = null;

        while (i < usuarios.size() && !encontrado) {
            u = usuarios.get(i);
            if (datos.equals(u.getId() + ":" + u.getContraseña())) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            return null;
        } else {
            return u;
        }
    }
    
    public ArrayList<Empresa> getEmpresa() {
        return empresas;
    }

    public void setCursos(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    public ArrayList<Usuario> getUsuario() {
        return usuarios;
    }

    public void setUsuario(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
