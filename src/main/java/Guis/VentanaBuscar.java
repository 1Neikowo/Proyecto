package Guis;

import Modelo.AIV;
import Modelo.Planta;
import javax.swing.*;
import java.awt.event.*;

public class VentanaBuscar extends VentanaBase {
    private JTextField especieTextField;
    private JTextField idTextField;
    private JButton btAceptar;
    private JButton btVolver;
    private AIV aiv;

    public VentanaBuscar(AIV aiv) {
        super("Buscar Planta", 500, 520);
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
    public void generarElementosVentana() {
        generarEncabezado();
        generarEspecieTextField();
        generarIdTextField();
        generarBotonAceptar();
        generarBotonVolver();
    }

    public void generarEncabezado() {
        String encabezado = "Buscar Planta";
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
            mostrarInformacionPlanta();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void mostrarInformacionPlanta() {
        String especie = especieTextField.getText().toLowerCase();
        int id = Integer.parseInt(idTextField.getText());

        if (aiv.existeplanta(especie, id)) {
            mostrarInformacionPlantaEncontrada(especie, id);
        } else {
            JOptionPane.showMessageDialog(this, "La planta no existe", "Planta no encontrada", JOptionPane.WARNING_MESSAGE);
            limpiarCampos();
        }
    }
    private void mostrarInformacionPlantaEncontrada(String especie, int id) {
        AIV aiv = new AIV();
        Planta plantaHallada = aiv.buscarPlanta(especie, id);
        JOptionPane.showMessageDialog(this, plantaHallada.mostrar(), "Informacion de planta encontrada", JOptionPane.INFORMATION_MESSAGE);
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