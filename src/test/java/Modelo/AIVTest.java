package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class AIVTest {
    private AIV aiv;
    private ArrayList<Planta> plantas;

    @BeforeEach
    void setUp() {
        aiv = new AIV();
        plantas = new ArrayList<>();
    }

    @Test
    void TestAgregarPlanta() {
        Planta planta1 = new Planta("Rosa", "Flor", "Mediano", "Interior", 10, 5);
        Planta planta2 = new Planta("Tulipan", "Flor", "Pequeño", "Exterior", 8, 7);

        aiv.agregarPlantaNueva(planta1);
        aiv.agregarPlantaNueva(planta2);

        plantas.add(planta1);
        plantas.add(planta2);

        assertEquals(plantas, aiv.obtenerListaDePlantas());
    }


    @Test
    void TestExistePlanta() {
        Planta planta1 = new Planta("Rosa", "Flor", "Mediano", "Interior", 10, 5);
        aiv.agregarPlantaNueva(planta1);
        assertTrue(aiv.existeplanta(planta1.getNombre(), planta1.getId()));
    }

    @Test
    void TestBuscarIdMayor() {
        Planta planta1 = new Planta("Rosa", "Flor", "Mediano", "Exterior", 10, 5);
        Planta planta2 = new Planta("Tulipan", "Flor", "Pequeño", "Exterior", 8, 7);
        Planta planta3 = new Planta("Margarita", "Flor", "Pequeño", "Exterior", 9, 8);

        aiv.agregarPlantaNueva(planta1);
        aiv.agregarPlantaNueva(planta2);
        aiv.agregarPlantaNueva(planta3);

        String highestId = aiv.buscarIdMayor();
        assertEquals("3", highestId);
    }
}
