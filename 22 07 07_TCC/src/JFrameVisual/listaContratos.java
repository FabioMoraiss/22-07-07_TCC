/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrameVisual;

import controle.Tecladinho;
import controle.controleContrato;
import modelo.contrato;

import javax.swing.JOptionPane;

import JFrameVisual.registrarContrato;
public class listaContratos extends javax.swing.JFrame {
    private controleContrato controle;
    private registrarContrato registrarNovoContrato;

    public listaContratos() {
        controle = new controleContrato();
        initComponents();
        setLocationRelativeTo(null);
        inicializarComponenteTela();
    }
    private void inicializarComponenteTela() {
        jTable1.setModel(controle.gerartDefaultTableModel());
    }
    public void atualizarTabela() {
        jTable1.setModel(controle.gerartDefaultTableModel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1EDITAR_CONTRATO = new javax.swing.JButton();
        jButton2REMVER_CONTRATO = new javax.swing.JButton();
        jButton3ADICIONAR_CONTRATO = new javax.swing.JButton();
        jToggleButton1FINALIZAR_CONTRATO = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
        jLabel1.setText("Lista de contratos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1EDITAR_CONTRATO.setText("editar contrato");
        jButton1EDITAR_CONTRATO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1EDITAR_CONTRATOActionPerformed(evt);
            }
        });

        jButton2REMVER_CONTRATO.setBackground(new java.awt.Color(255, 51, 0));
        jButton2REMVER_CONTRATO.setText("REMOVER CONTRATO");
        jButton2REMVER_CONTRATO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2REMVER_CONTRATOActionPerformed(evt);
            }
        });

        jButton3ADICIONAR_CONTRATO.setBackground(new java.awt.Color(0, 153, 0));
        jButton3ADICIONAR_CONTRATO.setText("ADICIONAR CONTRATO");
        jButton3ADICIONAR_CONTRATO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ADICIONAR_CONTRATOActionPerformed(evt);
            }
        });

        jToggleButton1FINALIZAR_CONTRATO.setText("Finalizar contrato");
        jToggleButton1FINALIZAR_CONTRATO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1FINALIZAR_CONTRATOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton3ADICIONAR_CONTRATO)
                .addGap(97, 97, 97)
                .addComponent(jButton1EDITAR_CONTRATO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1FINALIZAR_CONTRATO)
                .addGap(105, 105, 105)
                .addComponent(jButton2REMVER_CONTRATO)
                .addGap(78, 78, 78))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(406, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(391, 391, 391))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2REMVER_CONTRATO)
                        .addComponent(jToggleButton1FINALIZAR_CONTRATO))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1EDITAR_CONTRATO)
                        .addComponent(jButton3ADICIONAR_CONTRATO)))
                .addGap(11, 11, 11))
        );

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
    }// </editor-fold>                          

    private void jButton3ADICIONAR_CONTRATOActionPerformed(java.awt.event.ActionEvent evt) {                                                           
        registrarNovoContrato = new registrarContrato(this);
        registrarNovoContrato.setVisible(true);
        atualizarTabela();
    }                                                          

    private void jButton1EDITAR_CONTRATOActionPerformed(java.awt.event.ActionEvent evt) {                                                        
        int linha = jTable1.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "selecione um contrato para EDITTAR", 
           "você esqueceu uma coisa !", JOptionPane.WARNING_MESSAGE);
        } else {
            int idcontrato = controle.getListaContratos().get(linha).getId();
            registrarContrato tela = new registrarContrato(this, idcontrato);
            tela.setVisible(true);
            atualizarTabela();
        }
    }                                                       

    private void jButton2REMVER_CONTRATOActionPerformed(java.awt.event.ActionEvent evt) {                                                        
        int linha = jTable1.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "selecione um contrato para APAGAR", 
           "você esqueceu uma coisa !", JOptionPane.WARNING_MESSAGE);
        } else {
            contrato contr = controle.getListaContratos().get(linha);
            int opcao =
            JOptionPane.showConfirmDialog(null, "VOCÊ TEM CERTEZA QUE QUER DELETAR O CONTRATO " + contr.getId()
            , "COMFIRME SUA ESOCLHA", JOptionPane.YES_NO_CANCEL_OPTION);
            if(opcao == 0 && controle.deletar(controle.getListaContratos().get(linha))) {
                atualizarTabela();
            }
        }
    }    
    
    private void jToggleButton1FINALIZAR_CONTRATOActionPerformed(java.awt.event.ActionEvent evt) {                                                                 
        int linha = jTable1.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "selecione um contrato para FINALIZAR", 
           "você esqueceu uma coisa !", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controle.getListaContratos().get(linha).getAtivo() == false) {
                JOptionPane.showMessageDialog(null, "selecione o contrato já esta inativo !", 
           "erro 47tu", JOptionPane.WARNING_MESSAGE);

            } else {
                contrato contr = controle.getListaContratos().get(linha);
                int opcao =
                JOptionPane.showConfirmDialog(null, "VOCÊ TEM CERTEZA QUE QUER finalizar o contrato " + contr.getId()
                , "COMFIRME SUA ESOCLHA", JOptionPane.YES_NO_CANCEL_OPTION);

                if(opcao == 0 && controle.finalizar(controle.getListaContratos().get(linha))) {

                    if(controle.desocupar(controle.getListaContratos().get(linha)));

                    atualizarTabela();

                }
            }

        }
    } 

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
            java.util.logging.Logger.getLogger(listaContratos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(listaContratos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(listaContratos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(listaContratos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new listaContratos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1EDITAR_CONTRATO;
    private javax.swing.JButton jButton2REMVER_CONTRATO;
    private javax.swing.JButton jButton3ADICIONAR_CONTRATO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1FINALIZAR_CONTRATO;
    // End of variables declaration                   
}
