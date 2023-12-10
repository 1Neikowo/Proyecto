package Guis;
import Datos.GestorAdminArchivo;
import Modelo.AIV;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaMenuPrincipal extends VentanaBase {
    private JButton btAgregar;
    private JButton btEliminar;
    private JButton btBuscar;
    private JButton btMostrar;
    private JButton btModificar;
    private JButton btVolver;
    private JButton btCambiarPass;
    private AIV aiv;


    public VentanaMenuPrincipal(AIV aiv) {
        super("Menú", 500, 650);
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

    private void generarElementosVentana() {
        generarEncabezado();
        generarBotonAgregar();
        generarBotonEliminar();
        generarBotonModificar();
        generarBotonBuscar();
        generarBotonMostrar();
        generarBotonVolver();
        generarBotonCambiarPass();
    }

    public void generarEncabezado() {
        String encabezado = "¿Qué deseas realizar?";
        super.generarJLabelEncabezado(encabezado, 100, 20, 300, 50);
    }

    public void generarBotonAgregar() {
        btAgregar = generarBotonPrincipal("Agregar Plantas", 175, 90, 150, 35);
        this.add(btAgregar);
        btAgregar.addActionListener(this);
    }

    public void generarBotonEliminar() {
        btEliminar = generarBotonPrincipal("Eliminar Plantas", 175, 155, 150, 35);
        this.add(btEliminar);
        btEliminar.addActionListener(this);
    }

    public void generarBotonModificar() {
        btModificar = generarBotonPrincipal("Modificar Cantidad", 175, 220, 150, 35);
        this.add(btModificar);
        btModificar.addActionListener(this);
    }

    public void generarBotonBuscar() {
        btBuscar = generarBotonPrincipal("Buscar Plantas", 175, 285, 150, 35);

        this.add(btBuscar);
        btBuscar.addActionListener(this);
    }

    public void generarBotonMostrar() {
        btMostrar = generarBotonPrincipal("Mostrar Inventario", 175, 350, 150, 35);
        this.add(btMostrar);
        btMostrar.addActionListener(this);
    }
    public void generarBotonCambiarPass(){
        btCambiarPass = generarBotonPrincipal("Cambiar Contraseña", 175, 415, 150, 35);
        this.add(btCambiarPass);
        btCambiarPass.addActionListener(this);
    }

    public void generarBotonVolver() {
        btVolver = generarBotonPrincipal("Volver", 200, 500, 100, 30);
        this.add(btVolver);
        btVolver.addActionListener(this);
    }



    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == btAgregar) {
            new VentanaAgregar(aiv);
            this.dispose();
        }

        if (event.getSource() == btEliminar) {
            new VentanaEliminar(aiv);
            this.dispose();
        }

        if (event.getSource() == btModificar) {
            new VentanaModificar(aiv);
            this.dispose();
        }

        if (event.getSource() == btBuscar) {
            new VentanaBuscar(aiv);
            this.dispose();
        }

        if (event.getSource() == btMostrar) {
            new VentanaMostrar(aiv);
            this.dispose();
        }

        if (event.getSource() == btVolver) {
            new VentanaLogin(aiv);
            this.dispose();
        }

        if (event.getSource() == btCambiarPass){
            procesarCambiarPass();
        }
    }
    private void procesarCambiarPass(){
        String nuevapassword = JOptionPane.showInputDialog(this, "Ingrese la nueva contraseña", "Cambio de contraseña", JOptionPane.PLAIN_MESSAGE);
        if(validarPass(nuevapassword)){
            cambiarPass(nuevapassword);
        }else{
            JOptionPane.showMessageDialog(this, "No has ingresado nada ", "Contraseña no cambiada", JOptionPane.WARNING_MESSAGE);
        }
    }
    private boolean validarPass(String nuevapassword){
        return nuevapassword != null && !nuevapassword.isEmpty();
    }
    private void cambiarPass(String nuevapassword){
        GestorAdminArchivo gestor = new GestorAdminArchivo();
        gestor.getAdmin().setPassword(nuevapassword);
        gestor.guardarCambios();
        JOptionPane.showMessageDialog(this, "La contraseña se ha cambiado con éxito", "Contraseña cambiada", JOptionPane.INFORMATION_MESSAGE);
    }

}
