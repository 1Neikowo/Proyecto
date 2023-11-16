import java.util.ArrayList;
import java.io.BufferedWriter;
public class Launcher {
    public static void main(String[] args) {
        GestorArchivo gestor = new GestorArchivo();


        Planta planta = new Planta("rosa", "rojo", "verde", "perenne", 12,1);
        Planta plant = new Planta("margarita", "blanco", "verde", "perenne", 14,2);
        Planta plant2= new Planta("camile", "blanco", "verde", "perenne", 14,3);
        gestor.agregarNuevaPlantaArchivo(planta);
        gestor.agregarNuevaPlantaArchivo(plant);

        ArrayList<Planta> plantas = gestor.obtenerPlantasArchivo();
        for (int i = 0; i < plantas.size(); i++) {
            System.out.println(plantas.get(i));

        }
    }
}