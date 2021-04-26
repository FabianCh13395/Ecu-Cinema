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
public class ControlReporteAdministrador {

    private ModeloAdministrador admi;
    private Vista_ReporteAdministradores vistaA;

    public ControlReporteAdministrador(ModeloAdministrador admi, Vista_ReporteAdministradores vistaA) {
        this.admi = admi;
        this.vistaA = vistaA;
        vistaA.setVisible(true);
        InicioControl();
        transparentarBotones();
    }

    private void InicioControl() {
        llenarTablaAdmi();
        
        vistaA.getBtn_editar().addActionListener(l -> mostrarDialogo());
        vistaA.getBtnEditarAdmin().addActionListener(l -> editarVendedor());
    }

    private void llenarTablaAdmi() {
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
            vistaA.getTablaAdmi().setValueAt(p1.getEdad(), i.value, 5);
            vistaA.getTablaAdmi().setValueAt(p1.getCorreo(), i.value, 4);

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

    public void editarVendedor() {

        int ind = vistaA.getTablaAdmi().getSelectedRow();
        String Cedula = vistaA.getTablaAdmi().getValueAt(ind, 0).toString();
        String Nombre = vistaA.getTxtName().getText();
        String Apellido = vistaA.getTxtlast_name().getText();
        String Telefono = vistaA.getTxtNumberPhone().getText();
        String Correo = vistaA.getTxtemail().getText();
        Instant instant = vistaA.getDtc_Date().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha_nacimiento = Date.valueOf(zdt.toLocalDate());
        //ModeloPersona persona = new ModeloPersona(idPersona, nombre, apellido, fechaNacimiento, telefono, sexo, sueldo, cupo);
        ModeloAdministrador persona = new ModeloAdministrador(Cedula, Nombre, Telefono, Apellido, Correo, fecha_nacimiento);
        if (persona.Editar() == true) {

            ImageIcon ic = (ImageIcon) vistaA.getLbl_foto().getIcon();
            persona.setFotoA(ic.getImage());
            if (persona.Editar()) {
                llenarTablaAdmi();
                vistaA.getDlg_editarAdmin().setVisible(false);
                JOptionPane.showMessageDialog(vistaA, "Registro grabado satisfactoriamente");
            } else {
                JOptionPane.showMessageDialog(vistaA, "ERROR");
            }

            vistaA.getDlg_editarAdmin().setVisible(false);
            JOptionPane.showMessageDialog(vistaA, "Registro actualizado exitosamente");
        }

    }

    public void mostrarDialogo() {
        vistaA.getDlg_editarAdmin().setSize(439, 400);
        vistaA.getDlg_editarAdmin().setTitle("Actualizar Informacion");
        vistaA.getDlg_editarAdmin().setLocationRelativeTo(vistaA);
        vistaA.getDlg_editarAdmin().setVisible(true);
    }

    public void transparentarBotones() {

        vistaA.getBtn_imprimir().setOpaque(false);

        vistaA.getBtn_imprimir().setContentAreaFilled(false);
        vistaA.getBtn_imprimir().setBorderPainted(false);
        //----//
        vistaA.getBtn_editar().setOpaque(false);
        vistaA.getBtn_editar().setContentAreaFilled(false);
        vistaA.getBtn_editar().setBorderPainted(false);

        //------------//
        vistaA.getBtn_buscar().setOpaque(false);
        vistaA.getBtn_buscar().setContentAreaFilled(false);
        vistaA.getBtn_buscar().setBorderPainted(false);
        //-----------------------------------------------//
        vistaA.getBtn_editar().setOpaque(false);
        vistaA.getBtn_editar().setContentAreaFilled(false);
        vistaA.getBtn_editar().setBorderPainted(false);

    }

}
