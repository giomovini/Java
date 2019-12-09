/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.tabelas_banco;

import dados.conexao_banco.BancoConexao;
import dados.entidades.CandidatoDoacao;
import dados.entidades.DoadorEstado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Uchiha
 */
public class TabelaDoador {

    public BancoConexao bd = BancoConexao.getInstance();
    public PreparedStatement stmt;

    private static TabelaDoador Instancia;

    public static synchronized TabelaDoador getInstance() {

        if (Instancia == null) {
            Instancia = new TabelaDoador();
        }
        return Instancia;
    }

    private TabelaDoador() {
    }

    public boolean gravarDadosDoador(CandidatoDoacao Doador) {
        System.out.println(this.getClass() + " - method: gravarDadosDoador ");

        String sql = "INSERT INTO DOADOR "
                + "( NOME, NASCIMENTO, SEXO ,NOME_PAI ,NOME_MAE ,RG ,ENDERECO,CEP ,ESTADO ,CIDADE,BAIRRO,TELEFONE) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            stmt = bd.getConexao().prepareStatement(sql);
            stmt.setString(1, Doador.getNome());
            stmt.setString(2, Doador.getNascimento());
            stmt.setString(3, Doador.getSexo());
            stmt.setString(4, Doador.getNomePai());
            stmt.setString(5, Doador.getNomeMae());
            stmt.setString(6, Doador.getRG());
            stmt.setString(7, Doador.getEndereco());
            stmt.setString(8, Doador.getCep());
            stmt.setString(9, Doador.getEstado());
            stmt.setString(10, Doador.getCidade());
            stmt.setString(11, Doador.getBairro());
            stmt.setString(12, Doador.getTelefone());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean editarDoador(CandidatoDoacao Doador) {
        System.out.println(this.getClass() + " - method: editarDoador ");
        Statement stmt = null;
        try {
            stmt = bd.getConexao().createStatement();
            String sql = "UPDATE doador SET";
            sql += " NOME = '" + Doador.getNome() + "'";
            sql += ", NASCIMENTO  = '" + Doador.getNascimento() + "'";
            sql += ", SEXO = '" + Doador.getSexo() + "'";
            sql += ", NOME_PAI = '" + Doador.getNomePai() + "'";
            sql += ", NOME_MAE = '" + Doador.getNomeMae() + "'";
            sql += ", RG = '" + Doador.getRG() + "'";
            sql += ", ENDERECO = '" + Doador.getEndereco() + "'";
            sql += ", CEP = '" + Doador.getCep() + "'";
            sql += ", ESTADO = '" + Doador.getEstado() + "'";
            sql += ", CIDADE = '" + Doador.getCidade() + "'";
            sql += ", BAIRRO = '" + Doador.getBairro() + "'";
            sql += ", TELEFONE = '" + Doador.getTelefone() + "'";
            sql += " WHERE IDENTIFICADOR = " + Doador.getCodigoIdentificador();

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return true;
    }
    
    
     public ArrayList<DoadorEstado> buscarDadosDoadoresEstado() {
        System.out.println(this.getClass() + " - method: buscarTelefoneDoador ");
        String sql = "SELECT count(*) as qtde,ESTADO FROM doador GROUP by ESTADO";
        ArrayList<DoadorEstado> lista = new ArrayList<>();
        try {
            stmt = bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DoadorEstado result = new DoadorEstado();
                result.setQuantidade(rs.getInt("qtde"));
                result.setUF(rs.getString("ESTADO"));
                lista.add(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
     
     
     
    
    public String buscarDadosSexoDoadores() {
        System.out.println(this.getClass() + " - method: buscarDadosSexoDoadores ");
        String sql = "SELECT COUNT(*) as QTDE FROM doador WHERE SEXO = 'F'";
        String Resultado= "";
        try {
            stmt = bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Resultado = String.valueOf(rs.getInt("QTDE"))+"#";
            }else{
                Resultado = "0#";
            }
             sql = "SELECT COUNT(*) as QTDE FROM doador WHERE SEXO = 'M'";
            
            stmt = bd.getConexao().prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Resultado += String.valueOf(rs.getInt("QTDE"));
            }else{
                Resultado +="0";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resultado;
    }

    public CandidatoDoacao buscarInformacoesDoador(Integer idDoacao) {
        System.out.println(this.getClass() + " - method: buscarTelefoneDoador ");
        String sql = "SELECT telefone,endereco,cidade,estado,cep,bairro FROM doacao join doador on doacao.ID_DOADOR = doador.IDENTIFICADOR where doacao.IDENTIFICADOR = " + idDoacao;
        CandidatoDoacao Doador = null;
        try {
            stmt = bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Doador = new CandidatoDoacao();
                Doador.setCidade(rs.getString("CIDADE"));
                Doador.setEstado(rs.getString("ESTADO"));
                Doador.setBairro(rs.getString("BAIRRO"));
                Doador.setCep(rs.getString("CEP"));
                Doador.setEndereco(rs.getString("ENDERECO"));
                Doador.setTelefone(rs.getString("TELEFONE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Doador;
    }

    public ArrayList<CandidatoDoacao> buscarDoador(String nome, Integer id) {
        System.out.println(this.getClass() + " - method: buscarDoador ");
        ArrayList<CandidatoDoacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM DOADOR";
        if (!nome.equals("") && id == 0) {
            sql += " where NOME like '" + nome + "%'";
        }
        if (nome.equals("") && id > 0) {
            sql += " where IDENTIFICADOR = " + id;
        }
        if (!nome.equals("") && id > 0) {
            sql += " where NOME like '" + nome + "%' and IDENTIFICADOR = " + id;
        }

        try {
            stmt = bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                CandidatoDoacao Doador = new CandidatoDoacao();

                Doador.setCodigoIdentificador(rs.getInt("IDENTIFICADOR"));
                Doador.setNome(rs.getString("NOME"));
                Doador.setNascimento(rs.getString("NASCIMENTO"));
                Doador.setSexo(rs.getString("SEXO"));
                Doador.setNomePai(rs.getString("NOME_PAI"));
                Doador.setNomeMae(rs.getString("NOME_MAE"));
                Doador.setRG(rs.getString("RG"));
                Doador.setCidade(rs.getString("CIDADE"));
                Doador.setEstado(rs.getString("ESTADO"));
                Doador.setBairro(rs.getString("BAIRRO"));
                Doador.setCep(rs.getString("CEP"));
                Doador.setEndereco(rs.getString("ENDERECO"));
                Doador.setTelefone(rs.getString("TELEFONE"));

                lista.add(Doador);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean deletarDoador(Integer id) {

        try {
            stmt = bd.getConexao().prepareStatement("DELETE FROM doador WHERE IDENTIFICADOR = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
