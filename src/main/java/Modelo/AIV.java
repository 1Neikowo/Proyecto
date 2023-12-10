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
        guardarCambios();
    }
    //Metodo para verificar si existe una planta por nombre e id
    public boolean existeplanta(String nombre, int id) {
        for (int i = 0; i < listaDePlantas.size(); i++) {
            if (listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre) && listaDePlantas.get(i).getId() == id) {
                return true;}
        }
        return false;
    }
    //Metodo para verificar si existe una planta por nombre
    @Override
    public boolean existePlanta(String especie) {
        for (Planta planta : listaDePlantas) {
            if (planta.getNombre().equalsIgnoreCase(especie)) {
                return true;
            }
        }
        return false;
    }
    //Metodo para verificar si existe una planta por id
    public boolean existePlanta(int id) {
        for (Planta planta : listaDePlantas) {
            if (planta.getId()== id)
                return true;
            }
        return false;
    }

    //Metodo para modificar la cantidad de una planta
    public void  modificarCantidadPlanta(String nombre, int id, int cantidad)  {
        for(int i = 0; i < listaDePlantas.size(); i++){
            if(listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre) && listaDePlantas.get(i).getId()==id){
                listaDePlantas.get(i).setCantidad(cantidad);
                guardarCambios();
            }
        }
    }
    //Metodo para eliminar una planta
    public void eliminarPlanta(String nombre, int id){
        for(int i = 0; i < listaDePlantas.size(); i++){
            if(listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre) && listaDePlantas.get(i).getId()==id){
                listaDePlantas.remove(i);
                guardarCambios();
            }
        }
    }
    //Metodo para buscar una planta
    public Planta buscarPlanta(String nombre,int id){
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
        gestorArchivo.guardarCambios(this.listaDePlantas);
    }

    public void guardarUltimoID(){
        if(!listaDePlantas.isEmpty()){
        String  id= buscarIdMayor();
        gestorArchivo.guardarUltimoID(id);
        }else{
            gestorArchivo.guardarUltimoID("0");
        }
    }
    public String buscarIdMayor() {
        int contador = listaDePlantas.get(0).getId();
        for (int i = 0; i < listaDePlantas.size(); i++) {
            int idActual = listaDePlantas.get(i).getId();
            if (idActual > contador) {
                contador = idActual;
            }
        }
        return String.valueOf(contador);
    }
    public String leerUltimoIDArchivo(){
        return gestorArchivo.leerUltimoID();
    }
    public boolean arrayEmpty(){
        return listaDePlantas.isEmpty();
    }
}
