package Datos;

import Modelo.Planta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

public class GestorArchivo {
    private String nombreArchivo;

    public GestorArchivo() {
        this.nombreArchivo = "src/main/java/Datos/plantas.txt";
    }

    public boolean existeArchivo() {
        //Se verifica si existe el archivo con el nombre "nombreArchivo"
        File file = new File(nombreArchivo);
        return file.exists();
    }

    public void agregarPlantaArchivo(Planta planta) throws IOException {
        boolean existeArchivo = existeArchivo();
        if (existeArchivo) {
            agregarPlantaArchivoExiste(planta);
        } else {
            agregarPlantaArchivoNoExiste(planta);
        }
        }


    //Metodo para agregar una planta a un archivo existente
    //Funcionamiento FileWriter: Dado que el archivo existe, se agregara la informacion en la última fila, sin sobreescribir la preexistente dado el boolean entregado.
    //FileWriter segundo parametro boolean: true para agregar la informacion al final del archivo, false para sobreescribir el archivo.
    public void agregarPlantaArchivoExiste(Planta planta) throws IOException{
        try {
            //Agregar planta a un archivo existente
            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true));
            writer.write(planta.toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para agregar una planta a un archivo no existente
    //Funcionamiento FileWriter: Dado que el archivo no existe, se creara un archivo nuevo y se agregara la informacion en la primera fila
    public void agregarPlantaArchivoNoExiste(Planta planta) throws IOException{
        try {
            //Crear un archivo y agregar planta
            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo,false));
            writer.write(planta.toString());
            writer.newLine();
            System.out.println("Modelo.Planta añadida con exito");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Planta> obtenerPlantasArchivo(){
        //Se obitiene el arreglo de plantas que se almacenó en el arhivo de texto
        ArrayList<Planta> plantas = new ArrayList<>();
        if (existeArchivo()){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
                String linea = reader.readLine();
                while (linea != null){
                    String[] datos = linea.split(",");
                    Planta planta = new Planta(Integer.parseInt(datos[0]),datos[1],datos[2],datos[3],datos[4],Integer.parseInt(datos[5]),Integer.parseInt(datos[6]));
                    plantas.add(planta);
                    linea = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return plantas;
    }

    public void guardarCambios(ArrayList<Planta> plantas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, false))) {
            for (Planta persona : plantas) {
                bw.write(persona.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    }