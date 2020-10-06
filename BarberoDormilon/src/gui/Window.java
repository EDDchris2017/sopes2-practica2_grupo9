/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author mcalderon
 */
public class Window extends javax.swing.JFrame {

    private JLabel[] sillas;
    int cantidad_sillas = 20;
    
    /**
     * Creates new form Window
     */
    public Window() {
        
        initComponents();
        
        panelAreaEspera.setPreferredSize(new Dimension(495, 454));
        panelAreaEspera.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        // Añadir sillas a la sala.
        Icon icon = new ImageIcon(getClass().getResource("/images/empty-chair.png"));
        sillas = new JLabel[cantidad_sillas];
        for(int i = 0; i < cantidad_sillas; i++) {
            sillas[i] = new JLabel("#" + (i + 1));
            sillas[i].setHorizontalTextPosition(JLabel.CENTER);
            sillas[i].setVerticalTextPosition(JLabel.BOTTOM);
            sillas[i].setIcon(icon);
            panelAreaEspera.add(sillas[i]);
        }
        
        // Configuración para el label del barbero
        lblBarbero.setHorizontalTextPosition(JLabel.CENTER);
        lblBarbero.setVerticalTextPosition(JLabel.BOTTOM);
        
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAreaEspera = new javax.swing.JPanel();
        lblBarbero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelAreaEspera.setBorder(javax.swing.BorderFactory.createTitledBorder("Área de espera"));

        javax.swing.GroupLayout panelAreaEsperaLayout = new javax.swing.GroupLayout(panelAreaEspera);
        panelAreaEspera.setLayout(panelAreaEsperaLayout);
        panelAreaEsperaLayout.setHorizontalGroup(
            panelAreaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
        panelAreaEsperaLayout.setVerticalGroup(
            panelAreaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        lblBarbero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sleeping.png"))); // NOI18N
        lblBarbero.setText("Durmiendo...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelAreaEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(lblBarbero)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panelAreaEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBarbero)
                .addGap(202, 202, 202))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

    public JLabel getLblBarbero() {
        return lblBarbero;
    }

    public JLabel[] getSillas() {
        return sillas;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBarbero;
    private javax.swing.JPanel panelAreaEspera;
    // End of variables declaration//GEN-END:variables
}
