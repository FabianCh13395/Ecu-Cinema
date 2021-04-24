/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloVendedor;
import Modelo.Vendedor;
import Vistas.Vista_ReporteVendedores;
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
public class ControlReporteVendedores {
    private ModeloVendedor vendedor;
    private Vista_ReporteVendedores vistav;

    public ControlReporteVendedores(ModeloVendedor vendedor, Vista_ReporteVendedores vistav) {
        this.vendedor = vendedor;
        this.vistav = vistav;
    }
    private void InicioControl(){
    
}
    private void llenarTabla(){
        vistav.getTabla_Vendedor().setDefaultRenderer(Object.class, new ImagenTabla());
        vistav.getTabla_Vendedor().setRowHeight(100);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vistav.getTabla_Vendedor().getModel();
        tblModel.setNumRows(0);
        int ncol = tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);

        List<Vendedor> lista = new ModeloVendedor().ListarVendedores();
        lista.stream().forEach(p1 -> {
            //   String[] persona={p1.getIdPersona(),p1.getNombres(),p1.getApellidos(),String.valueOf(p1.getEdad())};

            tblModel.addRow(new Object[ncol]);
            vistav.getTabla_Vendedor().setValueAt(p1.getCedula(), i.value, 0);
            vistav.getTabla_Vendedor().setValueAt(p1.getNombre(), i.value, 1);
            vistav.getTabla_Vendedor().setValueAt(p1.getApellido(), i.value, 2);
            vistav.getTabla_Vendedor().setValueAt(p1.getTelefono(), i.value, 3);
            vistav.getTabla_Vendedor().setValueAt(p1.getEdad(), i.value, 4);
            vistav.getTabla_Vendedor().setValueAt(p1.getCorreo(), i.value, 5);
           

            java.awt.Image img = p1.getFotoV();

            if (img != null) {
                java.awt.Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newimg);
                renderer.setIcon(icon);
                vistav.getTabla_Vendedor().setValueAt(new JLabel(icon), i.value, 6);
            } else {
                vistav.getTabla_Vendedor().setValueAt(null, i.value, 6);
            }
            i.value++;
            
        });
    }
}
