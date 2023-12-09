package Launcher;
import javax.swing.*;
import java.awt.*;
import Guis.VentanaMostrar;
import Modelo.AIV;
import Modelo.Planta;
import Guis.VentanaLogin;
import java.io.IOException;
import java.util.ArrayList;

public class Launcher {
    public static void main(String[] args) throws IOException {
        AIV aiv = new AIV();
        aiv.obtenerPlantas();
        ArrayList<Planta> plantas=aiv.obtenerListaDePlantas();
        for(Planta planta: plantas){
            System.out.println(planta.toString());
        }
        SwingUtilities.invokeLater(() -> new VentanaMostrar(aiv));
        aiv.guardarCambios();

    }
}