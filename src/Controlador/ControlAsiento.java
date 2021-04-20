/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Asiento;
import Modelo.ModeloAsiento;
import Modelo.Sala;
import Vistas.VistaAsiento;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JToggleButton;

/**
 *
 * @author FabianCh
 */
public class ControlAsiento {
    private VistaAsiento a;
    private ModeloAsiento m;
    private JToggleButton [][] JTBotones;
    private int filas;
    private int columnas;
    private int largobotton;
    private int anchobotton = 45;
    private int ejeX = 20;
    private int ejeY= 55;
    private Sala s;
    List<Asiento> asi ;
    private String asiento="";
    private  ControlVenta v1;
    

    public ControlAsiento(VistaAsiento a, ModeloAsiento m,Sala s,ControlVenta v1) {
        this.a = a;
        this.m = m;
        this.s=s;
        this.v1=v1;
        a.setLocationRelativeTo(a);
        a.setVisible(true);
        InicioControlAsiento();
    }
    private void InicioControlAsiento(){
        a.getBtn_salirAsiento().addActionListener(l->a.dispose());
        FilasAsiento();
        
        
    }
    private void FilasAsiento(){
        
        asi=m.listarAsiento(s.getIdSala());
        
        String NombreSala=s.getNombreSala();
        System.out.println(NombreSala.substring(5));
        if(Integer.parseInt(NombreSala.substring(5))%2 ==0){
            
            filas=4;
        }else{
            filas=5;
        }
        
     columnas =10;
     largobotton = 75;
    anchobotton = 45;
    ejeX = 20;
     ejeY= 55;
    JTBotones = new JToggleButton[filas][columnas]; 
    botones();
    }
    
    
      
    
    private void botones(){
    
        Font fuenteletra = new Font("Tahoma",Font.BOLD,9);
        
        int contasientos = 0;
        JTBotones = new JToggleButton[filas][columnas];
        
        for(int i = 0; i < filas; i++ ){
            
            for (int j = 0;j < columnas; j++){
                JTBotones[i][j]=new JToggleButton();
                JTBotones[i][j].setBounds(ejeX,ejeY, largobotton,anchobotton );
                JTBotones[i][j].setText(asi.get(contasientos).getNombreAsiento());
                JTBotones[i][j].setFont(fuenteletra);
                JTBotones[i][j].setBackground(new Color(60, 158, 43));              
                if(!asi.get(contasientos).isDisponibilidad()){
                    JTBotones[i][j].setBackground(Color.RED);
                    JTBotones[i][j].setSelected(true);
                    System.out.println("Esta imprimiendo");
                }
                AccionBotones accion = new AccionBotones();
                JTBotones[i][j].addActionListener(accion);
                a.getjPanel1().add(JTBotones[i][j]);
                
                contasientos++;
                ejeX += 75;//separacion columnas
            }
            ejeX =20;//posicion inical
            ejeY +=75;//separacion de filas
        }
        a.getPanelAsientos().updateUI();
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }
    
    
    private class AccionBotones implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int con=0;
            for (int i = 0; i <filas; i++) {
                for (int j = 0; j <columnas; j++){
                   if(e.getSource().equals(JTBotones[i][j])){
                      ModeloAsiento n1= new ModeloAsiento();
                      n1.setS(s);
                      n1.setIdAsiento(asi.get(con).getIdAsiento());
                      n1.setNombreAsiento(JTBotones[i][j].getText());
                      if(JTBotones[i][j].isSelected()){
                          
                          asiento+=n1.getNombreAsiento()+"-";
                          v1.setNombreAsi(asiento);
                          n1.setDisponibilidad(false);
                         JTBotones[i][j].setBackground(Color.RED);
                          System.out.println("hola mundo");
                      }else{
                          n1.setDisponibilidad(true);
                         JTBotones[i][j].setBackground(new Color(60, 158, 43));
                          System.out.println("adios loco");
                      }
                      n1.CambiarDisponibilidad();
                      con++;
                   } 
                }
            }
            a.getPanelAsientos().updateUI();
        }
       
    }
    
    
    
}
