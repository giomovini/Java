/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.tabelas_banco;

import dados.conexao_banco.BancoConexao;
import dados.entidades.CandidatoDoacao;
import dados.entidades.Doacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Uchiha
 */
public class TabelaDoacao {

    public BancoConexao bd = BancoConexao.getInstance();
    public PreparedStatement stmt;

    private static TabelaDoacao Instancia;

    public static synchronized TabelaDoacao getInstance() {

        if (Instancia == null) {
            Instancia = new TabelaDoacao();
        }
        return Instancia;
    }

    private TabelaDoacao() {
    }

    public boolean gravarDadosDoacao(Doacao doacao) {
        System.out.println(this.getClass() + " - method: gravarDadosDoacao ");

        String sql = "INSERT INTO DOACAO "
                + "( DATA_DOACAO, "
                + "ID_DOADOR,"
                + "HepatiteB,"
                + "HepatiteC,"
                + "DoencaChagas,"
                + "Sifilis,"
                + "Aids,"
                + "HTLVI,"
                + "HTLVII,"
                + "AnticorposIrregulares,"
                + "TipoSangue,"
                + "RhSangue, "
                + "Anemia,"
                + "Triagem) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            stmt = bd.getConexao().prepareStatement(sql);
            stmt.setString(1, doacao.getDataDoacao());
            stmt.setInt(2, doacao.getDoador().getCodigoIdentificador());

            if (doacao.getHepatiteB() == null) {
                stmt.setNull(3, Types.BOOLEAN);
            } else {
                stmt.setBoolean(3, doacao.getHepatiteB());
            }

            if (doacao.getHepatiteC() == null) {
                stmt.setNull(4, Types.BOOLEAN);
            } else {
                stmt.setBoolean(4, doacao.getHepatiteC());
            }

            if (doacao.getDoencaChagas() == null) {
                stmt.setNull(5, Types.BOOLEAN);
            } else {
                stmt.setBoolean(5, doacao.getDoencaChagas());
            }

            if (doacao.getSifilis() == null) {
                stmt.setNull(6, Types.BOOLEAN);
            } else {
                stmt.setBoolean(6, doacao.getSifilis());
            }

            if (doacao.getAids() == null) {
                stmt.setNull(7, Types.BOOLEAN);
            } else {
                stmt.setBoolean(7, doacao.getAids());
            }

            if (doacao.getHTLVI() == null) {
                stmt.setNull(8, Types.BOOLEAN);
            } else {
                stmt.setBoolean(8, doacao.getHTLVI());
            }

            if (doacao.getHTLVII() == null) {
                stmt.setNull(9, Types.BOOLEAN);
            } else {
                stmt.setBoolean(9, doacao.getHTLVII());
            }

            if (doacao.getAnticorposIrregulares() == null) {
                stmt.setNull(10, Types.BOOLEAN);
            } else {
                stmt.setBoolean(10, doacao.getAnticorposIrregulares());
            }

            if (doacao.getTipoSangue() == null) {
                stmt.setNull(11, Types.VARCHAR);
            } else {
                System.out.println(doacao.getTipoSangue());
                stmt.setString(11, doacao.getTipoSangue());
            }

            if (doacao.getRhSangue() == null) {
                stmt.setNull(12, Types.BOOLEAN);
            } else {
                stmt.setBoolean(12, doacao.getRhSangue());
            }
            if (doacao.getAnemia() == null) {
                stmt.setNull(13, Types.BOOLEAN);
            } else {
                stmt.setBoolean(13, doacao.getAnemia());
            }
            if (doacao.getTriagemClinica() == null) {
                stmt.setNull(14, Types.BOOLEAN);
            } else {
                stmt.setBoolean(14, doacao.getTriagemClinica());
            }

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }
    
     public ArrayList<String>  buscarDadosDoacaoPorMes() {
        System.out.println(this.getClass() + " - method: buscarDadosDoacaoPorMes ");
        String sql = "SELECT MONTH(data_doacao) AS MES, Count(*) as QTDE from doacao GROUP by MONTH(data_doacao)";
        ArrayList<String> lista = new ArrayList<>();
        try {
            stmt = bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(String.valueOf(rs.getInt("MES"))+"#"+String.valueOf(rs.getInt("QTDE")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    

    public boolean editarDoacao(Doacao doacao) {
        System.out.println(this.getClass() + " - method: editarDoacao ");
        Statement stmt = null;
        try {
            stmt = bd.getConexao().createStatement();
            String sql = "UPDATE DOACAO SET";
            sql += " DATA_DOACAO = '" + doacao.getDataDoacao() + "'";
            sql += ", ID_DOADOR  = " + doacao.getDoador().getCodigoIdentificador();
            sql += ", HepatiteB  = " + doacao.getHepatiteB();
            sql += ", HepatiteC  = " + doacao.getHepatiteC();
            sql += ", DoencaChagas  = " + doacao.getDoencaChagas();
            sql += ", Sifilis  = " + doacao.getSifilis();
            sql += ", Aids  = " + doacao.getAids();
            sql += ", HTLVI  = " + doacao.getHTLVI();
            sql += ", HTLVII  = " + doacao.getHTLVII();
            sql += ", AnticorposIrregulares  = " + doacao.getAnticorposIrregulares();
            sql += ", TipoSangue  = '" + doacao.getTipoSangue() + "' ";
            sql += ", RhSangue  = " + doacao.getRhSangue();
            sql += ", Anemia  = " + doacao.getAnemia();
            sql += ", Triagem  = " + doacao.getTriagemClinica();
            sql += " WHERE IDENTIFICADOR = " + doacao.getCodigoIdentificador();

            System.out.println(sql);
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

    public ArrayList<Doacao> buscarDoacao(String nome, Integer id) {
        System.out.println(this.getClass() + " - method: buscarDoacao ");
        ArrayList<Doacao> lista = new ArrayList<>();
        String sql = "SELECT "
                + " doacao.IDENTIFICADOR, "
                + " doacao.DATA_DOACAO, "
                + " doacao.ID_DOADOR,"
                + " doacao.HepatiteB,"
                + " doacao.HepatiteC,"
                + " doacao.DoencaChagas,"
                + " doacao.Sifilis,"
                + " doacao.Aids,"
                + " doacao.HTLVI,"
                + " doacao.HTLVII,"
                + " doacao.AnticorposIrregulares,"
                + " doacao.TipoSangue,"
                + " doacao.RhSangue,"
                + " doacao.Anemia,"
                + " doacao.Triagem,"
                + " doador.NOME"
                + " FROM doacao "
                + " LEFT join doador on doacao.ID_DOADOR = doador.IDENTIFICADOR ";

        if (!nome.equals("") && id == 0) {
            sql += " where doador.NOME like '" + nome + "%'";
        }
        if (nome.equals("") && id > 0) {
            sql += " where doacao.IDENTIFICADOR = " + id;
        }
        if (!nome.equals("") && id > 0) {
            sql += " where doador.NOME like '" + nome + "%' and doacao.IDENTIFICADOR = " + id;
        }

        try {
            stmt = bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                CandidatoDoacao Doador = new CandidatoDoacao();
                Doacao doacao = new Doacao();
                doacao.setNomeDoador(rs.getString("NOME"));
                doacao.setCodigoIdentificador(rs.getInt("IDENTIFICADOR"));
                doacao.setDataDoacao(rs.getString("DATA_DOACAO"));
                doacao.setHepatiteB(returnoBooleanValue(rs, "HepatiteB"));
                doacao.setHepatiteC(returnoBooleanValue(rs, "HepatiteC"));
                doacao.setDoencaChagas(returnoBooleanValue(rs, "DoencaChagas"));
                doacao.setSifilis(returnoBooleanValue(rs, "Sifilis"));
                doacao.setAids(returnoBooleanValue(rs, "Aids"));
                doacao.setHTLVI(returnoBooleanValue(rs, "HTLVI"));
                doacao.setHTLVII(returnoBooleanValue(rs, "HTLVII"));
                doacao.setAnticorposIrregulares(returnoBooleanValue(rs, "AnticorposIrregulares"));
                doacao.setTipoSangue(rs.getString("TipoSangue"));
                doacao.setRhSangue(returnoBooleanValue(rs, "RhSangue"));
                doacao.setAnemia(returnoBooleanValue(rs, "Anemia"));
                doacao.setTriagemClinica(returnoBooleanValue(rs, "Triagem"));
                Doador.setNome(rs.getString("NOME"));
                doacao.setDoador(Doador);

                lista.add(doacao);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Boolean returnoBooleanValue(ResultSet rs, String param) throws SQLException {
        if (rs.getString(param) != null) {
            return rs.getBoolean(param);
        } else {
            return null;
        }

    }

    public Boolean validacaoDoacao(Integer ID_doador) {
        System.out.println(this.getClass() + " - method: validacaoDoacao ");

        String sql = "SELECT  DATEDIFF(CURDATE(),doacao.DATA_DOACAO) as ultima_doacao, doador.SEXO "
                + " FROM doacao "
                + " left join doador on doacao.ID_DOADOR = doador.IDENTIFICADOR "
                + " WHERE ID_DOADOR = " + ID_doador
                + " order by doacao.DATA_DOACAO DESC "
                + "LIMIT 1 ";

        try {
            stmt = bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                if (rs.getInt("ultima_doacao") < 90 && rs.getString("SEXO").equals("M")) {
                    return false;
                }
                if (rs.getInt("ultima_doacao") < 120 && rs.getString("SEXO").equals("F")) {
                    return false;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


    public boolean deletarDoacao(Integer id) {

        try {
            stmt = bd.getConexao().prepareStatement("DELETE FROM DOACAO WHERE IDENTIFICADOR = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
