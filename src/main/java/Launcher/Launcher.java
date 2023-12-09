package Launcher;
import javax.swing.*;
import java.awt.*;

import Guis.VentanaAgregar;
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
        ArrayList<Planta> plantas = aiv.obtenerListaDePlantas();

        SwingUtilities.invokeLater(() -> new VentanaLogin(aiv));


    }
}