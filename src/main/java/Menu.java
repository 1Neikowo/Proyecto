import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner teclado;
    private AIV gestor;

    public Menu(){
        teclado= new Scanner(System.in);
        gestor = new AIV();

    }
    public enum Clasificaciones{
        Hierba, Arbusto,Arbol,Enredadera,Suculenta,Bulbosa
    }
    public enum Ambientes{
    Interior,Exterior

    }
public enum Tamanos{
        Pequeno,Mediano,Grande
    }

    public String clasificacion(){
        int n=validarInt();
        System.out.println("1. Hierba");
        System.out.println("2. Arbusto");
        System.out.println("3. Arbol");
        System.out.println("4. Enredadera");
        System.out.println("5. Suculenta");
        System.out.println("6. Bulbosa");
        switch (n){
            case 1:
                return Clasificaciones.Hierba.toString();
            case 2:
                return Clasificaciones.Arbusto.toString();
            case 3:
                return Clasificaciones.Arbol.toString();
            case 4:
                return Clasificaciones.Enredadera.toString();
            case 5:
                return Clasificaciones.Suculenta.toString();
            case 6:
                return Clasificaciones.Bulbosa.toString();
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
        return null;

    }
    public String ambiente(){
        int n=validarInt();
        System.out.println("1. Interior");
        System.out.println("2. Exterior");
        switch(n) {
            case 1:
                return Ambientes.Interior.toString();
            case 2:
                return Ambientes.Exterior.toString();
        }
        return null;
    }
    public String tamano(){
        int n=validarInt();
        System.out.println("1. Pequeño");
        System.out.println("2. Mediano");
        System.out.println("3. Grande");

        switch(n) {
            case 1:
                return Tamanos.Pequeno.toString();
            case 2:
                return Tamanos.Mediano.toString();
            case 3:
                return Tamanos.Grande.toString();
        }
        return null;
    }


    public void mostrarMenu() {

        Login login = new Login();
        Admin admin = new Admin("admin","admin");

        login.iniciarSesion(admin);

        while (true) {
            mostrarMenuPrincipal();
            int opcion = validarInt();
            procesarOpcion(opcion);

        }
    }
    public int validarInt(){
        Scanner teclado = new Scanner(System.in);
        try {
            return teclado.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error. Ingrese un valor válido para la variable.");
            return validarInt();
        }

    }
    public void mostrarMenuPrincipal(){
        System.out.println("\nGestor de Plantas AIV");
        System.out.println("1. Agregar Planta ");
        System.out.println("2. Eliminar Planta");
        System.out.println("3. Buscar Planta");
        System.out.println("4. Mostrar Plantas");
        System.out.println("5. Actualizar Planta");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
    }
    public void gestionAgregarPlantaNueva() throws IOException {
        System.out.print("Nombre de la planta: ");
        String nombre = teclado.next();
        System.out.print("Seleccione la clasificación: ");
        String clasificacion = clasificacion();
        System.out.print("Tamaño: ");
        String tamano = tamano();
        System.out.print("Ambiente: ");
        String ambiente = ambiente();
        System.out.print("Precio: ");
        int precio = validarInt();
        System.out.print("Ingrese la cantidad inicial de la planta: ");
        int cantidad=validarInt();
        Planta nuevaPlanta= new Planta(nombre, clasificacion, tamano, ambiente, precio, cantidad);
        gestor.agregarPlantaNueva(nuevaPlanta);
        System.out.println("Planta agregada con éxito.");
    }
    public void gestionAgregarPlantaExistente() throws IOException {
        System.out.print("Nombre de la planta: ");
        String nombre = teclado.next();
        System.out.print("Ingrese el id de la planta: ");
        int id = validarInt();
        System.out.print("Ingrese la cantidad de plantas a agregar: ");
        int cantidad = validarInt();
        gestor.agregarPlantaExistente(nombre, id, cantidad);
        System.out.println("Stock agregado con éxito.");

    }
    public int validarCantidadEliminar(){
        int cantidad=validarInt();

        if(cantidad<0){
            System.out.println("Ingrese una cantidad válida");
            return validarCantidadEliminar();
        }
    }

    public void procesarOpcion(int opcion) throws IOException {
        Scanner teclado = new Scanner(System.in);
        switch (opcion) {
            case 1:
                System.out.println("1. Agregar planta nueva al inventario");
                System.out.println("2. Agregar stock a una planta registrada");
                int opcion_= validarInt();
                switch (opcion_) {
                    case 1:
                        gestionAgregarPlantaNueva();
                        break;
                    case 2:
                        gestionAgregarPlantaExistente();
                        break;
                }
                break;
            case 2:
                System.out.print("Nombre de la planta a eliminar: ");
                String nombreEliminar = teclado.next();
                System.out.println("Ingrese el id de la planta: ");
                int id = validarInt();
                System.out.println("Ingrese la cantidad a eliminar: ");
                int cantidad = validarInt();
                gestor.eliminarPlanta(nombreEliminar,id,cantidad);
                break;



































            case 3:
                System.out.print("Nombre de la planta a buscar: ");
                String nombreBuscar = teclado.next();
                Planta plantaEncontrada = gestor.buscarPlanta(nombreBuscar);
                if (plantaEncontrada != null) {
                    System.out.println("Planta encontrada:\n");
                    System.out.println(plantaEncontrada);
                } else {
                    System.out.println("Planta no encontrada.");
                }
                break;

            case 4:
                ArrayList<Planta> listaDePlantas = gestor.obtenerPlantas();
                System.out.println("\n");
                for (int i = 0; i < listaDePlantas.size();i++) {
                    System.out.println(listaDePlantas.get(i).toString());
                    System.out.println("-----------------------------");
                }
                break;

            case 5:
                System.out.print("Nombre de la planta a actualizar: ");
                String nombreActualizar = teclado.next();
                Planta plantaExistente = gestor.buscarPlanta(nombreActualizar);
                if (plantaExistente != null) {
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = teclado.next();
                    System.out.print("Nueva clasificación: ");
                    String nuevaClasificacion = teclado.next();
                    System.out.print("Nuevo tamaño: ");
                    String nuevoTamano = teclado.next();
                    System.out.print("Nuevo ambiente: ");
                    String nuevoAmbiente = teclado.next();
                    System.out.print("Nuevo precio: ");
                    int nuevoPrecio = validarInt();
                    int nuevacantidad=0;


                    Planta nuevaPlantaActualizada = new Planta(nuevoNombre, nuevaClasificacion, nuevoTamano, nuevoAmbiente, nuevoPrecio,nuevacantidad);
                    gestor.actualizarPlanta(nombreActualizar, nuevaPlantaActualizada);
                    System.out.println("Planta actualizada con éxito.");
                } else {
                    System.out.println("Planta no encontrada.");
                }
                break;

            case 6:
                System.out.println("Hasta luego.");
                System.exit(0);

            default:
                System.out.println("Opción no válida. Intente de nuevo.");
                break;
        }
    }

}