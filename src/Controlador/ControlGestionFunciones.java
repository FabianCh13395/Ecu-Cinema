/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Funcion;
import Modelo.ModeloFuncion;
import Modelo.ModeloPelicula;
import Modelo.ModeloSala;
import Modelo.Pelicula;
import Modelo.Sala;
import Vistas.Vista_GestionFunciones;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;

/**
 *
 * @author FabianCh
 */
public class ControlGestionFunciones {
    private ModeloFuncion f;
    private ModeloPelicula p;
    private ModeloSala s;
    private Vista_GestionFunciones g;

    public ControlGestionFunciones(ModeloFuncion f, Vista_GestionFunciones g) {
        this.f = f;
        this.g = g;
        g.setVisible(true);
        InicioControl();
    }
    private void InicioControl(){
        ListarFunciones();
        g.getBtnGuardar().addActionListener(l->GrabarFuncion());
        g.getJcombo_horaInicio().addItemListener(l->{
        LlenarComboBoxSala();
        AsignarHoraFinal();
        });
        LLenarTablaPelicula();
        LlenarComboBoxSala();
        actualizarSalaDispo();
    }
    private void LLenarTablaPelicula(){
        DefaultTableModel tbmodel;
        tbmodel=(DefaultTableModel)g.getTablaPelicula().getModel();
        tbmodel.setRowCount(0);
        int ncol=tbmodel.getColumnCount();
        Holder<Integer> i=new Holder<>(0);
        List<Pelicula> pelicula=new ModeloPelicula().listarPeliculaFuncion();
        pelicula.stream().forEach(s1->{
            tbmodel.addRow(new Object[ncol]);
        g.getTablaPelicula().setValueAt(s1.getIdPelicula(), i.value,0);
        g.getTablaPelicula().setValueAt(s1.getTitulo(),i.value, 1);
        g.getTablaPelicula().setValueAt(s1.getDuracion(),i.value,2);
        g.getTablaPelicula().setValueAt(s1.getGenero(),i.value,3);
        g.getTablaPelicula().setValueAt(s1.getClasificacion(),i.value,4);
        i.value++;
        });
        
    }
    private void LlenarComboBoxSala(){
        g.getCombo_Sala().removeAllItems();
        LocalTime tiempoInicio=null;
        String horaI=String.valueOf(g.getJcombo_horaInicio().getSelectedItem());
        if(horaI.equals("11:00")){
            tiempoInicio=LocalTime.of(11, 0, 0);
        }
        if(horaI.equals("13:00")){
            tiempoInicio=LocalTime.of(13, 0, 0);
        }
        if(horaI.equals("15:00")){
            tiempoInicio=LocalTime.of(15, 0, 0);
        }
        if(horaI.equals("17:00")){
            tiempoInicio=LocalTime.of(17, 0, 0);
        }
        if(horaI.equals("19:00")){
            tiempoInicio=LocalTime.of(19, 0, 0);
        }
        ModeloSala s= new ModeloSala();
        Funcion l= new Funcion();
        l.setHoraInicio(tiempoInicio);
        s.setF(l);
        List<Sala> sala=s.NIdSala();
        
        sala.stream().forEach(s2->{
        g.getCombo_Sala().addItem(s2.getIdSala()+"-"+s2.getNombreSala());
        });
    }
    private void GrabarFuncion(){
        LocalTime tiempoInicio=null;
        LocalTime tiempoFinal=null;
        String horaI=String.valueOf(g.getJcombo_horaInicio().getSelectedItem());
        if(horaI.equals("11:00")){
            tiempoInicio=LocalTime.of(11, 0, 0);
        }
        if(horaI.equals("13:00")){
            tiempoInicio=LocalTime.of(13, 0, 0);
        }
        if(horaI.equals("15:00")){
            tiempoInicio=LocalTime.of(15, 0, 0);
        }
        if(horaI.equals("17:00")){
            tiempoInicio=LocalTime.of(17, 0, 0);
        }
        if(horaI.equals("19:00")){
            tiempoInicio=LocalTime.of(19, 0, 0);
        }
        String horaF=g.getTxtHoraFinal().getText();
        if(horaF.equals("13:00")){
            tiempoFinal=LocalTime.of(13, 0, 0);
        }
        if(horaF.equals("15:00")){
            tiempoFinal=LocalTime.of(15, 0, 0);
        }
        if(horaF.equals("17:00")){
            tiempoFinal=LocalTime.of(17, 0, 0);
        }
        if(horaF.equals("19:00")){
            tiempoFinal=LocalTime.of(19, 0, 0);
        }
        if(horaF.equals("21:00")){
            tiempoFinal=LocalTime.of(21, 0, 0);
        }
        String idSala=String.valueOf(g.getCombo_Sala().getSelectedItem()).substring(0, 7);
          Instant instant = g.getFechaFuncion().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fechaFuncion= Date.valueOf(zdt.toLocalDate());
        Boolean Disponibilidad=true;
        if(g.getRbtnActivo().isSelected()){
            Disponibilidad=true;
        }
        if(g.getRbutonInactivo().isSelected()){
            Disponibilidad=false;
        }
        ModeloFuncion n=new ModeloFuncion();
        n.setDisponibilidad(Disponibilidad);
        n.setFecha(fechaFuncion);
        n.setHoraFin(tiempoFinal);
        n.setHoraInicio(tiempoInicio);
        Pelicula p=idPelicula();
        
        Sala s1=new Sala();
        s1.setIdSala(idSala);
        n.setS(s1);
        if(p!=null){
            n.setP(p);
            if(n.GuardarFuncion()){
                ListarFunciones();
                actualizarSalaDispo();
                LlenarComboBoxSala();
            }else{
                JOptionPane.showMessageDialog(g,"Error al grabar la Función");
            }
            
        }
    }
    private void AsignarHoraFinal(){
        String horaInicio =String.valueOf(g.getJcombo_horaInicio().getSelectedItem());
        if(horaInicio.equals("11:00")){
            g.getTxtHoraFinal().setText("13:00");  
            g.getTxtHoraFinal().setEditable(false);
        }
        if(horaInicio.equals("13:00")){
            g.getTxtHoraFinal().setText("15:00");  
            g.getTxtHoraFinal().setEditable(false);
        }
        if(horaInicio.equals("15:00")){
            g.getTxtHoraFinal().setText("17:00");  
            g.getTxtHoraFinal().setEditable(false);
        }
        if(horaInicio.equals("17:00")){
            g.getTxtHoraFinal().setText("19:00");   
            g.getTxtHoraFinal().setEditable(false);
        }
        if(horaInicio.equals("19:00")){
            g.getTxtHoraFinal().setText("21:00"); 
            g.getTxtHoraFinal().setEditable(false);
        }
    }
    private void ListarFunciones(){
          DefaultTableModel tbmodel;
        tbmodel=(DefaultTableModel)g.getTablaFuncion().getModel();
        tbmodel.setRowCount(0);
        int ncol=tbmodel.getColumnCount();
        Holder<Integer> i=new Holder<>(0);
        List<Funcion> funcion=new ModeloFuncion().ListarFunciones();
        funcion.stream().forEach(s1->{
            tbmodel.addRow(new Object[ncol]);
        g.getTablaFuncion().setValueAt(s1.getIdFuncion(), i.value,0);
        g.getTablaFuncion().setValueAt(s1.getHoraInicio(),i.value, 1);
        g.getTablaFuncion().setValueAt(s1.getHoraFin(),i.value,2);
        g.getTablaFuncion().setValueAt(s1.getP().getIdPelicula(),i.value,3);
        g.getTablaFuncion().setValueAt(s1.getS().getIdSala(),i.value,4);
        g.getTablaFuncion().setValueAt(s1.getFecha(),i.value,5);
        g.getTablaFuncion().setValueAt(s1.getDisponibilidad(),i.value,6);
        
        i.value++;
        });
        g.getLbl_idFuncion().setText(f.GeneraridFuncion());
    }
    private Pelicula idPelicula(){
        
       int num=g.getTablaPelicula().getSelectedRow();
       if(num!=-1){
           String idpelicula=String.valueOf(g.getTablaPelicula().getValueAt(num,0 ));
       Pelicula p=new Pelicula();
       p.setIdPelicula(idpelicula);
       return p;
       }else{
           JOptionPane.showMessageDialog(g,"Elija una pelicula");
       }
       return null;
    }
    private void actualizarSalaDispo(){
        String idSala=String.valueOf(g.getCombo_Sala().getSelectedItem()).substring(0, 7);
        ModeloSala s=new ModeloSala();
        s.setIdSala(idSala);
        s.DisponibilidadSala();
    }
}
