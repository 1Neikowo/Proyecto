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
    public void agregarPlantaNuevaArchivoNoExiste(Planta planta) throws IOException{
        try {
            //Crear un archivo y agregar planta
            System.out.println("No se halló un archivo, se creara uno nuevo");
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

    public Planta buscarPlanta(String name){
        ArrayList<Planta> plantas = obtenerPlantasArchivo();
        for(int i = 0; i < plantas.size(); i++){
            if(plantas.get(i).getNombre().equalsIgnoreCase(name)){
                return plantas.get(i);
            }
        }
        return null;


    }
    public void eliminarPlanta(String nombreAEliminar) {
        List<String> lineas = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith(nombreAEliminar + ",")) {
                    lineas.add(linea);
                }
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo,true));
            for (String l : lineas) {
                bw.write(l + System.lineSeparator());
            }
            bw.close();

            System.out.println("Planta eliminada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al eliminar planta: " + e.getMessage());
        }
    }


    // Dividir método anterior en otros métodos



    public void modificarCantidadPlantaArchivo(String nombre, int id, int cantidad){
        try {
            File archivo = new File(nombreArchivo);
            List<String> lineas = leerArchivo(archivo, nombre, id, cantidad);
            escribirArchivo(archivo, lineas);
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


