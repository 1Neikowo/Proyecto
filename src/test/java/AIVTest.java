import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AIVTest {

    @Test
    void eliminarPlanta() {
        AIV gestor = new AIV();
        Planta planta1 = new Planta("rosa", "flor", "pequeño", "exterior", 1);
        gestor.agregarPlanta(planta1);
        Planta planta2 = new Planta("tulipan", "flor", "pequeño", "exterior", 1);
        gestor.agregarPlanta(planta2);
        boolean resultado = gestor.eliminarPlanta("orquidea");
        assertFalse(resultado);
    }

    @Test
    void buscarPlanta() {
        AIV gestor= new AIV();
        Planta planta1 = new Planta("girasol", "flor", "grande", "exterior", 1);
        gestor.agregarPlanta(planta1);
        assertEquals(planta1, gestor.buscarPlanta("girasol"));

    }

    @Test
    void obtenerPlantas() {
        AIV gestor = new AIV();
        Planta planta1 = new Planta("rosa", "flor", "pequeño", "exterior", 1);
        gestor.agregarPlanta(planta1);
        Planta planta2 = new Planta("tulipan", "flor", "pequeño", "exterior", 1);
        gestor.agregarPlanta(planta2);
        ArrayList<Planta> listaDePlantas = new ArrayList<>();
        Planta plante3= new Planta("amancay", "flor", "mediano", "interior", 1);
        listaDePlantas.add(plante3);
        assertNotEquals(listaDePlantas, gestor.obtenerPlantas());





    }
}