/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagenes_Panel;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author EdwinPC
 */
public class Fondo_login extends javax.swing.JPanel {

    /**
     * Creates new form Fondo_Principal
     */
    public Fondo_login() {
        initComponents();
    }

    public void paint(Graphics g) {
        Dimension dimension=this.getSize();
        ImageIcon icon=new ImageIcon(getClass().getResource("/Imagenes_fondo/inicio.jpg"));
        g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
        this.setOpaque(false);
        super.paintChildren(g);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 798, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
