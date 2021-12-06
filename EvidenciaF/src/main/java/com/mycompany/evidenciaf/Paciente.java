package com.mycompany.evidenciaf;
/**
 *
 * @author Cortez Ramos
 */
import java.io.*;
import java.util.*;

public class Paciente {
    private int idPaciente;
    private String nombrePaciente;
    public ArrayList<Paciente> listaPac;
    public ArrayList<String> lista2 = new ArrayList<String>();

    public Paciente(){
        
    }
    
    public Paciente(int idPaciente, String nombrePaciente) {
        this.idPaciente = idPaciente;
        this.nombrePaciente = nombrePaciente;
    }
    
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    //Necesario añadir en los demas
    public void cargarPacientes() {
        if (listaPac == null) {
            listaPac = new ArrayList<>();
        }

        Paciente paciente1 = new Paciente(1,"Antonio Medina");
        Paciente paciente2 = new Paciente(2,"Gustavo Flores");
        listaPac.add(paciente1);
        listaPac.add(paciente2);
        
        
        int id ;
        String file="C:\\Users\\Cortez Ramos\\OneDrive\\Documentos\\txt2\\pacientes.txt";
        File archivo = new File(file);
        if (!archivo.exists()) {
            System.out.println("No se ha detectado ninguna lista existente");
        }else{
            ArrayList<String> texto = new ArrayList<String>();
            texto=leerTxt("C:\\Users\\Cortez Ramos\\OneDrive\\Documentos\\txt2\\pacientes.txt");

            for(int i=0; i<texto.size(); i++){
                 String data = texto.get(i);
                 String a= data;
                id = listaPac.size() + 1;
                Paciente nuevoPaciente = new Paciente(id,a);
                listaPac.add(nuevoPaciente);
            }
        }
        System.out.println("Los pacientes han sido cargados: " + listaPac.size());
    }
    
    public void añadirLista(Paciente paciente) throws IOException{
       listaPac.add(paciente);
       for (int i=2; i<listaPac.size(); i++){
          lista2.add(listaPac.get(i).getNombrePaciente()); 
       }
       System.out.println("El paciente fue añadido a la lista"); 
       save(lista2);
    }
    
    public void listP() {
        if(listaPac.size()==0){
            System.out.println("No hay pacientes para mostrar, crea alguno");
        }else{
            for (int i=0;i<listaPac.size();i++){  
                System.out.println(listaPac.get(i).getAll());
            } 
        }
    }
    
    public String getAll() {
        return "ID paciente:"+idPaciente+", "+"Nombre paciente:"+nombrePaciente;
    }
    
    public int cantidadPacientes(){
        return listaPac.size();
    }
    
    public void save(ArrayList<String> lista) throws IOException{
        String file="C:\\Users\\Cortez Ramos\\OneDrive\\Documentos\\txt2\\pacientes.txt";
        FileWriter fichero = new FileWriter(file);
        fichero.close();
        File archivo = new File(file);
        archivo.delete();
        BufferedWriter bufferW = new BufferedWriter(new FileWriter(file, true));
        for(int i=0; i<lista.size(); i++){
            bufferW.write(lista.get(i)+"\n");
        }
        bufferW.close();
        System.out.println("Se ha guardado el contenido con exito");
    }
    
    public static ArrayList leerTxt(String direccion){
        ArrayList<String> lista = new ArrayList<String>();
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                lista.add(bfRead);
            }   
        }catch(Exception e){
            System.out.println("No se encontró archivo");
        }
        return lista;
    }
}
