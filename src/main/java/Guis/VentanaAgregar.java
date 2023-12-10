package Guis;
import Modelo.AIV;
import Modelo.Planta;
import Utils.Ambiente;
import Utils.Clasificaciones;
import Utils.Tamaño;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class VentanaAgregar extends VentanaBase {
    private JTextField especieTextField;
    private JTextField precioTextField;
    private JTextField cantidadTextField;
    private JComboBox listaClasificaciones;
    private JComboBox listaTamanos;
    private JComboBox listaAmbientes;
    private JButton btVolver;
    private JButton btAceptar;
    private AIV aiv;


    public VentanaAgregar(AIV aiv) {
        super("Agregar Plantas", 500, 520);
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

    public void generarElementosVentana() {
        generarEncabezado();
        generarEspecieTextField();
        generarPrecioTextField();
        generarCantidadTextField();
        generarListaClasificaciones();
        generarListaTamanos();
        generarListaAmbientes();
        generarBotonVolver();
        generarBotonAceptar();
    }

    public void generarEncabezado() {
        String encabezado = "Agregar Planta";
        super.generarJLabelEncabezado(encabezado, 150, 20, 300, 50);
    }

    private void generarEspecieTextField() {
        String textoNombre = "Especie:";
        super.generarJLabel(textoNombre, 20, 100, 150, 20);
        especieTextField = super.generarTextField(200, 100, 250, 20);
        this.add(especieTextField);
    }

    private void generarPrecioTextField() {
        String textoNombre = "Precio:";
        super.generarJLabel(textoNombre, 20, 150, 150, 20);
        precioTextField = super.generarTextField(200, 150, 250, 20);
        agregarKeyListener(precioTextField);
        this.add(precioTextField);
    }

    private void generarCantidadTextField() {
        String textoNombre = "Cantidad:";
        super.generarJLabel(textoNombre, 20, 200, 150, 20);
        cantidadTextField = super.generarTextField(200, 200, 250, 20);
        agregarKeyListener(cantidadTextField);
        this.add(cantidadTextField);

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
    public void generarListaTamanos() {
        super.generarJLabel("Tamaño:", 20, 250, 150, 20);
        listaTamanos = super.generarListaDesplegable(Tamaño.values(), 200, 250, 250, 20);
        listaTamanos.setSelectedIndex(-1);
        listaTamanos.addActionListener(this);
        this.add(listaTamanos);
    }

    public void generarListaAmbientes() {
        super.generarJLabel("Ambiente:", 20, 300, 150, 20);
        listaAmbientes = super.generarListaDesplegable(Ambiente.values(), 200, 300, 250, 20);
        listaAmbientes.setSelectedIndex(-1);
        listaAmbientes.addActionListener(this);
        this.add(listaAmbientes);
    }

    public void generarListaClasificaciones() {
        super.generarJLabel("Clasificación:", 20, 350, 150, 20);
        listaClasificaciones = super.generarListaDesplegable(Clasificaciones.values(), 200, 350, 250, 20);
        listaClasificaciones.setSelectedIndex(-1);
        listaClasificaciones.addActionListener(this);
        this.add(listaClasificaciones);
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

    public Planta crearPlanta() {
        String especie = (especieTextField.getText()).toLowerCase();
        String clasificacion = listaClasificaciones.getSelectedItem().toString();
        String tamano = listaTamanos.getSelectedItem().toString();
        String ambiente = listaAmbientes.getSelectedItem().toString();
        int precio = Integer.parseInt(precioTextField.getText());
        int cantidad = Integer.parseInt(cantidadTextField.getText());
        int id = Integer.parseInt(aiv.leerUltimoIDArchivo()) + 1;
        return new Planta(id, especie, clasificacion, tamano, ambiente, precio, cantidad);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == listaAmbientes) {
            listaAmbientes.setPopupVisible(false);
        }
        if (event.getSource() == listaClasificaciones) {
            listaClasificaciones.setPopupVisible(false);
        }
        if (event.getSource() == listaTamanos) {
            listaTamanos.setPopupVisible(false);
        }
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
            procesarAgregarNuevaPlanta();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void procesarAgregarNuevaPlanta(){
        String especie = (especieTextField.getText().toLowerCase());
        if (aiv.existePlantaNombre(especie)) {
            JOptionPane.showMessageDialog(this, "La planta ya existe", "Planta existente", JOptionPane.WARNING_MESSAGE);
        }else {
            agregarNuevaPlanta();
        }
    }
    private void agregarNuevaPlanta() {
        Planta planta = crearPlanta();
        aiv.agregarPlantaNueva(planta);
        aiv.guardarUltimoID();
        JOptionPane.showMessageDialog(this, "Planta agregada correctamente");
        limpiarCampos();
    }
    private void limpiarCampos() {
        cantidadTextField.setText("");
        precioTextField.setText("");
        especieTextField.setText("");
        listaAmbientes.setSelectedItem(null);
        listaClasificaciones.setSelectedItem(null);
        listaTamanos.setSelectedItem(null);
    }

    public boolean validacionCampos() {

        return especieTextField.getText().isEmpty() || precioTextField.getText().isEmpty() || cantidadTextField.getText().isEmpty()
                || listaAmbientes.getSelectedItem() == null || listaTamanos == null || listaClasificaciones == null;

    }
}
