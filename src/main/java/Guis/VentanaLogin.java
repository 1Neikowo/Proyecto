package Guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Datos.GestorAdminArchivo;
import Modelo.AIV;
import Modelo.Admin;

public class VentanaLogin extends VentanaBase {
    private JPasswordField passwordField;
    private JButton btVolver;
    private JButton btIngresar;
    private JButton btMostrarOcultar;
    private JLabel jlPasswordProv;
    private AIV aiv;
    private boolean oculto;

    public VentanaLogin(AIV aiv) {
        super("Login", 500, 520);
        this.aiv = aiv;
        oculto = false;
        generarElementosVentana();
        agregarListenerCerrarVentana();
    }

    public void generarElementosVentana() {
        generarTitulo();
        generarPasswordField();
        generarBotonIngresar();
        generarLabelPasswordProv();
        generarBotonMostrarOcultar();
    }

    private void agregarListenerCerrarVentana() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "¡Hasta Luego 😉, vuelve pronto!");
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    public void agregarImagenDeFondo() {
        // Cambia la ruta de la imagen según la ubicación de tu archivo de imagen
        ImageIcon imagenDeFondo = new ImageIcon("src/main/resources/images/fondo1.png");

        // Crea un JLabel con la imagen de fondo
        JLabel fondo = new JLabel(imagenDeFondo);
        fondo.setBounds(0, -30, getWidth(), getHeight());

        // Asegúrate de que el fondo esté detrás de otros componentes
        getLayeredPane().add(fondo, Integer.valueOf(Integer.MIN_VALUE));

        // Establece el contenido de la ventana como transparente
        ((JPanel) getContentPane()).setOpaque(false);
    }

    private ImageIcon redimensionImagen() {
        ImageIcon icon = new ImageIcon("src/main/resources/images/ojo.jpg");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newicon = new ImageIcon(newImage);
        return newicon;
    }

    private void generarTitulo() {
        String textoTitulo = "Bienvenido a AIV";
        String textoSubtitulo = "Ingresa tus datos para comenzar";
        super.generarJLabelEncabezado(textoTitulo, 150, 40, 300, 50);
        super.generarJLabel(textoSubtitulo, 150, 80, 300, 50);
    }

    private void generarLabelPasswordProv() {
        String textoNombre = "Contraseña Predeterminada: 123";
        super.generarJLabel(textoNombre, 125, 180, 250, 20);
    }

    private void generarPasswordField() {
        String textoNombre = "Contraseña:";
        super.generarJLabel(textoNombre, 125, 250, 150, 20);
        passwordField = super.generarJPasswordField(125, 275, 250, 20);
        this.add(passwordField);
    }

    private void generarBotonIngresar() {
        btIngresar = generarBotonPrincipal("Ingresar", 200, 350, 100, 30);
        this.add(btIngresar);
        btIngresar.addActionListener(this);
    }

    private void generarBotonMostrarOcultar() {
        btMostrarOcultar = super.generarBotonPrincipal("", 390, 275, 40, 25);
        ImageIcon icono = redimensionImagen();
        btMostrarOcultar.setIcon(icono);
        this.add(btMostrarOcultar);
        btMostrarOcultar.addActionListener(this);
    }
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btMostrarOcultar) {
            if (oculto) {
                passwordField.setEchoChar('*');
                oculto = false;
            } else {
                passwordField.setEchoChar((char) 0);
                oculto = true;
            }
        }
        if (event.getSource() == btIngresar) {
            procesarIngresar();
        }

    }

    private void procesarIngresar() {
        if (passwordField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una contraseña", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        } else {
            autenticarAdmin();
        }
    }

    private void autenticarAdmin() {
        GestorAdminArchivo gestorPass = new GestorAdminArchivo();
        if (gestorPass.getAdmin().autenticar(passwordField.getText())) {
            new VentanaMenuPrincipal(aiv);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "La contraseña ingresada es incorrecta", "Contraseña Incorrecta", JOptionPane.WARNING_MESSAGE);
            passwordField.setText("");
        }
    }
}
