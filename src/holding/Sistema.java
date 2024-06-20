
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
    private ArrayList<Pais> paises;
    private ArrayList<Area> areas;
    
    
    public Sistema(){
        this.empresas = new ArrayList<Empresa>();
        this.usuarios = new ArrayList<Usuario>();
        this.paises = new ArrayList<Pais>();
        this.areas = new ArrayList<Area>();
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
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }
    
    public boolean coincideUsuario(String usuario){
        int i = 0;
        boolean coincide = false;
        Usuario u = null;
        
        while (i < usuarios.size() || coincide) {
            u = usuarios.get(i);
            if (usuario.equals(u.getId())) {
                coincide = true;
            } else {
                i++;
            }
        }
        
        return coincide;
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
    
    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public int generarCodigoAsesor () {
        int cod = 0;
        ArrayList<Integer> cods = new ArrayList<Integer>(); 
        
        for (Usuario u: usuarios){

            if(u instanceof Asesor){
                cods.add(((Asesor) u).getCodigo() );
            }

        }
        
        if(!cods.isEmpty()){
            
            for(int i = 0; i<cods.size(); i++){
                if(cod == 0){
                    cod = cods.get(i);
                }else if(cod < cods.get(i)){
                    cod = cods.get(i);
                }
            }
            
            return ++cod;
        }else{
            return ++cod;
        }
    }
    
    public void setEmpresas(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuario(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public boolean coincideCodEmp (int cod){
        Empresa empresa;
        for(int i = 0; i < empresas.size() ;i++){
            empresa = empresas.get(i);
            if(empresa.getCodigo()== cod){
                System.out.println("Se ha encontrado una empresa.");
                return true;
            }
        }
        
        System.out.println("No se ha encontrado una empresa con ese codigo.");
        return false;
    }

    public ArrayList<Pais> getPaises() {
        return paises;
    }

    public void setPaises(ArrayList<Pais> paises) {
        this.paises = paises;
    }
    
    public Pais buscarPais(String datos) {
        int i = 0;
        boolean encontrado = false;
        Pais p = null;

        while (i < paises.size() && !encontrado) {
            p = paises.get(i);
            if (datos.equals(p.getNombre())) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            return null;
        } else {
            return p;
        }
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public Area buscarAreas(String datos) {
        int i = 0;
        boolean encontrado = false;
        Area a = null;

        while (i < areas.size() && !encontrado) {
            a = areas.get(i);
            if (datos.equals(a.getNombre())) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            return null;
        } else {
            return a;
        }
    }
    
    public void sobreescribirLider(Vendedor lider){
        int i = 0;
        boolean encontrado = false;
        Usuario u = null;

        while (i < usuarios.size() && !encontrado) {
            u = usuarios.get(i);
            if (lider.getId().equals(u.getId())) {
                encontrado = true;
                usuarios.set(i, lider);
            } else {
                i++;
            }
        }
    }
}
