package Datos;
import Modelo.Admin;
import Modelo.Planta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
public class GestorPasswordArchivo {
    private String rutaPassword;
    private Admin admin;

    public GestorPasswordArchivo() {
        this.rutaPassword = "src/main/java/Datos/password.txt";
        Admin admin = obtenerAdmin();
    }

    public boolean existeArchivoPassword() {
        //Se verifica si existe el archivo con el nombre "nombreArchivo"
        File file = new File(rutaPassword);
        return file.exists();
    }

    public Admin obtenerAdmin() {
        if (existeArchivoPassword()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(rutaPassword))) {
                String linea = reader.readLine();
                if (linea != null) {
                    new Admin(linea);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public Admin getAdmin() {
        return this.admin;
    }

}