import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void getNombre() {
        Admin admin= new Admin("","32123");
        String nombre = admin.getNombre();
        assertEquals("",nombre);
    }

    @Test
    void autenticar() {
        Admin admin2= new Admin("","32123");
        boolean autentificar = admin2.autenticar("_","32123");
        assertFalse(autentificar);
    }
}