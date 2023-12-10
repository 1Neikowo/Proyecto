package Guis;

import Modelo.AIV;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VentanaMenuBienvenida extends VentanaBase{
    private AIV aiv;
    private JButton btIngresar;
    private JButton btSalir;
    public VentanaMenuBienvenida(AIV aiv){
        super("AIV",820,520);
        this.aiv = aiv;
        generarElementosVentana();
        agregarListenerCerrarVentana();
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
    public void generarElementosVentana(){
        generarImagen();
        generarTituloSuperpuesto();
        generarBotones();
    }
    private void generarImagen() {
        try {
            BufferedImage imagen = ImageIO.read(new File("src/main/resources/images/fondo1.png"));
            JLabel labelImagen = new JLabel(new ImageIcon(imagen));
            labelImagen.setBounds(-170, 0, imagen.getWidth(), imagen.getHeight());
            this.add(labelImagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generarTituloSuperpuesto() {
        // Crear JLabel para el título "AIV"
        JLabel tituloLabel = new JLabel("AIV");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 100));
        tituloLabel.setForeground(Color.BLACK); // Color del título

        // Configurar la posición del título superpuesto
        int x = -60+(getWidth() - tituloLabel.getPreferredSize().width) / 2;
        int y = (getHeight() - tituloLabel.getPreferredSize().height) / 2;

        // Crear un JLayeredPane para gestionar las capas
        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.add(tituloLabel, JLayeredPane.PALETTE_LAYER);
        tituloLabel.setBounds(x, y, tituloLabel.getPreferredSize().width, tituloLabel.getPreferredSize().height);
    }
    private void generarBotones() {
        btIngresar = generarBotonPrincipal("Iniciar Sesión", 600, 400, 120, 40);
        this.add(btIngresar);
        btIngresar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btIngresar) {
            new VentanaLogin(aiv);
            this.dispose();
        }
    }
}

