/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Atxy2k.CustomTextField.RestrictedTextField;
import Modelo.Categoria;
import Modelo.Cliente;
import Modelo.Funcion;
import Modelo.ModeloAsiento;
import Modelo.ModeloBoleto;
import Modelo.ModeloCategoria;
import Modelo.ModeloCliente;
import Modelo.ModeloCompra;
import Modelo.ModeloFuncion;
import Modelo.ModeloPelicula;
import Modelo.ModeloVendedor;
import Modelo.Pelicula;
import Modelo.Sala;
import Modelo.Vendedor;
import Vistas.Venta;
import Vistas.VistaAsiento;
import com.sun.javafx.tk.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;

/**
 *
 * @author FabianCh
 */
public class ControlVenta {

    private ModeloVendedor v;
    private ModeloCliente c;
    private Venta vistacli;
    private ModeloFuncion f;
    private ModeloCompra compra;
    List<Categoria> listac = new ArrayList<>();
    List<Funcion> funcion = new ArrayList<>();
    private int indice;
    private String nombreAsi;
    private double preciofinal = 0;

    public ControlVenta(ModeloVendedor v, Venta vistacli, ModeloCliente c) {
        this.v = v;
        this.vistacli = vistacli;
        this.c = c;
        vistacli.setVisible(true);
        control_btn_asiento();
        CargaFunciones();
        inicioControlCliente();
        eliminarFondobutton();
    }

    public void inicioControlCliente() {
        llenarComboCategoria();
        validarCliente();
        deshabilitarB(false);
        vistacli.getBtn_buscar().addActionListener(l -> mostrarInfCliente());
        vistacli.getBtn_registrarCli().addActionListener(l -> {
            if (validarCampos() == false) {
                JOptionPane.showMessageDialog(vistacli, "Necesita rellenar todos los campos ");
            } else {
                grabarCliente();
            }
        });
        vistacli.getBtn_Asiento().addActionListener(l -> {
            VistaAsiento v = new VistaAsiento();
            ModeloAsiento m = new ModeloAsiento();
            Sala s1 = new Sala();
            int l1 = vistacli.getjComboFuncion().getSelectedIndex();
            System.out.println(l1);
            ControlAsiento c = new ControlAsiento(v, m, funcion.get(l1).getS(), this);
            llenarTablaVenta();

            vistacli.getTxt_costoTotal().setText(String.valueOf(precioTotal()));

        });
        vistacli.getBtn_aceptar().addActionListener(l -> {
            if (String.valueOf(vistacli.getJcombo_MetodoPago().getSelectedItem()).equals("--Seleccione--")
                    || vistacli.getTxtcedula_cli().getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistacli, "Rellene Correctamente los campos");
            } else {
                realizarCompra();

                limpiarTabla();
                limpiarCampos();
                mostrarDiaolog();
            }

        });
        vistacli.getBtn_imprimir().addActionListener(l -> vistacli.getDlgFactura().setVisible(false));

    }

    public void setNombreAsi(String nombreAsi) {
        this.nombreAsi = nombreAsi;
    }

    private void grabarBoleto(ModeloCompra c) {
        int indice = vistacli.getjComboFuncion().getSelectedIndex();
        Funcion f = funcion.get(indice);
        System.out.println(f.getIdFuncion());
        Vendedor vendedor = v.getVendedor();
        System.out.println(vendedor.getId_vendedor());
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) vistacli.getTabButacas().getModel();
        List<String> nombresCa = new ArrayList<>();
        for (int i = 0; i < tbmodel.getRowCount(); i++) {
            nombresCa.add((tbmodel.getValueAt(i, 4).toString()));
        }
        nombresCa.stream().forEach(p1 -> {
            Categoria ca = listac.stream().filter(l -> l.getNombre().equals(p1)).findAny().get();
            ModeloBoleto bo = new ModeloBoleto();
            bo.setF(f);
            bo.setCo(c);
            bo.setC(ca);
            bo.setV(vendedor);
            if (bo.GuardarBoleto()) {

                System.out.println("boleto creado");
            } else {
                System.out.println("error de boleto");
            }
        });

    }

    private void realizarCompra() {
        String cedula = vistacli.getTxtcedula_cli().getText();

        Date Fechaactual = new Date(new java.util.Date().getTime());
        String metodoPa = String.valueOf(vistacli.getJcombo_MetodoPago().getSelectedItem());
        Double costoTotal = Double.valueOf(vistacli.getTxt_costoTotal().getText());
        LocalDateTime horaActual = LocalDateTime.now();
        int hora = horaActual.getHour();
        int minutos = horaActual.getMinute();
        int segundos = horaActual.getSecond();
        LocalTime horadecompra = LocalTime.of(hora, minutos, segundos);
        ModeloCompra c = new ModeloCompra();
        c.setCostoTotal(costoTotal);
        c.setFecha(Fechaactual);
        c.setHora(horadecompra);
        c.setMetodoPago(metodoPa);
        Cliente c1 = new Cliente();
        c1.setCedula(cedula);
        c.setC(c1);
        if (c.grabarCompra()) {
            grabarBoleto(c);
            JOptionPane.showMessageDialog(vistacli, "Compra realizada con exito");
        } else {
            JOptionPane.showMessageDialog(vistacli, "Error en la compra");
        }

    }

    private void CargaFunciones() {
        vistacli.getJcombo_Categoria().setSelectedIndex(0);
        funcion = new ModeloFuncion().traerFuncion();
        for (Funcion funcion : funcion) {
            vistacli.getjComboFuncion().addItem(funcion.toString());
        }
    }

    public void grabarCliente() {
        String cedula = vistacli.getTxtcedula_cli().getText();
        String nombre = vistacli.getTxt_nombreCli().getText();
        String apellido = vistacli.getTxt_apeliidoCli().getText();
        String telefono = vistacli.getTxt_telefonoCli().getText();
        String correo = vistacli.getTxt_correoCli().getText();
        Instant instant = vistacli.getFecha_cliente().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha_nacimiento = Date.valueOf(zdt.toLocalDate());
        ModeloCliente usuario = new ModeloCliente(cedula, nombre, telefono, apellido, correo, fecha_nacimiento);
        if (usuario.grabarUsuario() == true) {

            System.out.println("Usuario registrado");
        } else {
            System.out.println("ERROR");

        }
        ModeloCliente cliente = new ModeloCliente();
        cliente.setCedula(cedula);
        if (cliente.grabarCliente() == true) {
            JOptionPane.showMessageDialog(vistacli, "Cliente Guardado exitosamente");
        } else {
            JOptionPane.showMessageDialog(vistacli, "ERROR ");
        }
    }

    private void mostrarInfCliente() {

        String cedulacliente = vistacli.getTxt_buscar().getText();
        ModeloCliente cli = new ModeloCliente();
        cli.setCedula(cedulacliente);
        List<Cliente> lista = cli.buscarCedula();
        vistacli.getTxtcedula_cli().setText("");
        System.out.println(lista.size() + "Lista");
        System.out.println(cedulacliente + "hola");

        if (lista.size() > 0) {

            vistacli.getTxt_buscar().setText("");
            deshabilitarB(false);
            lista.forEach(c1 -> {
                vistacli.getTxtcedula_cli().setText(c1.getCedula());
                vistacli.getTxt_nombreCli().setText(c1.getNombre());
                vistacli.getTxt_apeliidoCli().setText(c1.getApellido());
                vistacli.getTxt_telefonoCli().setText(c1.getTelefono());
                vistacli.getTxt_correoCli().setText(c1.getCorreo());
                vistacli.getFecha_cliente().setDate(new java.util.Date(c1.getFecha_nacimiento().getTime()));
            });
        } else {
            vistacli.getTxt_buscar().setText("");
            vistacli.getTxtcedula_cli().setText(cedulacliente);
            JOptionPane.showMessageDialog(vistacli, "No existe el cliente");
            deshabilitarB(Boolean.TRUE);

        }

    }

    private void deshabilitarB(Boolean opcion) {

        vistacli.getTxtcedula_cli().setEditable(opcion);
        vistacli.getTxt_nombreCli().setEditable(opcion);
        vistacli.getTxt_apeliidoCli().setEditable(opcion);
        vistacli.getTxt_telefonoCli().setEditable(opcion);
        vistacli.getTxt_correoCli().setEditable(opcion);
        vistacli.getFecha_cliente().setEnabled(opcion);
        vistacli.getBtn_registrarCli().setEnabled(opcion);
    }

    private void validarCliente() {
        vistacli.getTxt_buscar().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (vistacli.getTxt_buscar().getText().length() >= 10) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        vistacli.getTxtcedula_cli().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (vistacli.getTxtcedula_cli().getText().length() >= 10) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        vistacli.getTxt_nombreCli().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (vistacli.getTxt_nombreCli().getText().length() >= 15) {
                    char c = e.getKeyChar();
                    e.consume();
                }
                char c = e.getKeyChar();
                if ((c < 'a' || c > 'z') && (c > 'A') | c < 'Z') {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        vistacli.getTxt_apeliidoCli().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (vistacli.getTxt_apeliidoCli().getText().length() >= 15) {
                    char c = e.getKeyChar();
                    e.consume();

                }
                char c = e.getKeyChar();
                if ((c < 'a' || c > 'z') && (c > 'A') | c < 'Z') {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        vistacli.getTxt_telefonoCli().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (vistacli.getTxt_telefonoCli().getText().length() >= 12) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        vistacli.getTxt_correoCli().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (paraEmail(vistacli.getTxt_correoCli().getText())) {

                } else {
                    JOptionPane.showMessageDialog(null, "Correo Incorrecto", "Validar correo", JOptionPane.INFORMATION_MESSAGE);
                    vistacli.getTxtcedula_cli().requestFocus();
                }
            }
        });

    }

    public boolean paraEmail(String correo2) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo2);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }

    public void llenarComboCategoria() {
        vistacli.getJcombo_Categoria().setSelectedIndex(0);
        ModeloCategoria c = new ModeloCategoria();
        vistacli.getJcombo_Categoria().removeAllItems();
        listac.clear();
        listac = c.traerCategoria();
        listac.stream().forEach(s -> vistacli.getJcombo_Categoria().addItem(s.getNombre()));
    }

    public void llenarTablaVenta() {
        int cont = vistacli.getjComboFuncion().getSelectedIndex();
        indice = cont;
        Funcion f1 = funcion.get(cont);
        int con1 = Integer.valueOf(vistacli.getTxt_Cantidad().getText());
        int contCa = vistacli.getJcombo_Categoria().getSelectedIndex();
        Categoria c = listac.get(contCa);
        Double precio1 = con1 * (c.getPrecio());
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) vistacli.getTabButacas().getModel();
        int ncol = tbmodel.getColumnCount();
        int nrow = tbmodel.getRowCount();
        System.out.println(nrow);

        tbmodel.addRow(new Object[ncol]);
        vistacli.getTabButacas().setValueAt(f1.getP().getTitulo(), nrow, 0);
        vistacli.getTabButacas().setValueAt(f1.getS().getNombreSala(), nrow, 1);
        vistacli.getTabButacas().setValueAt(f1.getHoraInicio(), nrow, 2);
        vistacli.getTabButacas().setValueAt(f1.getHoraFin(), nrow, 3);
        vistacli.getTabButacas().setValueAt(c.getNombre(), nrow, 4);
        vistacli.getTabButacas().setValueAt(con1, nrow, 5);
        vistacli.getTabButacas().setValueAt(precio1, nrow, 6);

    }

    private void limpiarTabla() {
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) vistacli.getTabButacas().getModel();
        tbmodel.setRowCount(0);
    }

    private double precioTotal() {
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) vistacli.getTabButacas().getModel();
        double precio = 0;
        for (int i = 0; i < tbmodel.getRowCount(); i++) {
            precio += Double.valueOf(tbmodel.getValueAt(i, 6).toString());
        }
        return precio;
    }

    private void mostrarDiaolog() {

        vistacli.getDlgFactura().setVisible(true);
        Funcion f = funcion.get(indice);
        vistacli.getLblpelicula().setText(f.getP().getTitulo());
        vistacli.getLbl_horainicio().setText(f.getHoraInicio().toString());
        vistacli.getLbl_cliente().setText(vistacli.getTxt_nombreCli().getText() + " " + vistacli.getTxt_apeliidoCli().getText());
        vistacli.getLbl_sala().setText(f.getS().getNombreSala());
        vistacli.getLbltotalPagar().setText(vistacli.getTxt_costoTotal().getText());
        vistacli.getLbl_asientos().setText(nombreAsi);
        nombreAsi = "";

    }

    private void limpiarCampos() {
        vistacli.getTxt_Cantidad().setText("");
        vistacli.getTxt_apeliidoCli().setText("");
        vistacli.getTxt_nombreCli().setText("");
        vistacli.getTxt_costoTotal().setText("");
        vistacli.getTxt_correoCli().setText("");
        vistacli.getTxt_telefonoCli().setText("");
        vistacli.getTxtcedula_cli().setText("");
        vistacli.getFecha_cliente().setCalendar(null);
        deshabilitarB(true);
        CargaFunciones();
        llenarComboCategoria();
    }

    private boolean validarCampos() {
        if (vistacli.getTxt_nombreCli().getText().isEmpty() || vistacli.getTxt_apeliidoCli().getText().isEmpty()
                || vistacli.getTxt_correoCli().getText().isEmpty() || vistacli.getTxt_telefonoCli().getText().isEmpty()
                || vistacli.getTxtcedula_cli().getText().isEmpty() || vistacli.getFecha_cliente().getDate() == null) {

            return false;
        }
        return true;
    }

    private void control_btn_asiento() {
        vistacli.getTxt_Cantidad().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (vistacli.getTxt_Cantidad().getText().length() >= 1) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!vistacli.getTxt_Cantidad().getText().isEmpty()) {
                    vistacli.getBtn_Asiento().setEnabled(true);
                } else {
                    vistacli.getBtn_Asiento().setEnabled(false);
                }
            }
        });

    }

    private void eliminarFondobutton() {

        vistacli.getBtn_buscar().setOpaque(false);
        vistacli.getBtn_buscar().setContentAreaFilled(false);
        vistacli.getBtn_buscar().setBorderPainted(false);
        //-------------------------------------------//
        
        vistacli.getBtn_Asiento().setOpaque(false);
        vistacli.getBtn_Asiento().setContentAreaFilled(false);
        vistacli.getBtn_Asiento().setBorderPainted(false);
    }

}
