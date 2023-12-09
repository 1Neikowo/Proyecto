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
        super("Mostrar Plantas", 500, 520);
        this.aiv = aiv;
        generarElementosVentana();
    }

    public void generarElementosVentana() {
        generarEncabezado();
        generarTabla();
        generarBotonVolver();
    }

    public void generarEncabezado() {
        String encabezado = "Mostrar Plantas";
        super.generarJLabelEncabezado(encabezado, 150, 20, 300, 50);
    }

    public void generarTabla() {
       /* DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Clasificacion", "Tamaño", "Ambiente", "Precio", "Cantidad"});
        ArrayList<Planta> plantas = aiv.obtenerListaDePlantas();
        for (Planta planta : plantas) {
            model.addRow(new Object[]{planta.getId(), planta.getNombre(),planta.getClasificacion(), planta.getTamano(), planta.getAmbiente(), planta.getPrecio(), planta.getCantidad()});
        }
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(450, 63));
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);*/
        Object[][] datos = {
                {1, "Planta 1", "Clasificación A", "Mediano", "Interior", 15.99, 10},
                {2, "Planta 2", "Clasificación B", "Grande", "Exterior", 25.99, 5},
                {3, "Planta 3", "Clasificación C", "Pequeño", "Interior", 9.99, 20}
        };

        // Nombres de las columnas
        String[] columnas = {"ID", "Nombre", "Clasificación", "Tamaño", "Ambiente", "Precio", "Cantidad"};

        // Crear el modelo de la tabla con los datos y columnas
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);

        // Crear la JTable con el modelo
         table = new JTable(modelo);
        table.setPreferredScrollableViewportSize(new Dimension(600, 200)); // Tamaño preferido de la tabla

        // Crear un JScrollPane y agregar la tabla a él
        JScrollPane scrollPane = new JScrollPane(table);

        // Crear un JFrame para mostrar la tabla
        JFrame frame = new JFrame("Ejemplo JTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

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
