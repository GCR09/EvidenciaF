package com.mycompany.evidenciaf;
/**
 *
 * @author Cortez Ramos
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    
    public static List<Usuario> usuarios;
    public static Medico medicos = new Medico();
    public static Paciente pacientes = new Paciente();
    public static Cita citas = new Cita();
    public static BufferedReader entrada =new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException{
        boolean existeUsuario;
        String usuario = "";
        String contrasena = "";
        System.out.println("Cargando sistema... ");
        cargarUsuarios();
        medicos.cargarMedico();
        pacientes.cargarPacientes();
        citas.cargarCitas(medicos,pacientes);
        System.out.println("Inicio de sesion:");
        System.out.println("Usuario:");
        usuario = entrada.readLine();
        System.out.println("Contraseña");
        contrasena = entrada.readLine();
        existeUsuario = validarCredenciales(usuario, contrasena);
        if (existeUsuario) {
            System.out.println("existe el usuario");
            menu(usuario);
            
        } else {
            System.out.println("el usuario no existe o la contraseña no es correcta");
        }

    }
    
    public static void cargarUsuarios() {

        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }

        usuarios.add(new Usuario(1, "Ricardo", "1234"));
        usuarios.add(new Usuario(2, "Esmeralda", "1234")
        );
        System.out.println("Los usuarios han sido cargados: " + usuarios.size());

    }

    public static boolean validarCredenciales(String usuario, String contrasena) {
        return usuarios.stream().anyMatch(x -> x.getNombre().equalsIgnoreCase(usuario) && x.getContrasena().equals(contrasena));
    }
    
    public static void menu(String usuario) throws IOException{
        int opcion;
        String repetir;
        int id;
        String nombre;
        System.out.println("¿Qué deseas realizar?:\n"
                + "1.-Dar de alta a medico\n"
                + "2.-Dar de alta a un paciente\n"
                + "3.-Crear una cita\n"
                + "4.-Entrar como administrdor\n"
                + "5.-Vertodas las listas");
        opcion=Integer.parseInt(entrada.readLine());
        switch(opcion){
            case 1:
                System.out.println("Seleccionaste \"Dar de alta a medico\"");
                System.out.println("------------------------------------");
                id = medicos.cantidadMedicos() + 1;
                System.out.println("--Ingresa el nombre completo--");
                nombre = entrada.readLine();
                System.out.println("--Ingresa la especialidad del medico--");
                String especialidad = entrada.readLine();
                Medico nuevoMedico = new Medico(id,nombre,especialidad);
                medicos.añadirLista(nuevoMedico);
                medicos.listM();
                break;
            case 2:
                System.out.println("Seleccionaste \"Dar de alta a un paciente\""); 
                System.out.println("------------------------------------");
                id = pacientes.cantidadPacientes() + 1;
                System.out.println("--Ingresa el nombre completo--");
                nombre = entrada.readLine();
                Paciente nuevoPaciente = new Paciente(id,nombre);
                pacientes.añadirLista(nuevoPaciente);
                pacientes.listP();
                break;
            case 3:
                System.out.println("Seleccionaste \"Crear una cita\""); 
                System.out.println("------------------------------------");
                id = citas.cantidadCitas() + 1;
                System.out.println("--Ingresa el motivo de la cita--");
                String motivo = entrada.readLine();
                System.out.println("--Ingresa el día de la cita (Formato: dd/mm/aa)--");
                String día = entrada.readLine();
                System.out.println("--Ingresa la hora de la cita (Formato: hh:mm)--");
                String hora = entrada.readLine();
                String fecha = día +" "+ hora;
                System.out.println("--Ingresa el id del doctor para establecer la cita--");
                System.out.println("---------------------------------------:");
                System.out.println("Lista medicos:");
                medicos.listM();
                System.out.println("---------------------------------------:");
                int idMedico = Integer.parseInt(entrada.readLine());
                Medico medi = new Medico();
                medi = medicos.listaMed.get(idMedico-1);
                System.out.println("--Ingresa el id del paciente para establecer la cita--");
                System.out.println("---------------------------------------:");
                System.out.println("Lista pacientes:");
                pacientes.listP();
                System.out.println("---------------------------------------:");
                int idPaciente = Integer.parseInt(entrada.readLine());
                Paciente pac = new Paciente();
                pac = pacientes.listaPac.get(idPaciente-1);
                Cita nuevaCita = new Cita(id,motivo,fecha,medi,pac);
                citas.añadirLista(nuevaCita);
                citas.listC();
                break;
            case 4:
                System.out.println("Seleccionaste \"Entrar como administrdor\""); 
                System.out.println("------------------------------------");
                if(usuario.equalsIgnoreCase("Ricardo")){
                    System.out.println("Acceso permitido;");
                    menu2(); 
                }else{
                    System.out.println("Acceso denegado:");
                }
                break;
            case 5:
                System.out.println("Lista medicos");
                System.out.println("------------------------------------");
                medicos.listM();
                System.out.println("------------------------------------");
                System.out.println("Lista pacientes");
                System.out.println("------------------------------------");
                pacientes.listP();
                System.out.println("------------------------------------");
                System.out.println("Lista citas");
                System.out.println("------------------------------------");
                citas.listC();
                System.out.println("------------------------------------");
                break;
            default:
                System.out.println("La opción ingresada es incorrecta, "
                        + "ingresa alguna opción valida");
                System.out.println("¿Deseas volver a intentar? (S o si para \"Sí\" o cualquier otra cosa para \"No\"");
                repetir = entrada.readLine();
                if(repetir.equalsIgnoreCase("S") || repetir.equalsIgnoreCase("Si")){
                   menu(usuario); 
                }else{
                    System.out.println("Gracias por usar el programa");
                }
                break;
        }
        System.out.println("¿Deseas realizar algo más? (S o si para \"Sí\" o cualquier otra cosa para \"No\")");
        repetir = entrada.readLine();
        if(repetir.equalsIgnoreCase("S") || repetir.equalsIgnoreCase("Si")){
           menu(usuario); 
        }else{
            System.out.println("Gracias por usar el programa");
        }
    }
    
    public static void menu2() throws IOException{
        int opcion;
        int id;
        String repetir;
        int eleccion;
        String cambiar;
        System.out.println("¿Qué deseas realizar?:\n"
                + "1.-Dar de baja o actualizar medico\n"
                + "2.-Dar de baja o actualizar paciente\n"
                + "3.Borrar o actualizar cita\n"
                + "4.Borra sistema por completo");
        opcion=Integer.parseInt(entrada.readLine());
        switch(opcion){
            case 1:
                System.out.println("Seleccionaste \"Dar de baja o actualizar medico\"");
                System.out.println("------------------------------------");
                System.out.println("¿Qué deseas realizar?:\n"
                        + "1.-Dar de baja\n"
                        + "2.-Actualizar");
                eleccion = Integer.parseInt(entrada.readLine());
                System.out.println("Ingresa el id del medico con el que deseas interactuar;");
                System.out.println("Lista medicos");
                System.out.println("------------------------------------");
                medicos.listM();
                System.out.println("------------------------------------");
                id = Integer.parseInt(entrada.readLine());
                switch (eleccion) {
                    case 1:                     
                        for (int i=0;i<medicos.listaMed.size();i++){
                            if(medicos.listaMed.get(i).getIdMedico()==id){
                                for(int j=i;j<medicos.listaMed.size();j++){
                                    if(j<medicos.listaMed.size()-1){
                                       medicos.listaMed.get(j+1).setIdMedico(j+1); 
                                    }
                                }
                                medicos.listaMed.remove(i);
                            }
                        } 
                        medicos.listM();
                        break;
                    case 2:
                        System.out.println("¿Qué deseas actulizar de un?:\n"
                        + "1.-Nombre\n"
                        + "2.-Especialidad");
                        eleccion = Integer.parseInt(entrada.readLine());
                        switch (eleccion) {
                            case 1:
                                System.out.println("¿Qué nombre le quieres poner ahora?");
                                cambiar = entrada.readLine();
                                for (int i=0;i<medicos.listaMed.size();i++){
                                    if(medicos.listaMed.get(i).getIdMedico()==id){
                                        medicos.listaMed.get(i).setNombreMedico(cambiar);
                                    }
                                } 
                                medicos.listM();
                                break;
                            case 2:
                                System.out.println("¿Qué especialidad le quieres poner ahora?");
                                cambiar = entrada.readLine();
                                for (int i=0;i<medicos.listaMed.size();i++){
                                    if(medicos.listaMed.get(i).getIdMedico()==id){
                                        medicos.listaMed.get(i).setEspecialidad(cambiar);
                                    }
                                }
                                medicos.listM();
                                break;
                            default:
                                System.out.println("La opción ingresada es incorrecta");
                                break;
                        }
                        break;
                    default:
                        System.out.println("La opción ingresada es incorrecta, "
                        + "ingresa alguna opción valida");
                        break;
                    }
                break;
            case 2:
                System.out.println("Seleccionaste \"Dar de baja o actualizar paciente\""); 
                System.out.println("------------------------------------");
                System.out.println("¿Qué deseas realizar?:\n"
                        + "1.-Dar de baja\n"
                        + "2.-Actualizar");
                eleccion = Integer.parseInt(entrada.readLine());
                System.out.println("Ingresa el id del paciente con el que deseas interactuar;");
                System.out.println("Lista pacientes");
                System.out.println("------------------------------------");
                pacientes.listP();
                System.out.println("------------------------------------");
                id = Integer.parseInt(entrada.readLine());
                switch (eleccion) {
                    case 1:                     
                        for (int i=0;i<pacientes.listaPac.size();i++){
                            if(pacientes.listaPac.get(i).getIdPaciente()==id){
                                for(int j=i;j<pacientes.listaPac.size();j++){
                                    if(j<pacientes.listaPac.size()-1){
                                       pacientes.listaPac.get(j+1).setIdPaciente(j+1); 
                                    }
                                }
                                pacientes.listaPac.remove(i);
                            }
                        } 
                        pacientes.listP();
                        break;
                    case 2:
                        System.out.println("¿Qué nombre le quieres poner ahora?");
                        cambiar = entrada.readLine();
                        for (int i=0;i<pacientes.listaPac.size();i++){
                            if(pacientes.listaPac.get(i).getIdPaciente()==id){
                                pacientes.listaPac.get(i).setNombrePaciente(cambiar);
                            }
                        } 
                        pacientes.listP();
                        break;
                    default:
                        System.out.println("La opción ingresada es incorrecta, "
                        + "ingresa alguna opción valida");
                        break;
                    }
                break;
            case 3:
                System.out.println("Seleccionaste \"Borrar o actualizar cita\""); 
                System.out.println("------------------------------------");
                System.out.println("¿Qué deseas realizar?:\n"
                        + "1.-Dar de baja\n"
                        + "2.-Actualizar");
                eleccion = Integer.parseInt(entrada.readLine());
                System.out.println("Ingresa el id de la cita con la que deseas interactuar;");
                System.out.println("Lista citas");
                System.out.println("------------------------------------");
                citas.listC();
                System.out.println("------------------------------------");
                id = Integer.parseInt(entrada.readLine());
                switch (eleccion) {
                    case 1:                     
                        for (int i=0;i<citas.listaCit.size();i++){
                            if(citas.listaCit.get(i).getId()==id){
                                for(int j=i;j<citas.listaCit.size();j++){
                                    if(j<citas.listaCit.size()-1){
                                       citas.listaCit.get(j+1).setId(j+1); 
                                    }
                                }
                                citas.listaCit.remove(i);
                            }
                        } 
                        citas.listC();
                        break;
                    case 2:
                        System.out.println("¿Qué deseas actulizar de un?:\n"
                        + "1.-Motivo\n"
                        + "2.-Fecha\n"
                        + "3.-Medico\n"
                        + "4.-Paciente");
                        eleccion = Integer.parseInt(entrada.readLine());
                        switch (eleccion) {
                            case 1:
                                System.out.println("¿Qué motivo le quieres poner ahora?");
                                cambiar = entrada.readLine();
                                for (int i=0;i<citas.listaCit.size();i++){
                                    if(citas.listaCit.get(i).getId()==id){
                                        citas.listaCit.get(i).setMotivoCita(cambiar);
                                    }
                                } 
                                citas.listC();
                                break;
                            case 2:
                                System.out.println("¿Qué fecha le quieres poner ahora? (Formato Día: dd/mm/aa "
                                        + "Formato hota: hh:mm)");
                                cambiar = entrada.readLine();
                                for (int i=0;i<citas.listaCit.size();i++){
                                    if(citas.listaCit.get(i).getId()==id){
                                        citas.listaCit.get(i).setFecha(cambiar);
                                    }
                                }
                                citas.listC();
                                break;
                            case 3:
                                System.out.println("¿Qué medico le quieres le quieres poner ahora? (ingresa el id))");
                                System.out.println("Lista medicos:");
                                medicos.listM();
                                System.out.println("---------------------------------------:");
                                int idMedico = Integer.parseInt(entrada.readLine());
                                Medico medi = new Medico();
                                medi = medicos.listaMed.get(idMedico-1);

                                for (int i=0;i<citas.listaCit.size();i++){
                                    if(citas.listaCit.get(i).getId()==id){
                                        citas.listaCit.get(i).setMedico(medi);
                                    }
                                }
                                citas.listC();
                                break;    
                            case 4:
                                System.out.println("¿Qué paciente le quieres poner ahora? (ingresa el id))");
                                System.out.println("Lista pacientes:");
                                pacientes.listP();
                                System.out.println("---------------------------------------:");
                                int idPaciente = Integer.parseInt(entrada.readLine());
                                Paciente pac = new Paciente();
                                pac = pacientes.listaPac.get(idPaciente-1);
                                
                                for (int i=0;i<citas.listaCit.size();i++){
                                    if(citas.listaCit.get(i).getId()==id){
                                        citas.listaCit.get(i).setPaciente(pac);
                                    }
                                }
                                citas.listC();
                                break;
                            default:
                                System.out.println("La opción ingresada es incorrecta");
                                break;
                        }
                        break;
                    default:
                        System.out.println("La opción ingresada es incorrecta, "
                        + "ingresa alguna opción valida");
                        break;
                    }
                break;
            case 4:
                System.out.println("Seleccionaste \"Borra sistema por completo\""); 
                System.out.println("------------------------------------");
                medicos.listaMed.removeAll(medicos.listaMed);
                pacientes.listaPac.removeAll(pacientes.listaPac);
                citas.listaCit.removeAll(citas.listaCit);
                break;
            default:
                System.out.println("La opción ingresada es incorrecta, "
                        + "ingresa alguna opción valida");
                System.out.println("¿Deseas volver a intentar o salir al menu principal? "
                        + "(S o si para \"Continuar\" o cualquier otra cosa para \"Volver a menu principal\"");
                repetir = entrada.readLine();
                if(repetir.equalsIgnoreCase("S") || repetir.equalsIgnoreCase("Si")){
                   menu2(); 
                }else{
                    System.out.println("Volviendo a menú principal...");
                    System.out.println("Menu principal;");
                }
        }
        System.out.println("¿Deseas hacer algo más o salir al menu principal? "
                        + "(S o si para \"Continuar\" o cualquier otra cosa para \"Volver a menu principal\"");
                repetir = entrada.readLine();
                if(repetir.equalsIgnoreCase("S") || repetir.equalsIgnoreCase("Si")){
                   menu2(); 
                }else{
                    System.out.println("Volviendo a menú principal...");
                    System.out.println("Menu principal;");
                }
    }

}
