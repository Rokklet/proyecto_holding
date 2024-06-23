
package holding;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Administrador extends Usuario implements Serializable {
    
    public Administrador(String u, String p) {
        setId(u);
        setContraseña(p);
    }
    
    @Override
    public boolean proceder(Sistema sistema){
        int p = 0;
        boolean seguir = true;
        EntradaSalida.mostrarString("___________________________________________________________________");
        System.out.println("Hola Administrador " + getId());
        EntradaSalida.mostrarString("___________________________________________________________________");
        do{
            p = EntradaSalida.leerInt("Elija alguna de las siguientes opciones:\n"
                    + "1_Para ingresar un nuevo vendedor.\n"
                    + "2_Para ingresar un nuevo ascesor.\n"
                    + "3_Para ingresar una nueva empresa.\n"
                    + "4_Para salir del menu.\n"
                    + "5_Para salir del programa.\n");
            
            switch (p){
                
                // DAR DE ALTA VENDEDOR
                
                case 1: 
                    ArrayList<Empresa> listaEmpresas = sistema.getEmpresas();
                    ArrayList<String> nomEmpresas = new ArrayList<String>();
                    
                        for(int i = 0; i<listaEmpresas.size();i++){
                            nomEmpresas.add(listaEmpresas.get(i).getNombre());
                        }
                        
                    if(listaEmpresas.isEmpty()){
                        EntradaSalida.mostrarString("___________________________________________________________________");
                        EntradaSalida.mostrarString("Primero deben haber empresas asociadas al holding");
                        EntradaSalida.mostrarString("___________________________________________________________________");
                    }else{
                        String usVen = EntradaSalida.leerString("¿Cual sera el usuario del vendedor?\n");
                        if(usVen.equals("")){
                            EntradaSalida.mostrarString("ERROR: Debe tener un usuario.\n");
                        }else{
                            if(sistema.coincideUsuario(usVen)){
                                EntradaSalida.leerString("Ese usuario ya esta existe.");
                            }else {
                                String conVen = EntradaSalida.leerString("Ingrese una contraseña:");
                                if(conVen.equals("")){
                                    throw new NullPointerException("ERROR: La password no puede ser nula.");
                                }else{
                                    String nomVen = EntradaSalida.leerString("Ingrese el nombre del vendedor: ");
                                    while(nomVen.equals("")){
                                        EntradaSalida.mostrarError("El nombre no puede estar vacio");
                                        nomVen = EntradaSalida.leerString("Ingrese el nombre del vendedor: ");
                                    }
                                    ArrayList<Usuario> listaUsuarios = sistema.getUsuarios();
                                    ArrayList<Integer> codVendedores = new ArrayList<Integer>();
                                    for(Usuario u: listaUsuarios){
                                            if(u instanceof Vendedor){
                                                Vendedor ven = (Vendedor)u;
                                                codVendedores.add(ven.getCod());
                                            }
                                        }
                                    
                                    //carga dirección
                                    
                                    String dir = EntradaSalida.leerString("Ingrese la direccion del vendedor: ");
                                    while(dir.isEmpty()){
                                        EntradaSalida.mostrarError("LA DIRECCION NO PUEDE ESTAR VACIA.");
                                        dir = EntradaSalida.leerString("Intente de nuevo: ");
                                    }
                                    
                                    if(EntradaSalida.leerBoolean("¿El vendedor fue captado por otro vendedor?"
                                            + "[1] SI"
                                            + "[0] NO")){
                                        EntradaSalida.mostrarString("Vendedores:");
                                        for(Usuario u: listaUsuarios){
                                            if(u instanceof Vendedor){
                                                Vendedor ven = (Vendedor)u;
                                                EntradaSalida.mostrarString("Codigo:" + ven.getCod() + "|  Nombre: " +ven.getNombre());
                                            }
                                        }
                                        int codLider = EntradaSalida.leerInt("Ingrese el codigo del vendedor por el que fue captado: ");
                                        while(!codVendedores.contains(codLider)){
                                            EntradaSalida.mostrarString("El codigo ingresado no pertenece a ningun Vendedor. Intente de nuevo");
                                            codLider = EntradaSalida.leerInt("Ingrese el codigo del vendedor: ");
                                        }
                                        Vendedor lider = null;
                                        for(Usuario u: listaUsuarios){
                                            if(u instanceof Vendedor){
                                                Vendedor ven = (Vendedor)u;
                                                if(codLider == ven.getCod()){
                                                    lider = ven;
                                                }
                                            }
                                        }
                                        Vendedor vendedor = new Vendedor(sistema.generarCodigoVendedor(), usVen, conVen, lider, nomVen, dir);
                                        sistema.getUsuarios().add(vendedor);
                                        lider.getVendedores().add(vendedor);
                                        sistema.sobreescribirLider(lider);
                                    }else{
                                       EntradaSalida.mostrarString("¿Para qué empresa trabajará el vendedor?");
                                        for(int k = 0; k < listaEmpresas.size(); k++ ){
                                            EntradaSalida.mostrarString(listaEmpresas.get(k).getNombre());
                                        }
                                        String nomEmpresa = EntradaSalida.leerString("Ingrese el nombre de la empresa: ");
                                        do{
                                            if(!nomEmpresas.contains(nomEmpresa)){
                                               EntradaSalida.mostrarString("No hay una empresa con ese nombre. Intente de nuevo");
                                               nomEmpresa = EntradaSalida.leerString("Ingrese el nombre de la empresa: "); 
                                            }
                                        }while(!nomEmpresas.contains(nomEmpresa)); 
                                        Empresa empresa = null;
                                        for(int k=0; k< listaEmpresas.size() ; k++){
                                            if(nomEmpresa.equals(listaEmpresas.get(k).getNombre())){
                                                empresa = listaEmpresas.get(k);
                                            }
                                        }
                                        sistema.getUsuarios().add(new Vendedor(sistema.generarCodigoVendedor(), usVen, conVen, empresa, nomVen, dir));
                                    }
                                }
                            }  
                        }
                    }
                    break;
                    
                case 2: //DAR DE ALTA ASESOR
                    ArrayList<Empresa> listadoEmpresas = sistema.getEmpresas();
                    if(listadoEmpresas.isEmpty()){
                        EntradaSalida.mostrarString("___________________________________________________________________");
                        EntradaSalida.mostrarString("Primero deben haber empresas asociadas al holding");
                        EntradaSalida.mostrarString("___________________________________________________________________");
                    }else{
                        
                        //Carga nombre de Usuario
                        
                        String usAses= EntradaSalida.leerString("ALTA ASESOR \n Usuario del asesor:");
                        do{
                            if(usAses.equals("")){
                                EntradaSalida.mostrarError("ERROR: El nomrbe de usuario no puede estar vacio.");
                                usAses = EntradaSalida.leerString("Ingrese el usuario del Asesor");
                            }
                            if(sistema.coincideUsuario(usAses)){
                                EntradaSalida.mostrarError("ERROR: Usuario ya existente.");
                                usAses = EntradaSalida.leerString("Ingrese el usuario del Asesor: ");
                            }
                        }while((usAses.equals("")) || sistema.coincideUsuario(usAses));
                        
                        //Carga Contraseña
                        
                        String pasAses = EntradaSalida.leerString("Ingrese una contraseña: ");
                        do{
                            if(pasAses.equals("")){
                                EntradaSalida.mostrarError("ERROR: La password no puede ser nula.");
                                pasAses = EntradaSalida.leerString("Ingrese una contraseña: ");
                            }
                        }while(pasAses.equals(""));

                        Asesor asesor = new Asesor(usAses, pasAses, sistema.generarCodigoAsesor());
                        
                        //Carga Dirección
                        
                        asesor.setDireccion(EntradaSalida.leerString("Cual es la direccion del asesor:"));
                        if(asesor.getDireccion().equals("")){
                            EntradaSalida.mostrarString("La direccion no puede ser nula");
                            asesor.setDireccion(EntradaSalida.leerString("Cual es la direccion del asesor:"));
                        }
                        
                        //Carga Nombre de Asesor
                        
                        asesor.setNombre(EntradaSalida.leerString("Ingrese el nombre del asesor:"));
                        if(asesor.getNombre().equals("")){
                            EntradaSalida.mostrarString("El nombre no puede ser nulo");
                            asesor.setNombre(EntradaSalida.leerString("Cual es el nombre del asesor?:"));
                        }
                        
                        //Carga Empresa y Areas de la misma
                        
                        boolean salida = true;
                        do{
                            
                            EntradaSalida.mostrarString("¿En qué empresa asesorara?");
                            
                            sistema.mostrarEmpresas();
                            
                            int cod = EntradaSalida.leerInt("Ingrese el codigo de la empresa: ");
                            while(!sistema.coincideCodEmp(cod)){
                                EntradaSalida.mostrarError("ERROR: No se ha encontrado una empresa con Codigo: " + cod + ".");
                                cod = EntradaSalida.leerInt("Ingrese el codigo de la empresa: ");
                            }
                            
                            for(Empresa e : listadoEmpresas){
                                if(cod == e.getCodigo()){
                                    if(asesor.coincideEmpresa(e)){
                                        EntradaSalida.mostrarError("ERROR: Esa empresa ya fue cargada.");
                                    }else{
                                        asesor.getEmpresas().add(e);
                                        
                                        for(int i = 0; i < e.getAreas().size() ; i++){
                                            if(!asesor.getAreas().contains(e.getAreas().get(i))){
                                                asesor.getAreas().add(e.getAreas().get(i));
                                                asesor.getFechaEntrada().add(LocalDate.now());
                                            }
                                        }
                                        
                                    }
                                }
                            } 
                           
                            if(!EntradaSalida.leerString("Para dejar de cargar empresas ingrese [S] sino dijite otro caracter.").equals("S")){
                                } else {
                                    salida = false;
                                }
                        }while(salida);
                        
                        sistema.getUsuarios().add(asesor);

                    }
                    break;
                    
                 //DAR DE ALTA UNA EMPRESA
                    
                case 3:
                    String nombre = EntradaSalida.leerString("ALTA EMPRESAS \n Nombre de la Empresa:");
                    if(nombre.equals("")){
                        EntradaSalida.mostrarString("Nombre no valido");
                    }else{

                        ArrayList<Pais> listPaises = sistema.getPaises();
                        Pais paisCede;
                        if(listPaises.isEmpty()){
                            
                            EntradaSalida.mostrarString("___________________________________________________________________");
                            EntradaSalida.mostrarString("Aun no hay paises disponibles.\n"
                                    + "Cargue el pais cede:\n");
                            EntradaSalida.mostrarString("___________________________________________________________________");
                            
                            paisCede = sistema.crearPais();
                            
                        }else{
                            
                            for(Pais pa : listPaises ){
                                pa.mostrar();
                            }
                            
                            boolean flag;
                            do{
                                flag = true;
                                String nom = EntradaSalida.leerString("Ingrese el nombre del pais cede o si no esta ingrese [0]:\n");
                                if(nom.equals("0")){
                                    EntradaSalida.mostrarString("___________________________________________________________________");
                                    EntradaSalida.mostrarString("Hay que crear al pais cede:\n");
                                    EntradaSalida.mostrarString("___________________________________________________________________");
                                    
                                    paisCede = sistema.crearPais();
                                    
                                }else{
                                    paisCede = sistema.buscarPais(nom);
                                    if(paisCede == null){
                                        flag = false;
                                        EntradaSalida.mostrarString("Ese nombre no esta en la lista.\n");
                                    }
                                }
                            }while(!flag);
                        }
                        Empresa empresa = new Empresa(sistema.generarCodigoEmpresa(), nombre, paisCede);
                        boolean salida = true;
                        do{
                            Pais pais;
                            
                            for(Pais pa : listPaises ){
                                pa.mostrar();
                            }
                            
                            boolean flag;
                            do{
                                flag = true;
                                String nom = EntradaSalida.leerString("Ingrese el nombre del pais donde desarrolla actividades o si no esta ingrese [1]:\n");
                                if(nom.equals("1")){
                                    EntradaSalida.mostrarString("___________________________________________________________________");
                                    EntradaSalida.mostrarString("Hay que crear al pais:\n");
                                    EntradaSalida.mostrarString("___________________________________________________________________");
                                    
                                    pais = sistema.crearPais();
                                    
                                }else{
                                    pais = sistema.buscarPais(nom);
                                    if(pais == null){
                                    flag = false;
                                        EntradaSalida.mostrarString("Ese nombre no esta en la lista.\n");
                                    }
                                }
                                
                            }while(!flag);
                            
                            empresa.getPaises().add(pais);
                            
                            if(EntradaSalida.leerString("Para dejar de cargar paises ingrese [s] sino dijite otro caracter.").equals("s")){
                                salida = false;
                            }
                            
                        }while(salida);

                        salida = true;
                        do{
                            Area area;
                            ArrayList<Area> listAreas = sistema.getAreas();
                            if(!listAreas.isEmpty()){
                                
                                for (Area a : listAreas){
                                    if(!empresa.coincideArea(a)){
                                        a.mostrar();
                                    }
                                    
                                }
                                
                                boolean flag;
                                do{
                                    flag = true;
                                    String nom = EntradaSalida.leerString("Ingrese el nombre del area o si no esta ingrese [1]:\n");
                                    if(nom.equals("1")){
                                        
                                        EntradaSalida.mostrarString("___________________________________________________________________");
                                        EntradaSalida.mostrarString("Hay que crear al area:\n");
                                        EntradaSalida.mostrarString("___________________________________________________________________");
                                        area = sistema.crearArea();
                                        
                                    }else{
                                        area = sistema.buscarAreas(nom);
                                        if(area == null){
                                        flag = false;
                                            EntradaSalida.mostrarString("Ese nombre no esta en la lista.\n");
                                        }else if(empresa.coincideArea(area)){
                                                flag = false;
                                                EntradaSalida.mostrarString("Ese area ya fue cargado.\n");
                                            }
                                    }
                                }while(!flag);
                                empresa.getAreas().add(area);
                            }else{
                                EntradaSalida.mostrarString("___________________________________________________________________");
                                EntradaSalida.mostrarString("No hay areas en el sistema.");
                                EntradaSalida.mostrarString("Hay que crear al area:\n");
                                EntradaSalida.mostrarString("___________________________________________________________________");
                                area = sistema.crearArea();
                                empresa.getAreas().add(area);
                            }
                            if(!EntradaSalida.leerString("Para dejar de cargar areas ingrese [S] sino dijite otro caracter.").equals("S")){
                                } else {
                                    salida = false;
                                }
                        }while(salida);

                        empresa.setFacturacion(EntradaSalida.leerInt("Ingrese la facturacion de la empresa: "));

                        sistema.getEmpresas().add(empresa);
                        EntradaSalida.mostrarString("Se dado de alta una nueva Empresa:");
                        empresa.mostrar();
                        
                    }
                    break;
                case 4:
                    EntradaSalida.mostrarString("Hasta Luego " + getId());
                    seguir = true;
                    break;
                case 5:
                    EntradaSalida.mostrarString("Hasta Luego " + getId());
                    seguir = false;
                    break;
                default :
                    EntradaSalida.mostrarString("Opcion inexistente.");
            }
            
            if (p >= 1 && p <= 3) {
                try {
                    EntradaSalida.mostrarString("Serealizando...");
                    sistema.serializar("holding.bin");
                } catch (IOException e) {
                    e.printStackTrace();
                    
                }
            }
        }while(p != 4 && p != 5);
        
        return seguir;
    }
        
    
    @Override
    public void mostrar(){
        System.out.println("Coordinador - Usuario: " + this.getId());
        System.out.println("Password: " + this.getContraseña());
        
    }
}
