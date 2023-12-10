package Datos;

import Modelo.Planta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

public class GestorPlantasArchivo {
    private String rutaPlantas;
    private String rutaID;

    public GestorPlantasArchivo() {
        this.rutaPlantas = "src/main/java/Datos/plantas.txt";
        this.rutaID = "src/main/java/Datos/id.txt";
    }

    public void guardarUltimoID(String id) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaID, false))) {
            bw.write(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String leerUltimoID() {
        if(existeArchivoID()) {
            try (BufferedReader br = new BufferedReader(new FileReader(rutaID))) {
                return br.readLine(); // Retorna la línea leída
            } catch (IOException e) {
                e.printStackTrace();
                return null; // En caso de error, retorna null
            }
        }else{
            createArchivoID();
            guardarUltimoID("0");
            return leerUltimoID();
        }
    }
    public void createArchivoID() {
        File f = new File(rutaID);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean existeArchivoID() {
        //Se verifica si existe el archivo con el nombre "nombreArchivo"
        File file = new File(rutaID);
        return file.exists();
    }
    public boolean existeArchivoPlantas() {
        //Se verifica si existe el archivo con el nombre "nombreArchivo"
        File file = new File(rutaPlantas);
        return file.exists();
    }

    public void createArchivoPlantas() {
        File f = new File(rutaPlantas);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean archivoPlantasVacio() {
        File archivo = new File(rutaPlantas);
        if (archivo.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea = reader.readLine();
                while (linea != null) {
                    // Verificar si la línea contiene datos válidos
                    if (!linea.trim().isEmpty()) {
                        // Si encontramos al menos una línea con datos, el archivo no está vacío
                        return false;
                    }
                    linea = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Si no se encuentra ninguna línea con datos válidos, consideramos el archivo como vacío
        return true;
    }
    //Se obtienen las plantas almacenadas en el archivo de texto
//Si el archivo EXISTE, se procede a la lectura con normalidad
//Si el archivo NO EXISTE, se crea un archivo nuevo y se vuelve a intentar la lectura
    public ArrayList<Planta> obtenerPlantasArchivo() {
        ArrayList<Planta> plantas = new ArrayList<>();

        if (existeArchivoPlantas()) {
            //Si el archivo estaba vacío, se retorna un arreglo vacío

            if (!archivoPlantasVacio()) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(rutaPlantas));
                    String linea = reader.readLine();
                    while (linea != null) {
                        String[] datos = linea.split(",");
                        Planta planta = new Planta(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], Integer.parseInt(datos[5]), Integer.parseInt(datos[6]));
                        plantas.add(planta);
                        linea = reader.readLine();
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return plantas;
            }

        } else {
            createArchivoPlantas();
            return obtenerPlantasArchivo();
        }

        return plantas;
    }


    //Metodo encargado de guardar en el archivo los cambios realizados en listaDePlantas
    public void guardarCambios(ArrayList<Planta> plantas) {
        /// if (existeArchivoPlantas()) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaPlantas, false))) {
            for (Planta planta : plantas) {
                bw.write(planta.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //} else {
        //  createArchivoPlantas();
        //  guardarCambios(plantas);
        //}

    }


}