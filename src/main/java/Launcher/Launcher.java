package Launcher;

import Modelo.AIV;
import Modelo.Planta;

import java.io.IOException;
import java.util.ArrayList;

public class Launcher {
    public static void main(String[] args) throws IOException {

        AIV aiv = new AIV();
        aiv.obtenerPlantas();
        //VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(aiv);
        ArrayList<Planta> listaDePlantas = aiv.obtenerListaDePlantas();
        listaDePlantas.add(new Planta("tulipan","flor","mediano","exterior",1000,10));
        listaDePlantas.add(new Planta("rosa","flor","mediano","exterior",1000,10));
        listaDePlantas.remove( 1);
        aiv.guardarCambios();



    }
}