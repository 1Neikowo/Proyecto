import java.util.ArrayList;
import java.io.BufferedWriter;
public class Launcher {
    public static void main(String[] args) {
    GestorArchivo gestorArchivo = new GestorArchivo();


    Planta planta = new Planta("rosa","rojo", "verde", "perenne",12);
    Planta plant=  new Planta("margarita","blanco", "verde", "perenne",14);
    gestorArchivo.agregarPlantaArchivo(planta);
    gestorArchivo.agregarPlantaArchivo(plant);

    }
}
