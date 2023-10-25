import java.util.ArrayList;

public class AIV {
    private ArrayList<Planta> listaDePlantas;

    public AIV(){
        listaDePlantas = new ArrayList<>();
    }
    public void agregarPlanta(Planta planta){
        listaDePlantas.add(planta);
    }
    public boolean eliminarPlanta(String nombre){
        return listaDePlantas.removeIf(planta -> planta.getNombre().equalsIgnoreCase(nombre));
    }
    public Planta buscarPlanta(String nombre){
        for(int i = 0; i < listaDePlantas.size(); i++){
            if(listaDePlantas.get(i).getNombre().equalsIgnoreCase(nombre)){
                return listaDePlantas.get(i);
            }
        }
        return null;
    }
    public ArrayList<Planta> obtenerPlantas(){
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
