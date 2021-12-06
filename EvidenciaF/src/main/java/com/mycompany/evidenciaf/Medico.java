package com.mycompany.evidenciaf;
/**
 *
 * @author Cortez Ramos
 */
import java.io.*;
import java.util.*;

public class Medico {
    private int idMedico;
    private String nombreMedico;
    private String especialidad;
    public ArrayList<Medico> listaMed;
    public ArrayList<String> lista1 = new ArrayList<String>();

    public Medico(){
        
    }
    
    public Medico(int idMedico, String nombreMedico, String especialidad) {
        this.idMedico = idMedico;
        this.nombreMedico = nombreMedico;
        this.especialidad = especialidad;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    //Necesario añadir en los demas
    public void cargarMedico() throws IOException{
        if (listaMed == null) {
            listaMed = new ArrayList<>();
        }

        Medico medico1 = new Medico(1,"Jaime Mesa","General");
        Medico medico2 = new Medico(2,"Ramon Valenzuela","Traumatologo");
        listaMed.add(medico1);
        listaMed.add(medico2);
        
        int id ;
        String file="C:\\Users\\Cortez Ramos\\OneDrive\\Documentos\\txt2\\medicos.txt";
        File archivo = new File(file);
        if (!archivo.exists()) {
            System.out.println("No se ha detectado ninguna lista existente");
        }else{
            ArrayList<String> texto = new ArrayList<String>();
            texto=leerTxt("C:\\Users\\Cortez Ramos\\OneDrive\\Documentos\\txt2\\medicos.txt");

            for(int i=0; i<texto.size(); i++){
                String data = texto.get(i);
                String[] split = data.split(",");
                String a="";
                String b="";
                a=split[0];
                b=split[1];
                id = listaMed.size() + 1;
                Medico nuevoMedico = new Medico(id,a,b);
                listaMed.add(nuevoMedico);
            }
        }
        System.out.println("Los medicos han sido cargados: " + listaMed.size());
    }
    
    public void añadirLista(Medico medico) throws IOException{
       listaMed.add(medico);
       for (int i=2; i<listaMed.size(); i++){
          lista1.add(listaMed.get(i).getNombreMedico()+","+listaMed.get(i).getEspecialidad()); 
       }
       System.out.println("El medico fue añadido a la lista"); 
       save(lista1);
    }
    
    public void listM() {
        if(listaMed.size()==0){
            System.out.println("No hay Medicos para mostrar, crea alguno");
        }else{
            for (int i=0;i<listaMed.size();i++){  
                System.out.println(listaMed.get(i).getAll());
            } 
        }
    }
    
    public String getAll() {
        return "ID medico:"+idMedico+", "+"Nombre medico:"+nombreMedico+", "+"Especialidad:"+especialidad;
    }
    
    public int cantidadMedicos(){
        return listaMed.size();
    }
    
    public void save(ArrayList<String> lista) throws IOException{
        String file="C:\\Users\\Cortez Ramos\\OneDrive\\Documentos\\txt2\\medicos.txt";
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
