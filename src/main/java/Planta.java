public class Planta {
    private static int contadorPlanta=0;
    private int id;
    private String nombre;
    private String clasificacion;
    private String tamano;
    private String ambiente;
    private int precio;

    public Planta(String nombre, String clasificacion, String tamano, String ambiente, int precio) {
        this.id = ++contadorPlanta;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.tamano = tamano;
        this.ambiente = ambiente;
        this.precio = precio;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getTamano() {
        return tamano;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public double getPrecio() {
        return precio;
    }


    @Override
    public String toString() {
        return  nombre +" "+ clasificacion +" "+ tamano +" "+ ambiente +" "+ precio;

    }
}
