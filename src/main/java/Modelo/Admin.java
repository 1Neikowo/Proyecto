package Modelo;

public class Admin {
    private String nombre;
    private String password;

    public Admin( String password){
        this.nombre = "Administrad@r";
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public boolean autenticar(String nombre, String password){
        return this.password.equals(password);
    }
}
