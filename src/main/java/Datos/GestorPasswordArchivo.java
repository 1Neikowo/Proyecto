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

    }

    public boolean existeArchivoPassword() {
        //Se verifica si existe el archivo con el nombre "nombreArchivo"
        File file = new File(rutaPassword);
        return file.exists();
    }

    public void obtenerAdmin() {
        if (existeArchivoPassword()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(rutaPassword))) {
                String linea = reader.readLine();
                if (linea != null) {
                     this.admin=new Admin(linea); // Devuelve el objeto Admin creado con la línea del archivo
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ;
    }

    public Admin getAdmin() {
        return admin;
    }

}