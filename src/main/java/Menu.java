import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
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
        System.out.println("1. Agregar Planta");
        System.out.println("2. Eliminar Planta");
        System.out.println("3. Buscar Planta");
        System.out.println("4. Mostrar Plantas");
        System.out.println("5. Actualizar Planta");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
    }
    public void procesarOpcion(int opcion){
        AIV gestor = new AIV();
        Scanner teclado = new Scanner(System.in);
        switch (opcion) {
            case 1:
                System.out.print("Nombre de la planta: ");
                String nombre = teclado.next();
                System.out.print("Clasificación: ");
                String clasificacion = teclado.next();
                System.out.print("Tamaño: ");
                String tamano = teclado.next();
                System.out.print("Ambiente: ");
                String ambiente = teclado.next();
                System.out.print("Precio: ");
                int precio = validarInt();



                Planta nuevaPlanta = new Planta(nombre, clasificacion, tamano, ambiente, precio);
                gestor.agregarPlanta(nuevaPlanta);
                System.out.println("Planta agregada con éxito.");
                break;

            case 2:
                System.out.print("Nombre de la planta a eliminar: ");
                String nombreEliminar = teclado.next();
                if (gestor.eliminarPlanta(nombreEliminar)){
                    System.out.println("Planta eliminada con éxito");
                }else {
                    System.out.println("Planta no encontrada");
                }
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


                    Planta nuevaPlantaActualizada = new Planta(nuevoNombre, nuevaClasificacion, nuevoTamano, nuevoAmbiente, nuevoPrecio);
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
