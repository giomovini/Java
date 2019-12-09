/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.conexao_banco;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Uchiha
 */
public class CriacaoTabelas {

    public BancoConexao bd = BancoConexao.getInstance();
    public PreparedStatement Pstmt;

    public void criarTabelas() {
        System.out.println(this.getClass() + " - method: criarTabelas ");
        Statement stmt = null;
        try {
            String arquivo = lerArquivoScript();
            String comandos[] = arquivo.split(";");
            stmt = bd.getConexao().createStatement();
            for (int i = 0; i < comandos.length; i++) {
                stmt.executeUpdate(comandos[i]);
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            bd.erroTabela(ex, "DOADOR");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CriacaoTabelas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String lerArquivoScript() {
        System.out.println(this.getClass() + " - method: lerArquivo ");
        BufferedReader br = null;
        String arquivo = "";
        try {
            br = new BufferedReader(new FileReader("./src/dados/conexao_banco/CriacaoBancoDados.sql"));
            while (br.ready()) {
                arquivo += br.readLine();
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Script de criacao do banco de dados nao encontrado!" + ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura do script de criacao do banco de dados!");
        } finally {

            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(CriacaoTabelas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return arquivo;
    }

}
