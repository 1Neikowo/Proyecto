import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlantaTest {
    Planta planta;
    @BeforeEach
    public void init() {
         planta = new Planta("rosa", "flor", "pequeña", "interior", 1);

    }

    @Test
    void getId() {
        assertNotEquals(1, planta.getId());
    }

    @Test
    void getNombre() {
        assertEquals("rosa", planta.getNombre());
    }

    @Test
    void getClasificacion() {
        assertNotEquals("", planta.getClasificacion());
    }

    @Test
    void getTamano() {
        assertEquals("pequeña", planta.getTamano());
    }

    @Test
    void getAmbiente() {
        assertNotEquals("233", planta.getAmbiente());
    }

    @Test
    void getPrecio() {
        assertEquals(1, planta.getPrecio());
    }
}