import java.io.IOException;
import java.util.ArrayList;

public class AIV {
    private GestorArchivo gestorArchivo;
    private ArrayList<Planta> listaDePlantas;

    public AIV(){
        listaDePlantas = new ArrayList<>();
        gestorArchivo = new GestorArchivo();
    }
    public void agregarPlantaNueva(Planta planta) throws IOException {
        gestorArchivo.agregarPlantaArchivo(planta);
    }
    public void  agregarPlantaExistente(String nombre, int id, int cantidad) throws IOException {
        gestorArchivo.agregarPlantaExistenteArchivo(nombre, id, cantidad);
    }
    public void eliminarPlanta(String nombre, int id, int cantidad){
        gestorArchivo.quitarPlantaArchivo(nombre, id, cantidad);
    }


    public Planta buscarPlanta(String nombre,int id){
        ArrayList<Planta> listaDePlantas=obtenerPlantas();
        for(int i = 0; i < listaDePlantas.size(); i++){
            if(listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre) && listaDePlantas.get(i).getId()==id){
                return listaDePlantas.get(i);
            }
        }
        return null;
    }


    public ArrayList<Planta> obtenerPlantas(){
        listaDePlantas = gestorArchivo.obtenerPlantasArchivo();
        return listaDePlantas;
    }


    public void actualizarPlanta(String nombre, Planta nuevaPlanta){
        for (int i = 0; i < listaDePlantas.size(); i++){
            if (listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre)){
                listaDePlantas.set(i,nuevaPlanta);
                return;
            }
        }
    }
}
