/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import javax.swing.JMenuItem;

/**
 *
 * @author JOSEPH
 */
public class Vista_Administrador extends javax.swing.JInternalFrame {

    /**
     * Creates new form Vista_Administrador
     */
    public Vista_Administrador() {
        initComponents();
    }

    public JMenuItem getBtnNuevasFunciones() {
        return btnNuevasFunciones;
    }

    public void setBtnNuevasFunciones(JMenuItem btnNuevasFunciones) {
        this.btnNuevasFunciones = btnNuevasFunciones;
    }

    public JMenuItem getBtnNuevasPelis() {
        return btnNuevasPelis;
    }

    public void setBtnNuevasPelis(JMenuItem btnNuevasPelis) {
        this.btnNuevasPelis = btnNuevasPelis;
    }

    public JMenuItem getBtnReportClientes() {
        return btnReportClientes;
    }

    public void setBtnReportClientes(JMenuItem btnReportClientes) {
        this.btnReportClientes = btnReportClientes;
    }

    public JMenuItem getBtnreportEmpleados() {
        return btnreportEmpleados;
    }

    public void setBtnreportEmpleados(JMenuItem btnreportEmpleados) {
        this.btnreportEmpleados = btnreportEmpleados;
    }

    public JMenuItem getBtnreportVentas() {
        return btnreportVentas;
    }

    public void setBtnreportVentas(JMenuItem btnreportVentas) {
        this.btnreportVentas = btnreportVentas;
    }

  
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        menuAdmin = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnNuevasPelis = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btnNuevasFunciones = new javax.swing.JMenuItem();
        btnNuevasSalas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnreportVentas = new javax.swing.JMenuItem();
        btnReportClientes = new javax.swing.JMenuItem();
        btnreportEmpleados = new javax.swing.JMenuItem();
        btnSalir = new javax.swing.JMenu();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(179, 43, 43));

        javax.swing.GroupLayout menuAdminLayout = new javax.swing.GroupLayout(menuAdmin);
        menuAdmin.setLayout(menuAdminLayout);
        menuAdminLayout.setHorizontalGroup(
            menuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 806, Short.MAX_VALUE)
        );
        menuAdminLayout.setVerticalGroup(
            menuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuAdmin)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuAdmin, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ticket_icon.png"))); // NOI18N
        jMenu1.setText("Peliculas");

        btnNuevasPelis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pelicula.png"))); // NOI18N
        btnNuevasPelis.setText("Gestion Peliculas");
        jMenu1.add(btnNuevasPelis);

        jMenuBar1.add(jMenu1);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/teatro.png"))); // NOI18N
        jMenu5.setText("Funciones");

        btnNuevasFunciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/glasses_icon.png"))); // NOI18N
        btnNuevasFunciones.setText("Gestionar Funciones");
        jMenu5.add(btnNuevasFunciones);

        btnNuevasSalas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sala.png"))); // NOI18N
        btnNuevasSalas.setText("Gestionar salas");
        jMenu5.add(btnNuevasSalas);

        jMenuBar1.add(jMenu5);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/report.png"))); // NOI18N
        jMenu2.setText("Reportes");

        btnreportVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Sales-report_.png"))); // NOI18N
        btnreportVentas.setText("Reporte Ventas");
        jMenu2.add(btnreportVentas);

        btnReportClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/documentediting_.png"))); // NOI18N
        btnReportClientes.setText("Reporte Clientes");
        btnReportClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportClientesActionPerformed(evt);
            }
        });
        jMenu2.add(btnReportClientes);

        btnreportEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/report.png"))); // NOI18N
        btnreportEmpleados.setText("Reporte Empleados");
        btnreportEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreportEmpleadosActionPerformed(evt);
            }
        });
        jMenu2.add(btnreportEmpleados);

        jMenuBar1.add(jMenu2);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        jMenuBar1.add(btnSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportClientesActionPerformed

    private void btnreportEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreportEmpleadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnreportEmpleadosActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnNuevasFunciones;
    private javax.swing.JMenuItem btnNuevasPelis;
    private javax.swing.JMenuItem btnNuevasSalas;
    private javax.swing.JMenuItem btnReportClientes;
    private javax.swing.JMenu btnSalir;
    private javax.swing.JMenuItem btnreportEmpleados;
    private javax.swing.JMenuItem btnreportVentas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JDesktopPane menuAdmin;
    // End of variables declaration//GEN-END:variables
}
