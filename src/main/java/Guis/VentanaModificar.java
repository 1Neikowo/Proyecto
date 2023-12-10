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
        agregarListenerCerrarVentana();
    }
    private void agregarListenerCerrarVentana(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "¡Nos vemos, vuelve pronto!");
                    System.exit(0);
                }else{
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }
    private void generarElementosVentana(){
        generarEncabezado();
        generarEspecieTextField();
        generarCantidadTextField();
        generarIdTextField();
        generarBotonAceptar();
        generarBotonVolver();
    }
    private void generarEncabezado() {
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
        agregarKeyListener(idTextField);
        this.add(idTextField);
        idTextField.addActionListener(this);
    }
    private void generarCantidadTextField() {
        String textoNombre = "Cantidad:";
        super.generarJLabel(textoNombre, 20, 200, 150, 20);
        cantidadTextField = super.generarTextField(200, 200, 250, 20);
        agregarKeyListener(cantidadTextField);
        this.add(cantidadTextField);
        cantidadTextField.addActionListener(this);

    }
    private void agregarKeyListener(JTextField jTextField) {
        jTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validarEntradaTeclado(e,jTextField);
            }
        });
    }
    private void validarEntradaTeclado(KeyEvent e, JTextField jTextField) {
        char caracter = e.getKeyChar();
        if (!Character.isDigit(caracter) || (caracter == '0' && jTextField.getText().isEmpty())) {
            e.consume();
        }
    }

    private void generarBotonVolver() {
        btVolver = generarBotonPrincipal("Volver", 100, 430, 100, 30);
        this.add(btVolver);
        btVolver.addActionListener(this);
    }
    private void generarBotonAceptar() {
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
            procesarAceptar();
        }
    }
    private void procesarAceptar() {
        if (validacionCampos()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        } else {
            procesarAceptarResultado();
        }
    }
    private boolean validacionCampos(){
        return especieTextField.getText().isEmpty() || idTextField.getText().isEmpty() || cantidadTextField.getText().isEmpty();

    }
    private void procesarAceptarResultado() {
        if (!aiv.existeplanta(especieTextField.getText(), Integer.parseInt(idTextField.getText()))) {
            JOptionPane.showMessageDialog(this,"La planta buscada no existe","Planta no hallada",JOptionPane.WARNING_MESSAGE);
        } else {
            modificarCantidadPlanta();
        }
    }
    private void modificarCantidadPlanta() {
        AIV aiv = new AIV();
        aiv.modificarCantidadPlanta(
                especieTextField.getText(),
                Integer.parseInt(idTextField.getText()),
                Integer.parseInt(cantidadTextField.getText())
        );
        JOptionPane.showMessageDialog(this,"Se ha modificado la cantidad exitosamente","Cambio de cantidad",JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }
    private void limpiarCampos() {
        especieTextField.setText("");
        idTextField.setText("");
        cantidadTextField.setText("");
    }

}
