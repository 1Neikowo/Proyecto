import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.io.PrintWriter;

public class GestorArchivo {
    private String nombreArchivo;

    public GestorArchivo() {
        this.nombreArchivo = "plantas.txt";
    }

    public boolean existeArchivo() {
        //Se verifica si existe el archivo con el nombre "nombreArchivo"
        File file = new File(nombreArchivo);
        return file.exists();
    }

    public void agregarPlantaArchivo(Planta planta) throws IOException {
        boolean existeArchivo = existeArchivo();
        if (existeArchivo) {
            agregarPlantaNuevaArchivoExiste(planta);
        } else {
            agregarPlantaNuevaArchivoNoExiste(planta);
        }

        }
    public void agregarPlantaExistente(){

    }

    // dividir método en otros métodos






    public boolean existePlanta(Planta plant){
        ArrayList<Planta> plantas = obtenerPlantasArchivo();
        for(Planta planta: plantas){
            if(planta.getNombre().equals(plant.getNombre()) && planta.getId()==plant.getId()){
                return true;
            }
        }
        return false;
    }
    //Metodo para agregar una planta a un archivo existente
    //Funcionamiento FileWriter: Dado que el archivo existe, se agregara la informacion en la última fila, sin sobreescribir la preexistente dado el boolean entregado.
    //FileWriter segundo parametro boolean: true para agregar la informacion al final del archivo, false para sobreescribir el archivo.
    public void agregarPlantaNuevaArchivoExiste(Planta planta) throws IOException{
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
    public void agregarPlantaNuevaArchivoNoExiste(Planta planta) throws IOException{
        try {
            //Crear un archivo y agregar planta
            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
            writer.write(planta.toString());
            writer.newLine();
            System.out.println("Planta añadida con exito");
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo,false))) {
            for (Planta persona : plantas) {
                bw.write(persona.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }











































    private List<String> leerArchivo(File archivo, String nombre, int id, int cantidad) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(actualizarCantidad(linea, nombre, id, cantidad));
            }
        }
        return lineas;
    }

    private String actualizarCantidad(String linea, String nombre, int id, int cantidad) {
        String[] partes = linea.split(",");
        if (partes[1].equals(nombre) && Integer.parseInt(partes[0]) == id) {
            System.out.println("Se encontró la planta");
            return partes[0] + "," + partes[1] + "," + partes[2] + "," + partes[3] + "," + partes[4] + "," + partes[5] + "," + cantidad;
        } else {
            return linea;
        }
    }

    private void escribirArchivo(File archivo, List<String> lineas) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                pw.println(linea);
            }
        }
    }








}


