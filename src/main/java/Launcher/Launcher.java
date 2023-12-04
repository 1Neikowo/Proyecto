package Launcher;

import Modelo.AIV;
import Modelo.Planta;

import java.io.IOException;
import java.util.ArrayList;

public class Launcher {
    public static void main(String[] args) throws IOException {

AIV aiv = new AIV();
Planta planta = new Planta("rosa", "flor", "mediano", "interior", 1000, 10);
aiv.agregarPlantaNueva(planta);
ArrayList<Planta> lista= aiv.obtenerListaDePlantas();
for(Planta p: lista){
    System.out.println(p.getNombre());
    System.out.println(p.getId());
    System.out.println(p.getClasificacion());
    System.out.println(p.getTamano());
    System.out.println(p.getAmbiente());
    System.out.println(p.getPrecio());
    System.out.println(p.getCantidad());
    System.out.println(" ");
}
        }
    }
