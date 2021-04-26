/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloAdministrador;
import Modelo.ModeloCliente;
import Modelo.ModeloVendedor;
import Modelo.Vendedor;
import Vistas.Vista_ReporteVendedores;
import java.awt.Image;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        vistav.setVisible(true);
        InicioControl();
        eliminarFondoButton();
    }

    private void InicioControl() {
        llenarTabla();
       
        vistav.getBtn_Editar().addActionListener(l -> editarVendedor());
        //vistav.getBtnEditarVendedor().addActionListener(l -> editarVendedor());

    }

    private void llenarTabla() {
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

    public void editarVendedor() {
        mostrarDialogo();

        int ind = vistav.getTabla_Vendedor().getSelectedRow();
        String Cedula = vistav.getTabla_Vendedor().getValueAt(ind, 0).toString();
        String Nombre = vistav.getTxtName().getText();
        String Apellido = vistav.getTxtlast_name().getText();
        String Telefono = vistav.getTxtNumberPhone().getText();
        String Correo = vistav.getTxtemail().getText();
        Instant instant = vistav.getDtc_Date().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha_nacimiento = Date.valueOf(zdt.toLocalDate());
        ModeloVendedor persona = new ModeloVendedor(Cedula, Nombre, Telefono, Apellido, Correo, fecha_nacimiento);
        if (persona.editarUsuario() == true) {

            if (persona.editarUsuario()) {
                llenarTabla();
                vistav.getDlg_editarVendedor().setVisible(false);
                JOptionPane.showMessageDialog(vistav, "Registro grabado satisfactoriamente");
            } else {
                JOptionPane.showMessageDialog(vistav, "ERROR");
            }

        }

        String contraseña = String.valueOf(vistav.getJpasw_vendedor1().getPassword());
        Image foto = ((ImageIcon) vistav.getLbl_foto().getIcon()).getImage();
        ModeloVendedor Vende = new ModeloVendedor();
        Vende.setCedula(Cedula);
        Vende.setContraseñaV(contraseña);
        Vende.setFotoV(foto);
        if (Vende.grabarVendedor() == true) {
            JOptionPane.showMessageDialog(vistav, "Vendedor Actualizado exitosamente");
            vistav.dispose();
        } else {
            JOptionPane.showMessageDialog(vistav, "ERROR ");
        }

    }

    
    public boolean getInformacion(){
    
     int ind = vistav.getTabla_Vendedor().getSelectedRow();
        if (ind != -1) {
            String cedula = vistav.getTabla_Vendedor().getValueAt(ind, 0).toString();
            String nombre = vistav.getTabla_Vendedor().getValueAt(ind, 1).toString();
            String apellido = vistav.getTabla_Vendedor().getValueAt(ind, 2).toString();
            String telefono = vistav.getTabla_Vendedor().getValueAt(ind, 3).toString();
            String fecha = vistav.getTabla_Vendedor().getValueAt(ind, 4).toString();
            String correo = vistav.getTabla_Vendedor().getValueAt(ind, 5).toString();
            //String foto = vistaA.getTablaAdmi().getValueAt(ind, 6).toString();

            ModeloCliente p1 = new ModeloCliente();
            p1.setCedula(cedula);
            vistav.getTxtName().setText(cedula);
            vistav.getTxtName().setText(nombre);
            vistav.getTxtlast_name().setText(apellido);
            vistav.getTxtNumberPhone().setText(telefono);
            vistav.getTxtemail().setText(correo);
            vistav.getDtc_Date().setDateFormatString(fecha);

            vistav.getTxtCedula().setEditable(false);
            return true;

        } else {
            JOptionPane.showMessageDialog(vistav, "USTED NO HA SELECCIONADO UNA FILA");
            return false;
        }
    
    
    }
    
    
    public void mostrarDialogo() {
        if (getInformacion()==true) {
            vistav.getDlg_editarVendedor().setSize(439, 400);
            vistav.getDlg_editarVendedor().setTitle("Actualizar Informacion");
            vistav.getDlg_editarVendedor().setLocationRelativeTo(vistav);
            vistav.getDlg_editarVendedor().setVisible(true);
        }else{
        
        }
        
    }

    public void eliminarFondoButton() {
        vistav.getBtn_imprimir().setOpaque(false);

        vistav.getBtn_imprimir().setContentAreaFilled(false);
        vistav.getBtn_imprimir().setBorderPainted(false);
        //----//
        vistav.getBtn_Editar().setOpaque(false);
        vistav.getBtn_Editar().setContentAreaFilled(false);
        vistav.getBtn_Editar().setBorderPainted(false);

        //------------//
        vistav.getBtn_buscar().setOpaque(false);
        vistav.getBtn_buscar().setContentAreaFilled(false);
        vistav.getBtn_buscar().setBorderPainted(false);
        //-------//
        vistav.getBtn_Eliminar().setOpaque(false);
        vistav.getBtn_Eliminar().setContentAreaFilled(false);
        vistav.getBtn_Eliminar().setBorderPainted(false);

    }

}
