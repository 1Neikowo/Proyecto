package Datos;
import Modelo.Admin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
public class GestorAdminArchivo {
    private String rutaPassword;
    private Admin admin;

    public GestorAdminArchivo() {
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
    public void guardarCambios(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaPassword, false))) {
            bw.write(this.admin.getPassword());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Admin getAdmin() {
        obtenerAdmin();
        return admin;
    }

}