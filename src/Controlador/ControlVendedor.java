/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloVendedor;
import Modelo.Vendedor;
import Vistas.Registro_Usuario;
import Vistas.Vista_MenuEmpleado;
import Vistas.vista_loguin;
import java.awt.Image;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author FabianCh
 */
public class ControlVendedor {
    private ModeloVendedor v;
    private Registro_Usuario re;
    private Vista_MenuEmpleado m;
    private vista_loguin l;

    public ControlVendedor(ModeloVendedor v, Registro_Usuario re) {
        this.v = v;
        this.re = re;
        inicioControlVendedor();
        this.re.setVisible(true);
        System.out.println("hola cruel");
        
    }

    public ControlVendedor() {
    }
    public void inicioControlVendedor(){
        re.getBtn_Registrar().addActionListener(l->grabarVendedor());
        re.getBtn_Examninar().addActionListener(l->cargarImagen());
        re.getBtn_cancelar().addActionListener(l->re.dispose());
        re.getLbl_codigo().setText(v.GeneraridVendedor());
    }
    private void grabarVendedor() {

        String cedula = re.getT_txt_cedulaV().getText();
        String nombre = re.getTxt_nombreV().getText();
        String apellido = re.getTxt_nombreV().getText();
        String telefono = re.getTxt_tlfV().getText();
        String correo= re.getTxt_CorreoV().getText();
        Instant instant = re.getFechaNaciV().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha_nacimiento = Date.valueOf(zdt.toLocalDate());

        ModeloVendedor usuario = new ModeloVendedor(cedula, nombre, telefono, apellido, correo, fecha_nacimiento);
        if (usuario.grabarUsuario()== true) {
            
            System.out.println("Usuario registrado");
        } else {
            System.out.println("ERROR");

        }
       String contraseña=String.valueOf(re.getJpasw_vendedor1().getPassword());
       Image foto=((ImageIcon)re.getLbl_fotoV().getIcon()).getImage();
       ModeloVendedor vendedor=new ModeloVendedor();
       vendedor.setCedula(cedula);
       vendedor.setContraseñaV(contraseña);
       vendedor.setFotoV(foto);
        if (vendedor.grabarVendedor()== true) {
            JOptionPane.showMessageDialog(re, "Vendedor Guardado exitosamente");
        } else {
            JOptionPane.showMessageDialog(re, "ERROR ");
        }
        
    }
    private void mostrarNF(){
        String cedula=l.getTxt_inicioCedula().getText();
        ModeloVendedor v1=new ModeloVendedor();
        v1.setCedula(cedula);
        String nombre=v1.VistaInicioVendedor();
        m.getLbl_nombreV().setText(nombre);
       
    }
    private void cargarImagen() {
        Image captura;

        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("*.jpg", "jpg");
        jfc.setFileFilter(extensionFilter);
        int estado = jfc.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                captura = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(re.getLbl_fotoV().getWidth(), re.getLbl_fotoV().getHeight(), java.awt.Image.SCALE_DEFAULT);
               re.getLbl_fotoV().setIcon(new ImageIcon(captura));
                re.getLbl_fotoV().updateUI();
            } catch (IOException ex) {
                Logger.getLogger(ControlVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
