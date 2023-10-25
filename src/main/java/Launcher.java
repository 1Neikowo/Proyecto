
public class Launcher {
    public static void main(String[] args) {
        Admin admin = new Admin("admin", "admin");
        Menu menu = new Menu(admin);
        menu.mostrarMenu();
    }
}
