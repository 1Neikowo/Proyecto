package Modelo;

import java.io.IOException;
import java.util.ArrayList;
import Datos.GestorPlantasArchivo;

public class AIV {
    private GestorPlantasArchivo gestorArchivo;
    private ArrayList<Planta> listaDePlantas;
    public AIV(){

        gestorArchivo = new GestorPlantasArchivo();
        this.listaDePlantas = obtenerPlantas();
    }
    //Getter listaDePlantas
    public ArrayList<Planta> obtenerListaDePlantas(){
        return listaDePlantas;
    }
    //Setter listaDePlantas
    public ArrayList<Planta> obtenerPlantas(){
        return listaDePlantas = gestorArchivo.obtenerPlantasArchivo();
    }
    //Metodo para agregar una planta nueva
    public void agregarPlantaNueva(Planta planta) {
        listaDePlantas.add(planta);
        gestorArchivo.agregarPlantaArchivo(planta);
    }
    //Metodo para verificar si existe una planta
    public boolean existeplanta(String nombre, int id) {
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
    public void eliminarPlanta(String nombre, int id){
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
                return;
            }
        }
    }
    public void guardarCambios(){
        gestorArchivo.guardarCambios(listaDePlantas);
    }

    public void obtenerUltimoID(){
        if(listaDePlantas.size()!=0){
        int id=listaDePlantas.get(listaDePlantas.size()-1).getId();
        gestorArchivo.guardarUltimoID(id);
        }else{
            gestorArchivo.guardarUltimoID(1);
        }
    }
}
