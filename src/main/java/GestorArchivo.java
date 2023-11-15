import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

public class GestorArchivo {
    String nombreArchivo;

    public GestorArchivo() {
        this.nombreArchivo = "plantas.txt";
    }
    public boolean existeArchivo() {
        //Se verifica si existe el archivo con el nombre "nombreArchivo"
        File file = new File(nombreArchivo);
        return file.exists();
    }
    public void agregarNuevaPlantaArchivo(Planta planta) {
        boolean existeArchivo = existeArchivo();
        if (existeArchivo) {
            try {
            //Agregar planta a un archivo existente
                BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true));
                writer.write(planta.toString());
                System.out.println("Planta añadida con exito");
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
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
    }
   public void agregarPlantaExistenteArchivo(String name, int id, int cantidad){
        ArrayList<Planta> plantas = obtenerPlantasArchivo();
        for(Planta planta: plantas){
            System.out.println(planta.toString());
        }
        boolean existeArchivo= existeArchivo();
        for(Planta plant : plantas){
            if(plant.getNombre().equals(name)&& plant.getId()==id){
                int nuevacantidad= plant.getCantidad()+cantidad;
                plant.setCantidad(nuevacantidad);
                if (existeArchivo) {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo,true));
                        writer.write(plant.toString());
                        writer.newLine();
                        System.out.println("Planta añadida con exito");
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                        System.out.println("No se halló un archivo, se creara uno nuevo y se agregara la nueva planta");
                        agregarNuevaPlantaArchivo(plant);
                }
            }else{
                System.err.println("No se halló la planta solicitada");
            }
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
                    Planta planta = new Planta(datos[0],datos[1],datos[2],datos[3],Integer.parseInt(datos[4]),Integer.parseInt(datos[5]));
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



    }

