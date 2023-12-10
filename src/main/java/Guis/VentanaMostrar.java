package Guis;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Guis.VentanaBase;
import Guis.VentanaMenuPrincipal;
import Modelo.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaMostrar extends VentanaBase {
    private JTable table;
    private JButton btVolver;
    private AIV aiv;
    private JPanel panel1;

    public VentanaMostrar(AIV aiv) {
        super("Plantas Almacenadas", 500, 520);
        this.aiv = aiv;
        this.setLayout(new BorderLayout());
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
        generarBotonVolver();
        generarTabla();
    }

    public void generarTabla() {
        DefaultTableModel model = crearModeloTabla();
        llenarModeloConPlantas(model);
        crearYConfigurarTabla(model);
    }

    private DefaultTableModel crearModeloTabla() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Clasificacion", "Tamaño", "Ambiente", "Precio", "Cantidad"});
        return model;
    }

    private void llenarModeloConPlantas(DefaultTableModel model) {
        aiv.obtenerPlantas();
        ArrayList<Planta> plantas = aiv.obtenerListaDePlantas();
        for (Planta planta : plantas) {
            model.addRow(new Object[]{planta.getId(), planta.getNombre(), planta.getClasificacion(), planta.getTamano(), planta.getAmbiente(), planta.getPrecio(), planta.getCantidad()});
        }
    }

    private void crearYConfigurarTabla(DefaultTableModel model) {
        JTable nuevaTabla = new JTable(model);
        nuevaTabla.setPreferredScrollableViewportSize(new Dimension(450, 63));
        JScrollPane scrollPane = new JScrollPane(nuevaTabla);

        actualizarInterfazUsuario(scrollPane);
    }

    private void actualizarInterfazUsuario(JScrollPane scrollPane) {
        getContentPane().add(scrollPane, BorderLayout.CENTER);
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
