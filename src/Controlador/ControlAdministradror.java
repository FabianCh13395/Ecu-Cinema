/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloAdministrador;
import Modelo.ModeloVendedor;
import Vistas.Registro_Usuario;
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
public class ControlAdministradror {
    private ModeloAdministrador a;
    private Registro_Usuario r;

    public ControlAdministradror(ModeloAdministrador a, Registro_Usuario r) {
        this.a = a;
        this.r = r;
        r.setVisible(true);
        r.getLbl_text1().setText("Registro Administrador");
        InicioControlAdministrador();
    }

    public ControlAdministradror() {
    }
    public void InicioControlAdministrador(){
        r.getBtn_Registrar().addActionListener(l->grabarAdministrador());
        r.getBtn_cancelar().addActionListener(l->r.dispose());
        r.getBtn_Examninar().addActionListener(l->cargarImagen());
        r.getLbl_codigo().setText(a.GeneraridAdministrador());
    }
    private void grabarAdministrador() {

        String cedula = r.getT_txt_cedulaV().getText();
        String nombre = r.getTxt_nombreV().getText();
        String apellido = r.getTxt_apellidoV().getText();
        String telefono = r.getTxt_tlfV().getText();
        String correo= r.getTxt_CorreoV().getText();
        Instant instant = r.getFechaNaciV().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha_nacimiento = Date.valueOf(zdt.toLocalDate());

        ModeloAdministrador usuario = new ModeloAdministrador(cedula, nombre, telefono, apellido, correo, fecha_nacimiento);
        if (usuario.grabarUsuario()== true) {
            
            System.out.println("Usuario registrado");
        } else {
            System.out.println("ERROR");

        }
       String contraseña=String.valueOf(r.getJpasw_vendedor1().getPassword());
       Image foto=((ImageIcon)r.getLbl_fotoV().getIcon()).getImage();
       ModeloAdministrador administrador=new ModeloAdministrador();
       administrador.setCedula(cedula);
       administrador.setContraseña(contraseña);
       administrador.setFotoA(foto);
        if (administrador.grabarAdministtador()== true) {
            JOptionPane.showMessageDialog(r, "Administrador Guardado exitosamente");
            r.dispose();
        } else {
            JOptionPane.showMessageDialog(r, "ERROR ");
        }
        
        
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
                captura = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(r.getLbl_fotoV().getWidth(), r.getLbl_fotoV().getHeight(), java.awt.Image.SCALE_DEFAULT);
               r.getLbl_fotoV().setIcon(new ImageIcon(captura));
                r.getLbl_fotoV().updateUI();
            } catch (IOException ex) {
                Logger.getLogger(ControlAdministradror.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void limpiarVentana(){
        r.getLbl_text1().setText("Registro Administrador");
        r.getTxt_nombreV().setText("");
        r.getTxt_apellidoV().setText("");
        r.getTxt_tlfV().setText("");
        r.getFechaNaciV().setCalendar(null);
        r.getJpas_vendedor().setText("");
        r.getJpasw_vendedor1().setText("");
        r.getTxt_CorreoV().setText("");
        r.getLbl_codigo().setText("");
        r.getLbl_fotoV().setIcon(null);
    }
}
