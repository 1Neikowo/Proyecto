package Modelo;

import java.io.IOException;
import java.util.ArrayList;
import Datos.GestorArchivo;

public class AIV {
    private GestorArchivo gestorArchivo;
    private ArrayList<Planta> listaDePlantas;
    public AIV(){
        listaDePlantas = new ArrayList<>();
        gestorArchivo = new GestorArchivo();
    }
    //Getter listaDePlantas
    public ArrayList<Planta> obtenerListaDePlantas(){
        obtenerPlantas();
        return listaDePlantas;
    }
    //Setter listaDePlantas
    public void obtenerPlantas(){
        listaDePlantas = gestorArchivo.obtenerPlantasArchivo();
    }
    //Metodo para agregar una planta nueva
    public void agregarPlantaNueva(Planta planta) throws IOException {
        gestorArchivo.agregarPlantaArchivo(planta);
    }
    //Metodo para verificar si existe una planta
    public boolean existeplanta(String nombre, int id) {
        obtenerPlantas();
        for (int i = 0; i < listaDePlantas.size(); i++) {
            if (listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre) && listaDePlantas.get(i).getId() == id) {
                return true;}
        }
        return false;
    }
    //Metodo para modificar la cantidad de una planta
    public void  modificarCantidadPlanta(String nombre, int id, int cantidad) throws IOException {
        obtenerPlantas();
        for(int i = 0; i < listaDePlantas.size(); i++){
            if(listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre) && listaDePlantas.get(i).getId()==id){
                listaDePlantas.get(i).setCantidad(cantidad);
                gestorArchivo.guardarCambios(listaDePlantas);
                return;
            }
        }
    }
    //Metodo para eliminar una planta
    public void eliminarPlanta(String nombre, int id, int cantidad){
        obtenerPlantas();
        for(int i = 0; i < listaDePlantas.size(); i++){
            if(listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre) && listaDePlantas.get(i).getId()==id){
                listaDePlantas.remove(i);
                gestorArchivo.guardarCambios(listaDePlantas);
                return;
            }
        }
    }
    //Metodo para buscar una planta
    public Planta buscarPlanta(String nombre,int id){
        obtenerPlantas();
        for(int i = 0; i < listaDePlantas.size(); i++){
            if(listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre) && listaDePlantas.get(i).getId()==id){
                return listaDePlantas.get(i);
            }
        }
        return null;
    }
    //Metodo para actualizar una planta
    public void actualizarPlanta(String nombre, Planta nuevaPlanta){
        for (int i = 0; i < listaDePlantas.size(); i++){
            if (listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre)){
                listaDePlantas.set(i,nuevaPlanta);
                gestorArchivo.guardarCambios(listaDePlantas);
                return;
            }
        }
    }

}
