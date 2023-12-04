import java.io.IOException;
import java.util.ArrayList;

public class AIV {
    private GestorArchivo gestorArchivo;
    private ArrayList<Planta> listaDePlantas;
    public AIV(){
        listaDePlantas = new ArrayList<>();
        gestorArchivo = new GestorArchivo();
    }
    public void obtenerPlantas(){
        listaDePlantas = gestorArchivo.obtenerPlantasArchivo();
    }
    public void agregarPlantaNueva(Planta planta) throws IOException {
        gestorArchivo.agregarPlantaArchivo(planta);
    }
    public boolean existeplanta(String nombre, int id) {
        obtenerPlantas();
        for (int i = 0; i < listaDePlantas.size(); i++) {
            if (listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre) && listaDePlantas.get(i).getId() == id) {
                return true;}
        }
        return false;
    }
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
    public Planta buscarPlanta(String nombre,int id){
        obtenerPlantas();
        for(int i = 0; i < listaDePlantas.size(); i++){
            if(listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre) && listaDePlantas.get(i).getId()==id){
                return listaDePlantas.get(i);
            }
        }
        return null;
    }
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
