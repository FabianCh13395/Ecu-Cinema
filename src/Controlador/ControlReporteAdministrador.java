/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Administrador;
import Modelo.Conexion_Ecu_Cinema;
import Modelo.ModeloAdministrador;
import Modelo.ModeloVendedor;
import Modelo.Vendedor;
import Vistas.Vista_ReporteAdministradores;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
        llenarTablaAdmi("");
    }

    private void InicioControl() {

        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                llenarTablaAdmi(vistaA.getTxt_buscar().getText());
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        vistaA.getTxt_buscar().addKeyListener(kl);
        vistaA.getBtn_editar().addActionListener(l -> mostrarDialogo());
        vistaA.getBtnEditarAdmin().addActionListener(l -> editarVendedor());
        vistaA.getBtn_imprimir().addActionListener(l -> imprimirReporte());
    }

    private void llenarTablaAdmi(String aguja) {
        vistaA.getTablaAdmi().setDefaultRenderer(Object.class, new ImagenTabla());
        vistaA.getTablaAdmi().setRowHeight(100);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vistaA.getTablaAdmi().getModel();
        tblModel.setNumRows(0);
        int ncol = tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);

        List<Administrador> lista = new ModeloAdministrador().ListarAdministradores(aguja);
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
        String cedula = vistaA.getTablaAdmi().getValueAt(ind, 0).toString();
        String nombre = vistaA.getTxtName().getText();
        String apellido = vistaA.getTxtlast_name().getText();
        String telefono = vistaA.getTxtNumberPhone().getText();
        String correo = vistaA.getTxtemail().getText();
        Instant instant = vistaA.getDtc_Date().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha_nacimiento = Date.valueOf(zdt.toLocalDate());

        ModeloAdministrador usuario = new ModeloAdministrador(cedula, nombre, telefono, apellido, correo, fecha_nacimiento);
        if (usuario.grabarUsuario() == true) {

            System.out.println("Usuario registrado");
        } else {
            System.out.println("ERROR");

        }

        String contraseña = String.valueOf(vistaA.getJps_Contraseña().getPassword());
        Image foto = ((ImageIcon) vistaA.getLbl_foto().getIcon()).getImage();
        ModeloAdministrador administrador = new ModeloAdministrador();
        administrador.setCedula(cedula);
        administrador.setContraseña(contraseña);
        administrador.setFotoA(foto);
        if (administrador.grabarAdministtador() == true) {
            JOptionPane.showMessageDialog(vistaA, "Administrador Guardado exitosamente");
            vistaA.dispose();
        } else {
            JOptionPane.showMessageDialog(vistaA, "ERROR ");
        }

    }

    public boolean getInformacion() {
        int ind = vistaA.getTablaAdmi().getSelectedRow();
        if (ind != -1) {
            String cedula = vistaA.getTablaAdmi().getValueAt(ind, 0).toString();
            String nombre = vistaA.getTablaAdmi().getValueAt(ind, 1).toString();
            String apellido = vistaA.getTablaAdmi().getValueAt(ind, 2).toString();
            String telefono = vistaA.getTablaAdmi().getValueAt(ind, 3).toString();
            String fecha = vistaA.getTablaAdmi().getValueAt(ind, 4).toString();
            String correo = vistaA.getTablaAdmi().getValueAt(ind, 5).toString();
            //String foto = vistaA.getTablaAdmi().getValueAt(ind, 6).toString();

            ModeloAdministrador p1 = new ModeloAdministrador();
            p1.setCedula(cedula);
            vistaA.getTxtName().setText(nombre);
            vistaA.getTxtlast_name().setText(apellido);
            vistaA.getTxtNumberPhone().setText(telefono);
            vistaA.getTxtemail().setText(correo);
            vistaA.getDtc_Date().setDateFormatString(fecha);

            vistaA.getTxtCedula().setEditable(false);
            return true;

        } else {
            JOptionPane.showMessageDialog(vistaA, "USTED NO HA SELECCIONADO UNA FILA");
            return false;
        }

    }

    public void mostrarDialogo() {
        if (getInformacion() == true) {

            vistaA.getDlg_editarAdmin().setSize(439, 400);
            vistaA.getDlg_editarAdmin().setTitle("Actualizar Informacion");
            vistaA.getDlg_editarAdmin().setLocationRelativeTo(vistaA);

            vistaA.getDlg_editarAdmin().setVisible(true);
            getInformacion();

        } else {
            JOptionPane.showMessageDialog(vistaA, "Error");

        }

    }

    public void imprimirReporte() {

        try {
            Conexion_Ecu_Cinema con = new Conexion_Ecu_Cinema();

            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/Vista_Reportes/rptAdministradores.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControlReporteClientes.class.getName()).log(Level.SEVERE, null, ex);
        }

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
