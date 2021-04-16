/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloAsiento;
import Vistas.VistaAsiento;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    

    public ControlAsiento(VistaAsiento a, ModeloAsiento m) {
        this.a = a;
        this.m = m;
        a.setLocationRelativeTo(a);
        a.setVisible(true);
        InicioControlAsiento();
    }
    private void InicioControlAsiento(){
        a.getBtn_salirAsiento().addActionListener(l->a.dispose());
        FilasAsiento();
        
        
    }
    private void FilasAsiento(){
        String NombreSala="sala 2";
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
        
        int contasientos = 1;
        JTBotones = new JToggleButton[filas][columnas];
        
        for(int i = 0; i < filas; i++ ){
            
            for (int j = 0;j < columnas; j++){
                JTBotones[i][j]=new JToggleButton();
                JTBotones[i][j].setBounds(ejeX,ejeY, largobotton,anchobotton );
                JTBotones[i][j].setText("A00" + contasientos);
                JTBotones[i][j].setFont(fuenteletra);
                JTBotones[i][j].setBackground(new Color(60, 158, 43));              
                
                AccionBotones accion = new AccionBotones();
                JTBotones[i][j].addActionListener(accion);
                a.getjPanel1().add(JTBotones[i][j]);
                
                contasientos++;
                ejeX += 75;//separacion columnas
            }
            ejeX =20;//posicion inical
            ejeY +=75;//separacion de filas
        }
    }
    
    
    private class AccionBotones implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i <filas; i++) {
                for (int j = 0; j <columnas; j++){
                   if(e.getSource().equals(JTBotones[i][j])){
                      
                      if(JTBotones[i][j].isSelected()){
                         JTBotones[i][j].setBackground(Color.RED);
                      }else{
                         JTBotones[i][j].setBackground(new Color(60, 158, 43));
                      }
                   } 
                }
            }
        }
       
    }
    
    
    
}
