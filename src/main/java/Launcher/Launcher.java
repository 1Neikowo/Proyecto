package Launcher;
import javax.swing.*;
import java.awt.*;


import Modelo.AIV;
import Modelo.Planta;
import Guis.VentanaLogin;
import java.io.IOException;
import java.util.ArrayList;

public class Launcher {
    public static void main(String[] args) throws IOException {
        AIV aiv = new AIV();
        SwingUtilities.invokeLater(() -> new VentanaLogin(aiv));
    }
}