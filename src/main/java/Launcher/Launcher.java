package Launcher;
import javax.swing.*;
import java.awt.*;
import Guis.*;
import Modelo.*;
import java.io.IOException;
import java.util.ArrayList;

public class Launcher {
    public static void main(String[] args) throws IOException {
        AIV aiv = new AIV();
        aiv.obtenerPlantas();
        ArrayList<Planta> plantas = aiv.obtenerListaDePlantas();
        SwingUtilities.invokeLater(() -> new VentanaLogin(aiv));
        for(Planta plant : plantas){
            System.out.println(plant.toString());
        }
        //Codigo Completo
        //Para el dia de la presentacion: vaciar plantas.txt, para mostrar cómo funciona el guardado y lectura del ultimo id.



    }
}