import java.util.ArrayList;
import java.io.BufferedWriter;
public class Launcher {
    public static void main(String[] args) {
        GestorArchivo gestorArchivo = new GestorArchivo();


        Planta planta = new Planta("rosa", "rojo", "verde", "perenne", 12,1);
        Planta plant = new Planta("margarita", "blanco", "verde", "perenne", 14,2);
        Planta plant2= new Planta("camile", "blanco", "verde", "perenne", 14,3);
        gestorArchivo.agregarPlantaExistenteArchivo("rosa",1,6);

        ArrayList<Planta> plantas = gestorArchivo.obtenerPlantasArchivo();
        for (int i = 0; i < plantas.size(); i++) {
            System.out.println(plantas.get(i));

        }
    }
}