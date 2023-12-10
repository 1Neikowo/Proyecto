package Guis;

import Modelo.AIV;

import javax.swing.*;
import java.awt.event.*;

public class VentanaEliminar extends VentanaBase {
    private JTextField especieTextField;
    private JTextField idTextField;
    private JButton btAceptar;
    private AIV aiv;
    private JButton btVolver;

    public VentanaEliminar(AIV aiv) {
        super("Eliminar Planta", 500, 520);
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
                }
            }
        });
    }

    public void generarElementosVentana() {
        generarEncabezado();
        generarEspecieTextField();
        generarIdTextField();
        generarBotonAceptar();
        generarBotonVolver();
    }

    public void generarEncabezado() {
        String encabezado = "Eliminar Planta";
        super.generarJLabelEncabezado(encabezado, 150, 20, 300, 50);
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
        if (event.getSource() == btVolver) {
            new VentanaMenuPrincipal(aiv);
            this.dispose();
        }
        if (event.getSource() == btAceptar) {
            procesarAceptar();
        }
    }
    private void procesarAceptar() {
        if (!validacionCampos()) {
            eliminarPlanta();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void eliminarPlanta() {
        String especie = especieTextField.getText();
        int id = Integer.parseInt(idTextField.getText());
        if (aiv.existeplanta(especie, id)) {
            aiv.eliminarPlanta(especie, id);
            JOptionPane.showMessageDialog(this, "La planta ha sido eliminada", "Planta eliminada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "La planta no existe", "Planta no encontrada", JOptionPane.WARNING_MESSAGE);
        }
        limpiarCampos();
    }
    private void limpiarCampos() {
        especieTextField.setText("");
        idTextField.setText("");
    }

    public boolean validacionCampos() {
        return especieTextField.getText().isEmpty() || idTextField.getText().isEmpty();
    }
}
