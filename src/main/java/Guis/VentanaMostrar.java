package Guis;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Guis.VentanaBase;
import Guis.VentanaMenuPrincipal;
import Modelo.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaMostrar extends VentanaBase {
    private JTable table;
    private JButton btVolver;
    private AIV aiv;
    private JPanel panel1;

    public VentanaMostrar(AIV aiv) {
        super("Plantas Almacenadas", 500, 520);
        this.aiv = aiv;
        generarElementosVentana();
    }

    public void generarElementosVentana() {
        generarEncabezado();
        generarBotonVolver();
        generarTabla();
    }

    public void generarEncabezado() {
        String encabezado = "Mostrar Plantas";
        super.generarJLabelEncabezado(encabezado, 150, 20, 300, 50);
    }

    public void generarTabla() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Clasificacion", "Tamaño", "Ambiente", "Precio", "Cantidad"});
        ArrayList<Planta> plantas = aiv.obtenerListaDePlantas();
        for (Planta planta : plantas) {
            model.addRow(new Object[]{planta.getId(), planta.getNombre(),planta.getClasificacion(), planta.getTamano(), planta.getAmbiente(), planta.getPrecio(), planta.getCantidad()});
        }
        // Creamos la tabla después de haber creado el modelo
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(450, 63));
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        // Actualizamos la interfaz de usuario para mostrar la nueva tabla
        getContentPane().revalidate();
        getContentPane().repaint();
    }


    public void generarBotonVolver() {
        btVolver = generarBotonPrincipal("Volver", 175, 400, 150, 35);
        this.add(btVolver);
        btVolver.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btVolver) {
            new VentanaMenuPrincipal(aiv);
            this.dispose();
        }
    }

}