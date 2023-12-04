package Modelo;

public class Admin {
    private String nombre;
    private String password;

    public Admin(String nombre, String password){
        this.nombre = nombre;
        this.password = password;
    }
    public String getNombre(){
        return nombre;
    }
    public boolean autenticar(String nombre, String password){
        return this.nombre.equals(nombre) && this.password.equals(password);
    }
}
