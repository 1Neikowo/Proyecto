package Guis;

import Modelo.AIV;

import javax.swing.*;
import java.awt.event.*;

public class VentanaModificar extends VentanaBase{
    private JTextField especieTextField;
    private JTextField cantidadTextField;
    private JTextField idTextField;
    private JButton btAceptar;
    private JButton btVolver;
    private AIV aiv;
    public VentanaModificar(AIV aiv){
        super("Modificar Cantidad Planta",500,520);
        this.aiv = aiv;
        generarElementosVentana();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "¡Nos vemos, vuelve pronto!");
                    System.exit(0);
                }else{
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }
    public void generarElementosVentana(){
        generarEncabezado();
        generarEspecieTextField();
        generarCantidadTextField();
        generarIdTextField();
        generarBotonAceptar();
        generarBotonVolver();
    }
    public void generarEncabezado() {
        String encabezado = "Modificar Cantidad";
        super.generarJLabelEncabezado(encabezado,150,20,300,50);
    }
    private void generarEspecieTextField() {
        String textoNombre = "Especie:";
        super.generarJLabel(textoNombre, 20, 100, 150, 20);
        especieTextField = super.generarTextField(200, 100, 250, 20);
        this.add(especieTextField);
    }
    private void generarIdTextField() {
        String textoNombre = "ID:";
        super.generarJLabel(textoNombre, 20, 150, 150, 20);
        idTextField = super.generarTextField(200, 150, 250, 20);
        idTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (!Character.isDigit(caracter)) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.add(idTextField);
        idTextField.addActionListener(this);
    }
    private void generarCantidadTextField() {
        String textoNombre = "Cantidad:";
        super.generarJLabel(textoNombre, 20, 200, 150, 20);
        cantidadTextField = super.generarTextField(200, 200, 250, 20);
        cantidadTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (!Character.isDigit(caracter)) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.add(cantidadTextField);

    }

    public void generarBotonVolver() {
        btVolver = generarBotonPrincipal("Volver", 100, 430, 100, 30);
        this.add(btVolver);
        btVolver.addActionListener(this);
    }
    public void generarBotonAceptar() {
        btAceptar = generarBotonPrincipal("Aceptar", 290, 430, 100, 30);
        this.add(btAceptar);
        btAceptar.addActionListener(this);
    }
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == btVolver){
            new VentanaMenuPrincipal(aiv);
            this.dispose();
        }
        if(event.getSource() == btAceptar){
            if (especieTextField.getText().isEmpty() || idTextField.getText().isEmpty() || cantidadTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            }else{

            }
        }
    }
}
