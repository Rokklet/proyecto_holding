
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
            i=EntradaSalida.leerInt("Elija alguna de las siguientes opciones:\n"
                    + "1_Para ingresar un nuevo vendedor.\n"
                    + "2_Para ingresar un nuevo ascesor.\n"
                    + "3_Para ingresar una nueva empresa.\n"
                    + "4_Para salir del menu.\n"
                    + "5_Para salir del programa.\n");
            switch (i){
                case 1:
                    ArrayList<Empresa> listaEmpresas = sistema.getEmpresas();
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
                                    sistema.getUsuarios().add(new Vendedor(usVen, conVen));
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
                                    pais.mostrarPais();
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
                                    pais.mostrarPais();
                                }
                                boolean flag;
                                do{
                                    flag = true;
                                    String nomPais = EntradaSalida.leerString("Ingrese el nombre del pais o si no esta ingrese [0]:\n");
                                    if(nomPais.equals("0")){
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
                                if(EntradaSalida.leerInt("Para dejar de cargar paises ingrese [0]") == 0){
                                    salida = false;
                                }
                            }while(salida);
                            
                            
                            
                            empresa.mostrar();
                            
                            
                            sistema.getEmpresas().add(empresa);
                            try {
                                sistema.serializar("holding.txt");
                            } catch (IOException ex) {
                                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            EntradaSalida.mostrarString("Se dado de alta una nueva Empresa");
                        }
                        
                    }
            }
        }while(i!=4 || i!=5);
        
        
        return seguir;
    }
        
    
    @Override
    public void mostrar(){
        System.out.println("Coordinador - Usuario: " + this.getId());
        System.out.println("Password: " + this.getContraseña());
        
    }
}
