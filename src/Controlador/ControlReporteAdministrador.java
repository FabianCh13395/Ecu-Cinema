/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Administrador;
import Modelo.ModeloAdministrador;
import Modelo.ModeloVendedor;
import Modelo.Vendedor;
import Vistas.Vista_ReporteAdministradores;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;


/**
 *
 * @author FabianCh
 */
public class ControlReporteAdministrador {
    private ModeloAdministrador admi;
    private Vista_ReporteAdministradores vistaA;

    public ControlReporteAdministrador(ModeloAdministrador admi, Vista_ReporteAdministradores vistaA) {
        this.admi = admi;
        this.vistaA = vistaA;
    }
    
    private void InicioControl(){
        
    }
     private void llenarTablaAdmi(){
        vistaA.getTablaAdmi().setDefaultRenderer(Object.class, new ImagenTabla());
        vistaA.getTablaAdmi().setRowHeight(100);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vistaA.getTablaAdmi().getModel();
        tblModel.setNumRows(0);
        int ncol = tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);

        List<Administrador> lista = new ModeloAdministrador().ListarAdministradores();
        lista.stream().forEach(p1 -> {
            //   String[] persona={p1.getIdPersona(),p1.getNombres(),p1.getApellidos(),String.valueOf(p1.getEdad())};

            tblModel.addRow(new Object[ncol]);
            vistaA.getTablaAdmi().setValueAt(p1.getCedula(), i.value, 0);
            vistaA.getTablaAdmi().setValueAt(p1.getNombre(), i.value, 1);
            vistaA.getTablaAdmi().setValueAt(p1.getApellido(), i.value, 2);
            vistaA.getTablaAdmi().setValueAt(p1.getTelefono(), i.value, 3);
            vistaA.getTablaAdmi().setValueAt(p1.getEdad(), i.value, 4);
            vistaA.getTablaAdmi().setValueAt(p1.getCorreo(), i.value, 5);
           

            java.awt.Image img = p1.getFotoA();

            if (img != null) {
                java.awt.Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newimg);
                renderer.setIcon(icon);
                vistaA.getTablaAdmi().setValueAt(new JLabel(icon), i.value, 6);
            } else {
                vistaA.getTablaAdmi().setValueAt(null, i.value, 6);
            }
            i.value++;
            
        });
    }
}
