
package holding;

import java.io.IOException;
import java.io.Serializable;
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
        int i = 0;
        boolean seguir = true;
        System.out.println("Hola Administrador " + getId());
        
        do{
            i = EntradaSalida.leerInt("Elija alguna de las siguientes opciones:\n"
                    + "1_Para ingresar un nuevo vendedor.\n"
                    + "2_Para ingresar un nuevo ascesor.\n"
                    + "3_Para ingresar una nueva empresa.\n"
                    + "4_Para salir del menu.\n"
                    + "5_Para salir del programa.\n");
            switch (i){
                case 1: // DAR DE ALTA VENDEDOR
                    ArrayList<Empresa> listaEmpresas = sistema.getEmpresas();
                    ArrayList<String> nomEmpresas = new ArrayList<String>();
                    ArrayList<Usuario> listaUsuarios = sistema.getUsuarios();
                    ArrayList<Integer> codVendedores = new ArrayList<Integer>();
                    
                        for(i = 0; i<listaEmpresas.size();i++){
                            nomEmpresas.add(listaEmpresas.get(i).getNombre());
                        }
                    if(listaEmpresas.isEmpty()){
                        EntradaSalida.mostrarString("Primero deben haber empresas asociadas al holding");
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
                                    
                                    int codVen = EntradaSalida.leerInt("Ingrese el cod del vendedor: ");
                                    for(Usuario u: listaUsuarios){
                                            if(u instanceof Vendedor){
                                                Vendedor ven = (Vendedor)u;
                                                codVendedores.add(ven.getCod());
                                            }
                                        }
                                    while(codVendedores.contains(codVen)){
                                        EntradaSalida.mostrarError("ERROR: Ese codigo ya esta en uso");
                                        codVen = EntradaSalida.leerInt("Ingrese un nuevo cod de vendedor: ");
                                    }

                                    if(EntradaSalida.leerBoolean("¿El vendedor fue captado por otro vendedor?")){
                                        EntradaSalida.mostrarString("Vendedores");
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
                                        Vendedor Lider = null;
                                        for(Usuario u: listaUsuarios){
                                            if(u instanceof Vendedor){
                                                Vendedor ven = (Vendedor)u;
                                                if(codLider == ven.getCod()){
                                                    Lider = ven;
                                                }
                                            }
                                        }
                                        
                                        sistema.getUsuarios().add(new Vendedor(codVen, usVen, conVen, Lider, nomVen));
                                        
                                        //Agregar el nuevo vendedor a la lista del lider
                                        /*
                                        listaUsuarios = sistema.getUsuarios();
                                       
                                        for(Usuario u: listaUsuarios){
                                            if(u instanceof Vendedor){
                                                Vendedor ven = (Vendedor)u;
                                                if(codVen == ven.getCod()){
                                                   for (int j = 0 ; j < sistema.getUsuarios().size() ; j++){  
                                                        if(sistema.getUsuarios().get(j) == Lider){
                                                            
                                                        }
                                                   } 
                                                    
                                                }
                                            }
                                        }
                                         */  
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
                                        sistema.getUsuarios().add(new Vendedor(codVen, usVen, conVen, empresa, nomVen));
                                    }
                                }
                            }  
                        }
                    }
                    break;
                case 2: //DAR DE ALTA ASESOR
                    ArrayList<Empresa> listadoEmpresas = sistema.getEmpresas();
                    if(listadoEmpresas.isEmpty()){
                        EntradaSalida.mostrarString("Primero deben haber empresas asociadas al holding");
                    }else{
                        String usAses= EntradaSalida.leerString("ALTA ASESOR \n Usuario del asesor:");
                        if(usAses.equals("")){
                            EntradaSalida.mostrarString("Usuario no valido");
                        }else{
                            if(sistema.coincideUsuario(usAses)){
                                EntradaSalida.mostrarString("Nombre de usuario ya existente");
                            }else{
                                String pasAses = EntradaSalida.leerString("Ingrese una contraseña: ");
                                if(pasAses.equals("")){
                                    EntradaSalida.mostrarString("ERROR: La password no puede ser nula.");
                                }else{
                                    sistema.getUsuarios().add(new Asesor(usAses, pasAses));
                                }
                            }
                        }
                    }
                    break;
                case 3: //DAR DE ALTA UNA EMPRESA
                    String nombre = EntradaSalida.leerString("ALTA EMPRESAS \n Nombre de la Empresa:");
                    if(nombre.equals("")){
                        System.out.println("Nombre no valido");
                    }else{
                        ArrayList<Empresa> empresas = sistema.getEmpresas();
                        ArrayList<Integer> codigos = new ArrayList<Integer>();
                        for(i = 0; i<empresas.size();i++){
                            codigos.add(empresas.get(i).getCodigo());
                        }
                        int codigo = EntradaSalida.leerInt("Ingrese el codigo de la Empresa: ");
                        
                        if(codigos.contains(codigo)){
                            EntradaSalida.mostrarString("El codigo ya existe");
                        }else{
                            ArrayList<Pais> listPaises = sistema.getPaises();
                            Pais paisCede;
                            if(listPaises.isEmpty()){
                                EntradaSalida.mostrarString("Aun no hay paises disponibles.\n"
                                        + "Cargue el pais cede:\n");
                                String nomPaisSC = EntradaSalida.leerString("Ingrese el nombre del pais cede:\n");
                                String capPaisSC = EntradaSalida.leerString("Ingrese la capital del pais:\n");
                                int pbiPaisSC = EntradaSalida.leerInt("Ingrese el pbi del pais:\n");
                                int cantHabitantesSC = EntradaSalida.leerInt("Ingrese la cantidad de habitantes del pais:\n");
                                paisCede = new Pais(nomPaisSC, capPaisSC, pbiPaisSC, cantHabitantesSC);
                                sistema.getPaises().add(paisCede);
                            }else{
                                Pais pais;
                                for(i = 0; i < listPaises.size(); i++){
                                    pais = listPaises.get(i);
                                    pais.mostrar();
                                }
                                boolean flag;
                                do{
                                    flag = true;
                                    String nomPaisSC = EntradaSalida.leerString("Ingrese el nombre del pais cede o si no esta ingrese [0]:\n");
                                    if(nomPaisSC.equals("0")){
                                        EntradaSalida.mostrarString("Hay que crear al pais cede:\n");
                                        nomPaisSC = EntradaSalida.leerString("Ingrese el nombre del pais cede:\n");
                                        String capPaisSC = EntradaSalida.leerString("Ingrese la capital del pais:\n");
                                        int pbiPaisSC = EntradaSalida.leerInt("Ingrese el pbi del pais:\n");
                                        int cantHabitantesSC = EntradaSalida.leerInt("Ingrese la cantidad de habitantes del pais:\n");
                                        paisCede = new Pais(nomPaisSC, capPaisSC, pbiPaisSC, cantHabitantesSC);
                                        sistema.getPaises().add(paisCede);
                                    }else{
                                        paisCede = sistema.buscarPais(nomPaisSC);
                                        if(paisCede == null){
                                            flag = false;
                                            EntradaSalida.mostrarString("Ese nombre no esta en la lista.\n");
                                        }
                                    }
                                }while(!flag);
                            }
                            Empresa empresa = new Empresa(codigo, nombre, paisCede);
                            boolean salida = true;
                            do{
                                Pais pais;
                                for(i = 0; i < listPaises.size(); i++){
                                    pais = listPaises.get(i);
                                    if(empresa.coincidePais(pais)){
                                        pais.mostrar();
                                    }
                                }
                                boolean flag;
                                do{
                                    flag = true;
                                    String nomPais = EntradaSalida.leerString("Ingrese el nombre del pais donde desarrolla actividades o si no esta ingrese [1]:\n");
                                    if(nomPais.equals("1")){
                                        EntradaSalida.mostrarString("Hay que crear al pais:\n");
                                        nomPais = EntradaSalida.leerString("Ingrese el nombre del pais:\n");
                                        String capPais = EntradaSalida.leerString("Ingrese la capital del:\n");
                                        int pbiPais = EntradaSalida.leerInt("Ingrese el pbi del:\n");
                                        int cantHabitantes = EntradaSalida.leerInt("Ingrese la cantidad de habitantes del pais:\n");
                                        pais = new Pais(nomPais, capPais, pbiPais, cantHabitantes);
                                        sistema.getPaises().add(pais);
                                    }else{
                                        pais = sistema.buscarPais(nomPais);
                                        if(pais == null){
                                        flag = false;
                                            EntradaSalida.mostrarString("Ese nombre no esta en la lista.\n");
                                        }
                                    }
                                }while(!flag);
                                empresa.getPaises().add(pais);
                                if(EntradaSalida.leerString("Para dejar de cargar paises ingrese [S] sino dijite otro caracter.").equals("S")){
                                    salida = false;
                                }
                            }while(salida);
                            
                            salida = true;
                            do{
                                Area area;
                                ArrayList<Area> listAreas = sistema.getAreas();
                                if(!listAreas.isEmpty()){
                                    for(i = 0; i < listAreas.size(); i++){
                                        area = listAreas.get(i);
                                        if(empresa.coincideArea(area)){
                                            area.mostrar();
                                        }
                                    }
                                    boolean flag;
                                    do{
                                        flag = true;
                                        String nomArea = EntradaSalida.leerString("Ingrese el nombre del area o si no esta ingrese [1]:\n");
                                        if(nomArea.equals("1")){
                                            EntradaSalida.mostrarString("Hay que crear al area:\n");
                                            nomArea = EntradaSalida.leerString("Ingrese el nombre del area:\n");
                                            area = new Area(nomArea);
                                            sistema.getAreas().add(area);
                                        }else{
                                            area = sistema.buscarAreas(nomArea);
                                            if(area == null){
                                            flag = false;
                                                EntradaSalida.mostrarString("Ese nombre no esta en la lista.\n");
                                            }
                                        }
                                    }while(!flag);
                                    empresa.getAreas().add(area);
                                }else{
                                    EntradaSalida.mostrarString("No hay areas en el sistema.");
                                    EntradaSalida.mostrarString("Hay que crear al area:\n");
                                    String nomArea = EntradaSalida.leerString("Ingrese el nombre del area:\n");
                                    area = new Area(nomArea);
                                    sistema.getAreas().add(area);
                                    empresa.getAreas().add(area);
                                }
                                if(!EntradaSalida.leerString("Para dejar de cargar areas ingrese [S] sino dijite otro caracter.").equals("S")){
                                    } else {
                                        salida = false;
                                    }
                            }while(salida);
                            
                            empresa.setFacturacion(EntradaSalida.leerInt("Ingrese la factiracion de la empresa"));
                            
                            sistema.getEmpresas().add(empresa);
                            EntradaSalida.mostrarString("Se dado de alta una nueva Empresa:");
                            empresa.mostrar();
                            /*try {
                                sistema.serializar("holding.bin");
                                EntradaSalida.mostrarString("Se dado de alta una nueva Empresa:");
                                empresa.mostrar();
                            } catch (Exception e) {
                                System.err.println("NO SE PUDO CARGAR LA EMPRESA.");
                            }*/
                            
                        }
                        
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
            
            if (i >= 1 && i <= 3) {
                try {
                    sistema.serializar("holding.bin");
                } catch (IOException e) {
                    e.printStackTrace();
                    
                }
            }
        }while(i != 4 && i != 5);
        
        return seguir;
    }
        
    
    @Override
    public void mostrar(){
        System.out.println("Coordinador - Usuario: " + this.getId());
        System.out.println("Password: " + this.getContraseña());
        
    }
}
