package com.mycompany.evidenciaf;
/**
 *
 * @author Cortez Ramos
 */
import java.io.*;
import java.util.*;


public class Cita {
    private int id;
    private String motivoCita;
    private String fecha;
    private Medico medico;
    private Paciente paciente;
    public ArrayList<Cita> listaCit;
    public ArrayList<String> lista3 = new ArrayList<String>();
    
    public Cita(){
        
    }

    public Cita(int id, String motivoCita, String fecha, Medico medico, Paciente paciente) {
        this.id = id;
        this.motivoCita = motivoCita;
        this.fecha = fecha;
        this.medico = medico;
        this.paciente = paciente;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    
    //Necesario añadir en los demas
    public void cargarCitas(Medico medic, Paciente pacient) {
        if (listaCit == null) {
            listaCit = new ArrayList<>();
        }

        Medico medi = new Medico();
        medi = medic.listaMed.get(1);
        Paciente pac = new Paciente();
        pac = pacient.listaPac.get(1);
        Cita cita1 = new Cita(1,"Fractura de brazo","06/12/21 16:30", medi, pac);
        listaCit.add(cita1);
        
        
        int id ;
        String file="C:\\Users\\Cortez Ramos\\OneDrive\\Documentos\\txt2\\citas.txt";
        File archivo = new File(file);
        if (!archivo.exists()) {
            System.out.println("No se ha detectado ninguna lista existente");
        }else{
            ArrayList<String> texto = new ArrayList<String>();
            texto=leerTxt("C:\\Users\\Cortez Ramos\\OneDrive\\Documentos\\txt2\\citas.txt");

            for(int i=0; i<texto.size(); i++){
                String data = texto.get(i);
                String[] split = data.split(",");
                String a="";
                String b="";
                String c="";
                String d="";
                a=split[0];
                b=split[1];
                c=split[2];
                d=split[3];
                id = listaCit.size() + 1;
                for (int x=0;x<medic.listaMed.size();x++){  
                    if(medic.listaMed.get(x).getNombreMedico().equals(c)){
                        medi = medic.listaMed.get(x);
                    }
                }
                for (int x=0;x<pacient.listaPac.size();x++){  
                    if(pacient.listaPac.get(x).getNombrePaciente().equals(d)){
                        pac = pacient.listaPac.get(x);
                    }
                }

                Cita nuevaCita = new Cita(id,a,b,medi,pac);
                listaCit.add(nuevaCita);
            }
        }
        
        System.out.println("Las citas han sido cargadas: " + listaCit.size());
    }
    
    public void añadirLista(Cita cita) throws IOException{
       listaCit.add(cita);
       for (int i=1; i<listaCit.size(); i++){
          lista3.add(listaCit.get(i).getMotivoCita()+","+listaCit.get(i).getFecha()+","+listaCit.get(i).getMedico().getNombreMedico()
          +","+listaCit.get(i).getPaciente().getNombrePaciente()); 
       }
       System.out.println("El paciente fue añadido a la lista"); 
       save(lista3);
    }
    
    public void listC() {
        if(listaCit.size()==0){
            System.out.println("No hay pacientes para mostrar, crea alguno");
        }else{
            for (int i=0;i<listaCit.size();i++){  
                System.out.println(listaCit.get(i).getAll());
            } 
        }
    }
    
    public String getAll() {
        return "ID cita:"+id+", "+"Motivo cita:"+motivoCita+", "+"Fecha cita:"+fecha+", "
                +"Medico:"+medico.getNombreMedico()+", "+"Paciente:"+paciente.getNombrePaciente();
    }
    
    public int cantidadCitas(){
        return listaCit.size();
    }
    
    public void save(ArrayList<String> lista) throws IOException{
        String file="C:\\Users\\Cortez Ramos\\OneDrive\\Documentos\\txt2\\citas.txt";
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
