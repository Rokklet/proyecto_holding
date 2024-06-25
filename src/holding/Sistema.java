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

    public Sistema() {
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

    public boolean coincideUsuario(String usuario) {
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

    public int generarCodigoAsesor() {
        int cod = 0;
        ArrayList<Integer> cods = new ArrayList<Integer>();

        for (Usuario u : usuarios) {

            if (u instanceof Asesor) {
                cods.add(((Asesor) u).getCodigo());
            }

        }

        if (!cods.isEmpty()) {

            for (int i = 0; i < cods.size(); i++) {
                if (cod == 0) {
                    cod = cods.get(i);
                } else if (cod < cods.get(i)) {
                    cod = cods.get(i);
                }
            }

            return ++cod;
        } else {
            return ++cod;
        }
    }

    public int generarCodigoVendedor() {
        int cod = 0;
        ArrayList<Integer> cods = new ArrayList<>();

        for (Usuario u : usuarios) {

            if (u instanceof Vendedor) {
                cods.add(((Vendedor) u).getCod());
            }

        }

        if (!cods.isEmpty()) {

            for (int i = 0; i < cods.size(); i++) {
                if (cod == 0) {
                    cod = cods.get(i);
                } else if (cod < cods.get(i)) {
                    cod = cods.get(i);
                }
            }

            return ++cod;
        } else {
            return ++cod;
        }
    }

    public int generarCodigoEmpresa() {
        int cod = 0;
        ArrayList<Integer> cods = new ArrayList<Integer>();

        for (Empresa e : empresas) {
            cods.add(e.getCodigo());
        }

        if (!cods.isEmpty()) {

            for (int i = 0; i < cods.size(); i++) {
                if (cod == 0) {
                    cod = cods.get(i);
                } else if (cod < cods.get(i)) {
                    cod = cods.get(i);
                }
            }

            return ++cod;
        } else {
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

    public boolean coincideCodEmp(int cod) {

        for (Empresa e : empresas) {
            if (cod == e.getCodigo()) {
                return true;
            }
        }
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

    public void sobreescribirLider(Vendedor lider) {
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

    public void mostrarEmpresas() {
        for (Empresa e : empresas) {
            EntradaSalida.mostrarString("Codigo: " + e.getCodigo() + "\t|Nombre: " + e.getNombre());
        }
    }

    // Crea un pais y devuelve el mismo
    public Pais crearPais() {

        String nom = EntradaSalida.leerString("Ingrese el nombre del pais: ");
        while (nom.isEmpty() || coincideNombrePais(nom)) {
            if (nom.isEmpty()) {
                EntradaSalida.mostrarError("ERROR: El nombre del pais no puede ser nulo.");
                nom = EntradaSalida.leerString("Ingrese el nombre del pais: ");
            }
            if (coincideNombrePais(nom)) {
                EntradaSalida.mostrarError("ERROR: Ese pais ya existe.");
                nom = EntradaSalida.leerString("Ingrese el nombre del pais: ");
            }
        }

        String cap = EntradaSalida.leerString("Ingrese la capital del pais: ");
        while (cap.isEmpty()) {
            EntradaSalida.mostrarError("ERROR: El pais debe tener una capital.");
            cap = EntradaSalida.leerString("Ingrese la capital del pais: ");
        }

        int pbi = EntradaSalida.leerInt("Ingrese el pbi del pais: ");
        int hab = EntradaSalida.leerInt("Ingrese la cantidad de habitantes: ");

        Pais pais = new Pais(nom, cap, pbi, hab);
        paises.add(pais);
        return pais;

    }

    private boolean coincideNombrePais(String nom) {
        for (Pais p : paises) {
            if (nom.equals(p.getNombre())) {
                return true;
            }
        }
        return false;
    }

    //Crea un area y devuelve la misma
    public Area crearArea() {
        String nom = EntradaSalida.leerString("Ingrese el nombre del area: ");
        while (nom.isEmpty() || coincideNombreArea(nom)) {
            if (nom.isEmpty()) {
                EntradaSalida.mostrarError("ERROR: El nombre del area no puede ser nula.");
                nom = EntradaSalida.leerString("Ingrese el nombre del area: ");
            }
            if (coincideNombreArea(nom)) {
                EntradaSalida.mostrarError("ERROR: Esa area ya existe.");
                nom = EntradaSalida.leerString("Ingrese el nombre del area: ");
            }
        }

        String des = EntradaSalida.leerString("ingrese una descripcion del area: ");
        while (des.isEmpty()) {
            EntradaSalida.mostrarError("ERROR: La descricion no puede estar vacia.");
            des = EntradaSalida.leerString("ingrese una descripcion del area: ");
        }

        Area area = new Area(nom, des);
        areas.add(area);
        return area;
    }

    private boolean coincideNombreArea(String nom) {
        for (Area a : areas) {
            if (nom.equals(a.getNombre())) {
                return true;
            }
        }
        return false;
    }

    public boolean existenVendedores() {

        for (Usuario u : usuarios) {
            if (u instanceof Vendedor) {
                return true;
            }
        }
        
        return false;
    }
}
