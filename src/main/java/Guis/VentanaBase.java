package Guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaBase extends JFrame implements ActionListener{
    private final Font fuenteTitulo;
    private final Font fuenteTexto;
    protected final Color colorBoton = new Color(1,93,82);

    public VentanaBase(String nombre, int largoX, int largoY){
        super(nombre);
        super.setVisible(true);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(largoX, largoY);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        this.setLayout(null);
        this.fuenteTitulo = new Font("Calibri", Font.BOLD | Font.ITALIC, 30);
        this.fuenteTexto = new Font("Calibri", Font.BOLD, 14);
    }

    public JButton generarBotonPrincipal(String text, int locationX, int locationY, int largoX, int largoY) {
        JButton boton = new JButton(text);
        boton.setBounds(locationX, locationY, largoX, largoY);
        boton.setFocusPainted(false);
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorBoton);
        boton.setFont(fuenteTexto);
        return boton;
    }
    protected void generarJLabelEncabezado(String texto, int posicionX, int posicionY, int largoX, int largoY) {
        JLabel label = new JLabel(texto);
        label.setBounds(posicionX, posicionY, largoX, largoY);
        label.setFont(fuenteTitulo);
        this.add(label);
    }
    public void generarJLabel(String text, int locationX, int locationY, int largoX, int largoY) {
        JLabel label = new JLabel(text);
        label.setBounds(locationX, locationY, largoX, largoY);
        label.setFont(fuenteTexto);
        this.add(label);
    }
    public JTextField generarTextField(int locationX, int locationY, int largoX, int largoY) {
        JTextField textField = new JTextField();
        textField.setBounds(locationX, locationY, largoX, largoY);
        return textField;
    }
    public JPasswordField generarJPasswordField(int locationX, int locationY, int largoX, int largoY) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(locationX, locationY, largoX, largoY);
        return passwordField;
    }
    protected JComboBox generarListaDesplegable(Object[] datosLista, int posicionX, int posicionY, int largoX, int largoY) {
        JComboBox lista = new JComboBox(datosLista);
        lista.setBounds(posicionX, posicionY, largoX, largoY);
        return lista;
    }

    public void actionPerformed(ActionEvent e){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
