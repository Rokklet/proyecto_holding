
package holding;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

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
                            }
                            sistema.getUsuarios().add(new Vendedor(usVen, conVen));
                        }  
                    }
                    break;
                case 2:
                    int codEmp = EntradaSalida.leerInt("Ingrese un codigo unico:\n");
                    if(codEmp == 0){
                        EntradaSalida.mostrarString("ERROR: Debe tener un codigo.\n");
                    }else{
                        if(sistema.coincideCodEmp(codEmp)){
                            EntradaSalida.mostrarString("Este codigo ya esta existe.\n");
                        }else {
                            String nomEmp = EntradaSalida.leerString("¿Cual es el nombre de la empresa?\n");
                            if(nomEmp.equals("")){
                                throw new NullPointerException("ERROR: Debe tener un nombre.\n");
                            }
                            sistema.getEmpresas().add(new Empresa(codEmp, nomEmp));
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
                            Empresa empresa = new Empresa(codigo,nombre);
                            sistema.getEmpresas().add(empresa);
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
