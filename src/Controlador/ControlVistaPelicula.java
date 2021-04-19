/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPelicula;
import Modelo.Pelicula;
import Vistas.Vista_gestionPeliculas;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;


/**
 *
 * @author FabianCh
 */
public class ControlVistaPelicula {
    private ModeloPelicula p;
    private Vista_gestionPeliculas v;

    public ControlVistaPelicula(ModeloPelicula p, Vista_gestionPeliculas v) {
        this.p = p;
        this.v = v;
        v.setVisible(true);
        v.getTxt_codigoPeli().setText(p.GeneraridPelicula());
        v.getTxt_codigoPeli().setEnabled(false);
        InicioContro();
    }
    private void InicioContro(){
        v.getJactualizar().setVisible(false);
       v.getBtn_guardar().addActionListener(l->grabarPelicula());
       v.getJeditar().addActionListener(l->EditarPelicula());
       v.getJeliminar().addActionListener(l->EliminarPelicula());
       v.getBtn_ExaminarFoto().addActionListener(l->cargarImagen());
        v.getJactualizar().addActionListener(l->actualiza());
       v.getBtn_salir().addActionListener(l->v.dispose());
       cargaPeliculas();
    }
    private void grabarPelicula(){
        boolean disponibilidad=true ;
        String titulo=v.getTxt_Titulo().getText();
        String Descripcion=v.getTxt_area_Descripcion().getText();
        String genero=String.valueOf(v.getCombo_genero().getSelectedItem());
        String clasificacion=String.valueOf(v.getCombo_pelicula().getSelectedItem());
        int duracion =Integer.parseInt(v.getTxt_duracion().getText());
        Image foto=((ImageIcon)v.getLbl_fotoPeli().getIcon()).getImage();
        if(v.getRadio_btnActivo().isSelected()){
            disponibilidad=true;
        }else{
            disponibilidad=false;
        }
        ModeloPelicula p1=new ModeloPelicula();
        p1.setTitulo(titulo);
        p1.setDescripcion(Descripcion);
        p1.setGenero(genero);
        p1.setClasificacion(clasificacion);
        p1.setDuracion(duracion);
        p1.setFoto(foto);
        p1.setEstado(disponibilidad);
        if(p1.GrabarPelicula()){
           cargaPeliculas();
        }else{
            JOptionPane.showMessageDialog(v, "Error al guardar la Película");
        }
    }
     private void cargaPeliculas() {
        v.getTabPeliculas().setDefaultRenderer(Object.class, new ImagenTabla());
        v.getTabPeliculas().setRowHeight(100);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) v.getTabPeliculas().getModel();
        tblModel.setNumRows(0);
        int ncol = tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);

        List<Pelicula> lista = new ModeloPelicula().listarPeliculas();
        lista.stream().forEach(p1 -> {
            //   String[] persona={p1.getIdPersona(),p1.getNombres(),p1.getApellidos(),String.valueOf(p1.getEdad())};

            tblModel.addRow(new Object[ncol]);
            v.getTabPeliculas().setValueAt(p1.getTitulo(), i.value, 0);
            v.getTabPeliculas().setValueAt(p1.getGenero(), i.value, 1);
            v.getTabPeliculas().setValueAt(p1.getDuracion(), i.value, 2);
            v.getTabPeliculas().setValueAt(p1.getClasificacion(), i.value, 3);
            v.getTabPeliculas().setValueAt(p1.getEstado(), i.value, 5);
            

            java.awt.Image img = p1.getFoto();

            if (img != null) {
                java.awt.Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newimg);
                renderer.setIcon(icon);
                v.getTabPeliculas().setValueAt(new JLabel(icon), i.value, 4);
            } else {
                v.getTabPeliculas().setValueAt(null, i.value, 4);
            }
            i.value++;
            
        });
    }
     public void EditarPelicula() {
        int ind=v.getTabPeliculas().getSelectedRow();
        if(ind==-1){
            JOptionPane.showMessageDialog(v, "Seleccione una fila");
        }else{        
 
            String idPelicula = v.getTabPeliculas().getValueAt(ind, 0).toString();
            String titulo = v.getTabPeliculas().getValueAt(ind, 1).toString();
            String genero = v.getTabPeliculas().getValueAt(ind, 2).toString();
            String duracion = v.getTabPeliculas().getValueAt(ind, 3).toString();
            String clasificacion = v.getTabPeliculas().getValueAt(ind, 4).toString();
            String estado = v.getTabPeliculas().getValueAt(ind, 6).toString();
            ImageIcon imagen = null;
                if (v.getTabPeliculas().getValueAt(ind, 5) != null) {
                    imagen = (ImageIcon) ((javax.swing.JLabel) v.getTabPeliculas().getValueAt(ind, 5)).getIcon();
                }    
                ModeloPelicula p1 = new ModeloPelicula();
                p1.setIdPelicula(idPelicula);
                v.getTxt_codigoPeli().setText(idPelicula);
                v.getTxt_Titulo().setText(titulo);
                v.getCombo_genero().setSelectedItem(genero);
                v.getTxt_duracion().setText(duracion);
                v.getTxt_area_Descripcion().setText("Pelicula sobre:");
                v.getCombo_pelicula().setSelectedItem(clasificacion);
                v.getRadio_btnActivo().setSelected(false);
                v.getRadio_btnInactivo().setSelected(false);
                if (imagen != null) {
                    java.awt.Image i = imagen.getImage();
                    ImageIcon ime = new ImageIcon(i.getScaledInstance(v.getLbl_fotoPeli().getWidth(), v.getLbl_fotoPeli().getHeight(), java.awt.Image.SCALE_DEFAULT));
                    v.getLbl_fotoPeli().setIcon(ime);
                } else {

                }
                v.getJactualizar().setVisible(true);
                v.getLbl_fotoPeli().updateUI();
                v.getTxt_codigoPeli().setEditable(false);
                v.getBtn_guardar().setVisible(false);
                
            } 
    }
     private void actualiza(){
            boolean disponibilidad=true ;
        String idpelicula=v.getTxt_codigoPeli().getText();
        String titulo = v.getTxt_Titulo().getText();
        String Descripcion=v.getTxt_area_Descripcion().getText();
        String genero=String.valueOf(v.getCombo_genero().getSelectedItem());
        String clasificacion=String.valueOf(v.getCombo_pelicula().getSelectedItem());
        int duracion =Integer.parseInt(v.getTxt_duracion().getText());
        Image foto=((ImageIcon)v.getLbl_fotoPeli().getIcon()).getImage();
        if(v.getRadio_btnActivo().isSelected()){
            disponibilidad=true;
        }else{
            disponibilidad=false;
        }
        if(!idpelicula.equals("") || !titulo.equals("")||!genero.equals("")||!clasificacion.equals("")||!foto.equals("")){
          
        ModeloPelicula peli = new ModeloPelicula(idpelicula, Descripcion, clasificacion, genero, duracion, titulo, disponibilidad, foto);
        if (peli.Editar() == true) {
            cargaPeliculas();
            JOptionPane.showMessageDialog(v, "Registro actualizado exitosamente");
            limpiar();
        }} else {
            JOptionPane.showMessageDialog(v, "Campos vacios no se puede Actualizar");
        }
        
    }
    public void EliminarPelicula(){
        int ind=v.getTabPeliculas().getSelectedRow();
        if(ind==-1){
            JOptionPane.showMessageDialog(v, "Seleccione una fila");
        }else{
            String idpeli = v.getTabPeliculas().getValueAt(ind, 0).toString();
                ModeloPelicula peli = new ModeloPelicula();
                peli.setIdPelicula(idpeli);
            if(peli.eliminar()){
                cargaPeliculas();
                JOptionPane.showMessageDialog(v, "Pelicula Eliminada Con Exito");
            }else{
                 JOptionPane.showMessageDialog(v, "FALLO AL ELIMINAR EL REGISTRO\nPelicula Relacionada con Funcion");
            }
        }
    } 
    
    private void limpiar(){
        ModeloPelicula peli=new ModeloPelicula();
                v.getTxt_codigoPeli().setText(peli.GeneraridPelicula());
                v.getTxt_Titulo().setText("");
                v.getCombo_genero().setSelectedIndex(0);
                v.getTxt_duracion().setText("");
                v.getTxt_area_Descripcion().setText("Pelicula sobre:");
                v.getCombo_pelicula().setSelectedIndex(0);
                v.getRadio_btnActivo().setSelected(false);
                v.getRadio_btnInactivo().setSelected(false);  
                v.getLbl_fotoPeli().setIcon(null);
                v.getJactualizar().setVisible(false);
                v.getBtn_guardar().setVisible(true);
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
                captura = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(v.getLbl_fotoPeli().getWidth(), v.getLbl_fotoPeli().getHeight(), java.awt.Image.SCALE_DEFAULT);
               v.getLbl_fotoPeli().setIcon(new ImageIcon(captura));
                v.getLbl_fotoPeli().updateUI();
            } catch (IOException ex) {
                Logger.getLogger(ControlVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
