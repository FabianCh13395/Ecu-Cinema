/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
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

    public JMenu getReporteAdministradores() {
        return reporteAdministradores;
    }

    public void setReporteAdministradores(JMenu reporteAdministradores) {
        this.reporteAdministradores = reporteAdministradores;
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

    public JMenuItem getMenuReporteAdministrador() {
        return menuReporteAdministrador;
    }

    public void setMenuReporteAdministrador(JMenuItem menuReporteAdministrador) {
        this.menuReporteAdministrador = menuReporteAdministrador;
    }

    public JDesktopPane getMenuAdmin() {
        return menuAdmin;
    }

    public void setMenuAdmin(JDesktopPane menuAdmin) {
        this.menuAdmin = menuAdmin;
    }

    public JMenu getMenu_peliculas() {
        return menu_peliculas;
    }

    public void setMenu_peliculas(JMenu menu_peliculas) {
        this.menu_peliculas = menu_peliculas;
    }

    public void setBtnNuevasPelis(JMenuItem btnNuevasPelis) {
        this.btnNuevasPelis = btnNuevasPelis;
    }

    public JMenuItem getBtnNuevasSalas() {
        return btnNuevasSalas;
    }

    public void setBtnNuevasSalas(JMenuItem btnNuevasSalas) {
        this.btnNuevasSalas = btnNuevasSalas;
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
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_peliculas = new javax.swing.JMenu();
        btnNuevasPelis = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btnNuevasFunciones = new javax.swing.JMenuItem();
        btnNuevasSalas = new javax.swing.JMenuItem();
        reporteAdministradores = new javax.swing.JMenu();
        btnreportVentas = new javax.swing.JMenuItem();
        btnReportClientes = new javax.swing.JMenuItem();
        btnreportEmpleados = new javax.swing.JMenuItem();
        menuReporteAdministrador = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(179, 43, 43));

        menuAdmin.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/iconoGenral.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("EcuCinema");

        menuAdmin.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        menuAdmin.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout menuAdminLayout = new javax.swing.GroupLayout(menuAdmin);
        menuAdmin.setLayout(menuAdminLayout);
        menuAdminLayout.setHorizontalGroup(
            menuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuAdminLayout.createSequentialGroup()
                .addContainerGap(707, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(40, 40, 40))
        );
        menuAdminLayout.setVerticalGroup(
            menuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuAdminLayout.createSequentialGroup()
                .addGroup(menuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuAdminLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1))
                    .addGroup(menuAdminLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel16)))
                .addContainerGap(435, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuAdmin)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuAdmin)
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));

        menu_peliculas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ticket_icon.png"))); // NOI18N
        menu_peliculas.setText("Peliculas");
        menu_peliculas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnNuevasPelis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pelicula.png"))); // NOI18N
        btnNuevasPelis.setText("Gestion Peliculas");
        menu_peliculas.add(btnNuevasPelis);

        jMenuBar1.add(menu_peliculas);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/teatro.png"))); // NOI18N
        jMenu5.setText("Funciones");
        jMenu5.setToolTipText("Gestionar Salas de Cine");
        jMenu5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnNuevasFunciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/glasses_icon.png"))); // NOI18N
        btnNuevasFunciones.setText("Gestionar Funciones");
        jMenu5.add(btnNuevasFunciones);

        btnNuevasSalas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sala.png"))); // NOI18N
        btnNuevasSalas.setText("Gestionar salas");
        jMenu5.add(btnNuevasSalas);

        jMenuBar1.add(jMenu5);

        reporteAdministradores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/report.png"))); // NOI18N
        reporteAdministradores.setText("Reportes");
        reporteAdministradores.setToolTipText("Informes, Reportes y registros");
        reporteAdministradores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnreportVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Sales-report_.png"))); // NOI18N
        btnreportVentas.setText("Reporte Ventas");
        btnreportVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reporteAdministradores.add(btnreportVentas);

        btnReportClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/documentediting_.png"))); // NOI18N
        btnReportClientes.setText("Reporte Clientes");
        btnReportClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportClientesActionPerformed(evt);
            }
        });
        reporteAdministradores.add(btnReportClientes);

        btnreportEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/report.png"))); // NOI18N
        btnreportEmpleados.setText("Reporte Vendedores");
        btnreportEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreportEmpleadosActionPerformed(evt);
            }
        });
        reporteAdministradores.add(btnreportEmpleados);

        menuReporteAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/check_1.png"))); // NOI18N
        menuReporteAdministrador.setText("Reporte Administradores");
        reporteAdministradores.add(menuReporteAdministrador);

        jMenuBar1.add(reporteAdministradores);

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
    private javax.swing.JMenuItem btnreportEmpleados;
    private javax.swing.JMenuItem btnreportVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JDesktopPane menuAdmin;
    private javax.swing.JMenuItem menuReporteAdministrador;
    private javax.swing.JMenu menu_peliculas;
    private javax.swing.JMenu reporteAdministradores;
    // End of variables declaration//GEN-END:variables
}
