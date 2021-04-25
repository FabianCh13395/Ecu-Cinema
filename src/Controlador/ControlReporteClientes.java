/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ModeloCliente;
import Modelo.ModeloVendedor;
import Modelo.Vendedor;
import Vistas.Vista_ReportesClientes;
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
public class ControlReporteClientes {
    private ModeloCliente cliente;
    private Vista_ReportesClientes  vistare;

    public ControlReporteClientes(ModeloCliente cliente, Vista_ReportesClientes vistare) {
        this.cliente = cliente;
        this.vistare = vistare;
       vistare.setVisible(true);
       InicioControl();
       
    }
    private void InicioControl(){
        llenarTabla();
        vistare.getBtn_salir().addActionListener(l->vistare.dispose());
    }
     private void llenarTabla(){
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vistare.getTabla_Cliente().getModel();
        tblModel.setNumRows(0);
        int ncol = tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);

        List<Cliente> lista = new ModeloCliente().ListarClientes();
        lista.stream().forEach(p1 -> {
            //   String[] persona={p1.getIdPersona(),p1.getNombres(),p1.getApellidos(),String.valueOf(p1.getEdad())};

            tblModel.addRow(new Object[ncol]);
            vistare.getTabla_Cliente().setValueAt(p1.getCedula(), i.value, 0);
            vistare.getTabla_Cliente().setValueAt(p1.getNombre(), i.value, 1);
            vistare.getTabla_Cliente().setValueAt(p1.getApellido(), i.value, 2);
            vistare.getTabla_Cliente().setValueAt(p1.getTelefono(), i.value, 3);
            vistare.getTabla_Cliente().setValueAt(p1.getCorreo(), i.value, 4);
            vistare.getTabla_Cliente().setValueAt(p1.getFecha_nacimiento(), i.value, 5);         
            i.value++;
            
        });
    }
}
