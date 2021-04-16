/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloSala;
import Modelo.Sala;
import Vistas.Vista_Salas;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;

/**
 *
 * @author FabianCh
 */
public class ControlVistaSala {
    private ModeloSala m;
    private Vista_Salas v;

    public ControlVistaSala(ModeloSala m, Vista_Salas v) {
        this.m = m;
        this.v = v;
        v.setVisible(true);
        
        
        InicioControl();
    }
    private void InicioControl(){
        v.getBtnGuardarSala().addActionListener(l-> grabarSala());
        v.getjC_NombreSala().addItemListener(l->AsignarAsientos());
        listarSala();
    }
    private void grabarSala(){
        boolean vacante;
        String sala=String.valueOf(v.getjC_NombreSala().getSelectedItem());
        int totalAsientos=Integer.parseInt(v.getTxt_numeroA().getText());
        if(String.valueOf(v.getjC_Disponibilidad().getSelectedItem()).equalsIgnoreCase("libre")){
            vacante=true;
        }else{
           vacante=false; 
        }
        ModeloSala sm= new ModeloSala();
        sm.setNombreSala(sala);
        sm.setDisponibilidad(vacante);
        sm.setTotalAsientos(totalAsientos);
        if(sm.crearSala()){
            listarSala();
        }else{
            JOptionPane.showMessageDialog(v, "Error al intentar guardar la Sala");
        }
    }
    private void AsignarAsientos(){
        String sala=String.valueOf(v.getjC_NombreSala().getSelectedItem());
        if(Integer.parseInt(sala.substring(5))%2==0){
            v.getTxt_numeroA().setText("40");
            v.getTxt_numeroA().setEnabled(false);
        }else{
            v.getTxt_numeroA().setText("50");
            v.getTxt_numeroA().setEnabled(false);
        }
    }
    private void listarSala(){
        DefaultTableModel tbmodel;
        tbmodel=(DefaultTableModel)v.getTabSalas().getModel();
        tbmodel.setRowCount(0);
        int ncol=tbmodel.getColumnCount();
        Holder<Integer> i=new Holder<>(0);
        List<Sala> sala=new ModeloSala().ListarSalas();
        sala.stream().forEach(s1->{
            tbmodel.addRow(new Object[ncol]);
        v.getTabSalas().setValueAt(s1.getIdSala(), i.value,0);
        v.getTabSalas().setValueAt(s1.getNombreSala(),i.value, 1);
        v.getTabSalas().setValueAt(s1.getTotalAsientos(),i.value,2);
        
        i.value++;
        });
        v.getLbl_codigoSala().setText(m.Generar_idSala());
    }
    
}
