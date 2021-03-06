/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import javax.swing.JFrame;
import org.openide.util.Exceptions;

/**
 *
 * @author Tran Cuong
 */
public class FormConfirm extends javax.swing.JFrame {

    private JFrame jFrame;
    
    public FormConfirm(JFrame jFrame, ArrayList<String> listText, Callable confirmFunction) {
        initComponents();
        
        this.jFrame = jFrame;
        
        // Cài đặt nội dung câu hỏi
        String content = "<html>";
        content += "<div style=\"text-align: center; width: 300px;\">";
        for (int i = 0; i < listText.size() && i < 4; i++) {
            content += "<p>" + listText.get(i) + "</p>";
        }
        content += "</div>";
        content += "</html>";        
        this.lblQuestion.setText(content);
        
        // Hàm xác nhận
        // lambda expression
        this.btnOK.addActionListener((ActionEvent evt) -> {
            try {
                confirmFunction.call();
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        });

        // Hàm hủy bỏ
        this.btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                hideFormConfirm();
            }
        });
        
        // Vô hiệu hóa form chính, buộc phải chọn trên FormConfirm
        this.setLocationRelativeTo(jFrame);
        this.setVisible(true);
        jFrame.setEnabled(false);
    }
    
    // Ẩn FormConfirm, khôi phục form chính
    private void hideFormConfirm() {
        this.dispose();
        this.jFrame.setVisible(true);
        this.jFrame.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblQuestion = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblQuestion.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblQuestion, org.openide.util.NbBundle.getMessage(FormConfirm.class, "FormConfirm.lblQuestion.text")); // NOI18N

        btnOK.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnOK, org.openide.util.NbBundle.getMessage(FormConfirm.class, "FormConfirm.btnOK.text")); // NOI18N

        btnCancel.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnCancel, org.openide.util.NbBundle.getMessage(FormConfirm.class, "FormConfirm.btnCancel.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblQuestion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK)
                    .addComponent(btnCancel))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        hideFormConfirm();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblQuestion;
    // End of variables declaration//GEN-END:variables
}
