package tela;


/**
 *
 * @author henry
 */
public class VelhaTela extends javax.swing.JFrame {

    /**
     * Creates new form VelhaTela
     */
    public VelhaTela() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        botao0 = new javax.swing.JButton();
        botao1 = new javax.swing.JButton();
        botao4 = new javax.swing.JButton();
        botao3 = new javax.swing.JButton();
        botao5 = new javax.swing.JButton();
        botao2 = new javax.swing.JButton();
        botao7 = new javax.swing.JButton();
        botao6 = new javax.swing.JButton();
        botao8 = new javax.swing.JButton();
        campoAtualizacoes = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        limpar = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo da Velha 1.0");
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);

        botao0.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        botao0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao0ActionPerformed(evt);
            }
        });

        botao1.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        botao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao1ActionPerformed(evt);
            }
        });

        botao4.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        botao4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao4ActionPerformed(evt);
            }
        });

        botao3.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        botao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao3ActionPerformed(evt);
            }
        });

        botao5.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        botao5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao5ActionPerformed(evt);
            }
        });

        botao2.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        botao2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao2ActionPerformed(evt);
            }
        });

        botao7.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        botao7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao7ActionPerformed(evt);
            }
        });

        botao6.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        botao6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao6ActionPerformed(evt);
            }
        });

        botao8.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        botao8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao8ActionPerformed(evt);
            }
        });

        campoAtualizacoes.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        campoAtualizacoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        campoAtualizacoes.setText("Player 1");
        campoAtualizacoes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        campoAtualizacoes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        campoAtualizacoes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        campoAtualizacoes.setAlignmentX(CENTER_ALIGNMENT);

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 0, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Developed by Saldanha Technology ®");
        jLabel1.setToolTipText("");

        limpar.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        limpar.setText("Try again");
        limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botao3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botao0, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botao2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botao5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botao6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botao7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botao8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(limpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(campoAtualizacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(limpar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(campoAtualizacoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botao0, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(botao2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(botao3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(botao4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botao5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(botao1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botao7, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(botao6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao1ActionPerformed
      if(botao1.getText().equals("")){
        botao1.setText(jogar());
       resultado();
      }
    }//GEN-LAST:event_botao1ActionPerformed

    private void botao0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao0ActionPerformed
       if(botao0.getText().equals("")){
        botao0.setText(jogar());
        resultado();
       }
        
        
        
    }//GEN-LAST:event_botao0ActionPerformed

    private void botao2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao2ActionPerformed
        
          if(botao2.getText().equals("")){
        botao2.setText(jogar());
        resultado();
       }
    }//GEN-LAST:event_botao2ActionPerformed

    private void botao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao3ActionPerformed
        if(botao3.getText().equals("")){
        botao3.setText(jogar());
        resultado();
       }
    }//GEN-LAST:event_botao3ActionPerformed

    private void botao4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao4ActionPerformed
        if(botao4.getText().equals("")){
        botao4.setText(jogar());
        resultado();
       }
    }//GEN-LAST:event_botao4ActionPerformed

    private void botao5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao5ActionPerformed
          if(botao5.getText().equals("")){
        botao5.setText(jogar());
        resultado();
        
        
       }
       
    }//GEN-LAST:event_botao5ActionPerformed

    private void botao6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao6ActionPerformed
          if(botao6.getText().equals("")){
        botao6.setText(jogar());
        resultado();
       }
    }//GEN-LAST:event_botao6ActionPerformed

    private void botao7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao7ActionPerformed
           if(botao7.getText().equals("")){
        botao7.setText(jogar());
        resultado();
       }
    }//GEN-LAST:event_botao7ActionPerformed

    private void botao8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao8ActionPerformed
          if(botao8.getText().equals("")){
        botao8.setText(jogar());
        resultado();
       }
     
    }//GEN-LAST:event_botao8ActionPerformed

    private void limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparActionPerformed
      botao0.setText("");
      botao1.setText("");
      botao2.setText("");
      botao3.setText("");
      botao4.setText("");
      botao5.setText("");
      campoAtualizacoes.setForeground(java.awt.Color.BLACK);
      botao6.setText("");
      botao7.setText("");
      botao8.setText(""); 
      i=2;
      jogadas =0;
      ganhou =0;
      campoAtualizacoes.setText("Player 1");
    }//GEN-LAST:event_limparActionPerformed
    
    
    static int i =2;
    static int jogadas;
    static int aux;
    public static int ganhou=0;
    static String x = "X";
    static String o = "O";
    
    public static String jogar(){
        String a = "";
        if(ganhou ==0){
                if((i%2)== 0){
                    aux = 2;
                    a= x;
                    
                }
                else{
                    aux = 1;
                    a= o;
                }
            campoAtualizacoes.setText("Player "+ aux);

            i++;
            jogadas++;
        
        }
        
        return a;
        
    }
     public static void resultado(){
       int cont =0;
       
       String txt = null;
       String player = "Player ";
       String win = " Win";
       
       
       while(cont<2){
            if(cont==0){
                txt=x;
            }else{
                txt=o;
            }
            
           
          if(botao0.getText().equals(txt)  && botao1.getText().equals(txt) && botao2.getText().equals(txt)){
              jogar();
              campoAtualizacoes.setText(player+aux+win);
              campoAtualizacoes.setForeground(java.awt.Color.red);
              ganhou =1;
              
          }
          else if(botao3.getText().equals(txt)  && botao4.getText().equals(txt) && botao5.getText().equals(txt)){
              jogar();
              campoAtualizacoes.setText(player+aux+win);
              campoAtualizacoes.setForeground(java.awt.Color.red);
              ganhou =1;
          }
          else if(botao6.getText().equals(txt)  && botao7.getText().equals(txt) && botao8.getText().equals(txt)){
              jogar();
              campoAtualizacoes.setText(player+aux+win);
              campoAtualizacoes.setForeground(java.awt.Color.red);
              ganhou =1;
          }
          else if(botao0.getText().equals(txt)  && botao3.getText().equals(txt) && botao6.getText().equals(txt)){
              jogar();
              campoAtualizacoes.setText(player+aux+win); 
              campoAtualizacoes.setForeground(java.awt.Color.red);
              ganhou =1;
          }
          else if(botao1.getText().equals(txt)  && botao4.getText().equals(txt) && botao7.getText().equals(txt)){
              jogar();
              campoAtualizacoes.setText(player+aux+win);
              campoAtualizacoes.setForeground(java.awt.Color.red);
              ganhou =1;
          }
          else if(botao2.getText().equals(txt)  && botao5.getText().equals(txt) && botao8.getText().equals(txt)){
              jogar();
              campoAtualizacoes.setText(player+aux+win);
              campoAtualizacoes.setForeground(java.awt.Color.red);
              ganhou =1;
          }
          else if(botao0.getText().equals(txt)  && botao4.getText().equals(txt) && botao8.getText().equals(txt)){
              jogar();
              campoAtualizacoes.setText(player+aux+win); 
              campoAtualizacoes.setForeground(java.awt.Color.red);
              ganhou =1;
          }
          else if(botao6.getText().equals(txt)  && botao4.getText().equals(txt) && botao2.getText().equals(txt)){
              jogar();
              campoAtualizacoes.setText(player+aux+win); 
              campoAtualizacoes.setForeground(java.awt.Color.red);
              ganhou =1;
          }
          if(jogadas ==9 && ganhou ==0){
              campoAtualizacoes.setText("Game Over");
              campoAtualizacoes.setForeground(java.awt.Color.red);
          }
         
         
        cont++;
       }
       
        
    }
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(VelhaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VelhaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VelhaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VelhaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VelhaTela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton botao0;
    public static javax.swing.JButton botao1;
    public static javax.swing.JButton botao2;
    public static javax.swing.JButton botao3;
    public static javax.swing.JButton botao4;
    public static javax.swing.JButton botao5;
    public static javax.swing.JButton botao6;
    public static javax.swing.JButton botao7;
    public static javax.swing.JButton botao8;
    private static javax.swing.JLabel campoAtualizacoes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JButton limpar;
    // End of variables declaration//GEN-END:variables
}
