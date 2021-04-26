/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ModeloAdministrador;
import Modelo.ModeloCliente;
import Modelo.ModeloVendedor;
import Modelo.Vendedor;
import Vistas.Vista_ReportesClientes;
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
public class ControlReporteClientes {

    private ModeloCliente cliente;
    private Vista_ReportesClientes vistare;

    public ControlReporteClientes(ModeloCliente cliente, Vista_ReportesClientes vistare) {
        this.cliente = cliente;
        this.vistare = vistare;
        vistare.setVisible(true);
        InicioControl();
        transparentarBotones();

    }

    private void InicioControl() {
        llenarTabla();

        vistare.getBtn_Actualizar().addActionListener(l -> mostrarDialogo());
        vistare.getBtnEditarClient().addActionListener(l -> editarCliente());

    }

    private void llenarTabla() {
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

    public void editarCliente() {

        int ind = vistare.getTabla_Cliente().getSelectedRow();
        String Cedula = vistare.getTabla_Cliente().getValueAt(ind, 0).toString();
        String Nombre = vistare.getTxtName().getText();
        String Apellido = vistare.getTxtlast_name().getText();
        String Telefono = vistare.getTxtNumberPhone().getText();
        String Correo = vistare.getTxtemail().getText();
        Instant instant = vistare.getDtc_Date().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha_nacimiento = Date.valueOf(zdt.toLocalDate());
        //ModeloPersona persona = new ModeloPersona(idPersona, nombre, apellido, fechaNacimiento, telefono, sexo, sueldo, cupo);
        ModeloCliente persona = new ModeloCliente(Cedula, Nombre, Telefono, Apellido, Correo, fecha_nacimiento);
        if (persona.actualizarUsuario() == true) {

            System.out.println("Usuario registrado");
        } else {
            System.out.println("ERROR");

        }

        ModeloCliente cliente = new ModeloCliente();
        cliente.setCedula(Cedula);

        if (cliente.grabarCliente() == true) {
            JOptionPane.showMessageDialog(vistare, "Cliete Guardado exitosamente");
            vistare.dispose();
        } else {
            JOptionPane.showMessageDialog(vistare, "ERROR ");
        }

    }

    public boolean getInformacion() {
        int ind = vistare.getTabla_Cliente().getSelectedRow();
        if (ind != -1) {
            String cedula = vistare.getTabla_Cliente().getValueAt(ind, 0).toString();
            String nombre = vistare.getTabla_Cliente().getValueAt(ind, 1).toString();
            String apellido = vistare.getTabla_Cliente().getValueAt(ind, 2).toString();
            String telefono = vistare.getTabla_Cliente().getValueAt(ind, 3).toString();
            String fecha = vistare.getTabla_Cliente().getValueAt(ind, 4).toString();
            String correo = vistare.getTabla_Cliente().getValueAt(ind, 5).toString();
            //String foto = vistaA.getTablaAdmi().getValueAt(ind, 6).toString();

            ModeloCliente p1 = new ModeloCliente();
            p1.setCedula(cedula);
            vistare.getTxtcedula().setText(cedula);
            vistare.getTxtName().setText(nombre);
            vistare.getTxtlast_name().setText(apellido);
            vistare.getTxtNumberPhone().setText(telefono);
            vistare.getTxtemail().setText(correo);
            vistare.getDtc_Date().setDateFormatString(fecha);

            vistare.getTxtcedula().setEditable(false);
            return true;

        } else {
            JOptionPane.showMessageDialog(vistare, "USTED NO HA SELECCIONADO UNA FILA");
            return false;
        }

    }

    public void mostrarDialogo() {
        if (getInformacion()==true) {
            vistare.getDlg_editarCliente().setSize(439, 400);
            vistare.getDlg_editarCliente().setTitle("Actualizar Informacion");
            vistare.getDlg_editarCliente().setLocationRelativeTo(vistare);
            vistare.getDlg_editarCliente().setVisible(true);
        }else{
        
        
        }
        
    }

    public void transparentarBotones() {

        vistare.getBtn_Imprimir().setOpaque(false);

        vistare.getBtn_Imprimir().setContentAreaFilled(false);
        vistare.getBtn_Imprimir().setBorderPainted(false);
        //----//
        vistare.getBtn_Actualizar().setOpaque(false);
        vistare.getBtn_Actualizar().setContentAreaFilled(false);
        vistare.getBtn_Actualizar().setBorderPainted(false);

        //------------//
        vistare.getBtn_buscar().setOpaque(false);
        vistare.getBtn_buscar().setContentAreaFilled(false);
        vistare.getBtn_buscar().setBorderPainted(false);

    }

}
