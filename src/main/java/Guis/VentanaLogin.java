package Guis;

import javax.swing.*;
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

    private void agregarListenerCerrarVentana() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "¡Nos vemos, vuelve pronto!");
                    System.exit(0);
                }
            }
        });
    }

    public void generarElementosVentana() {
        generarTitulo();
        generarPasswordField();
        generarBotonVolver();
        generarBotonIngresar();
        generarLabelPasswordProv();
        generarBotonMostrarOcultar();
    }

    public void generarTitulo() {
        String textoTitulo = "Bienvenido a AIV";
        String textoSubtitulo = "Ingresa tus datos para comenzar";
        super.generarJLabelEncabezado(textoTitulo, 125, 40, 300, 50);
        super.generarJLabel(textoSubtitulo, 125, 80, 300, 50);
    }

    public void generarLabelPasswordProv() {
        String textoNombre = "Contraseña Predeterminada: 123";
        super.generarJLabel(textoNombre, 125, 140, 250, 20);
    }

    public void generarPasswordField() {
        String textoNombre = "Password:";
        super.generarJLabel(textoNombre, 125, 180, 150, 20);
        passwordField = super.generarJPasswordField(125, 210, 250, 20);
        this.add(passwordField);
    }

    public void generarBotonIngresar() {
        btIngresar = super.generarBotonPrincipal("Ingresar", 275, 240, 100, 30);
        this.add(btIngresar);
        btIngresar.addActionListener(this);
    }

    public void generarBotonMostrarOcultar() {
        btMostrarOcultar = super.generarBotonPrincipal("Mostrar/ocultar", 75, 210, 50, 30);
        ImageIcon icono = new ImageIcon("src/main/java/Utils/ojoabierto.jpg");
        btMostrarOcultar.setIcon(icono);
        this.add(btMostrarOcultar);
        btMostrarOcultar.addActionListener(this);
    }

    public void generarBotonVolver() {
        btVolver = super.generarBotonPrincipal("Salir", 200, 425, 100, 30);
        this.add(btVolver);
        btVolver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btMostrarOcultar) {
            if (oculto) {
                passwordField.setEchoChar('*');
                oculto= false;
            } else {
                passwordField.setEchoChar((char) 0);
                oculto = true;
            }
        }
        if (event.getSource() == btVolver) {
            JOptionPane.showMessageDialog(this, "Hasta Luego! 😉");
            this.dispose();
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
