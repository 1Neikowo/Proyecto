package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void getPassword() {
        Admin admin = new Admin("clave123");
        assertNotEquals("",admin.getPassword());

    }

    @Test
    void setPassword() {
        Admin admin = new Admin("clave123");
        admin.setPassword(" ");
        assertEquals(" ",admin.getPassword());
    }

    @Test
    void autenticar() {
        Admin admin = new Admin("clave123");
        assertTrue(admin.autenticar("clave123"));
    }
}