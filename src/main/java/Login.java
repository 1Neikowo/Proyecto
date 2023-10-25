import java.util.Scanner;

public class Login {
    private Scanner teclado = new Scanner(System.in);

    public void iniciarSesion(Admin admin) {
        boolean autenticado = false;
        while(!autenticado) {
            System.out.println("\nBienvenido a AIV    Ingrese 'x' para salir");
            System.out.println("Credenciales: admin | admin\n");

            System.out.print("Usuario: ");
            String usuario = teclado.next();

            if (usuario.equals("x")) {
                System.out.println("Hasta luego.");
                System.exit(0);
            }

            System.out.print("Contraseña: ");

            String contrasena = teclado.next();

            if (contrasena.equals("x")) {
                System.out.println("Hasta luego.");
                System.exit(0);
            }

            autenticado = admin.autenticar(usuario, contrasena);
            if (!autenticado){
                System.out.println("Inicio de sesión incorrecto. Intente de nuevo.");
            }
        }
    }
}
