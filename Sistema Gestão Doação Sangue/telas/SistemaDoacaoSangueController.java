/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dados.conexao_banco.AcessoBanco;
import dados.conexao_banco.BancoConexao;
import dados.conexao_banco.CriacaoTabelas;
import dados.entidades.CandidatoDoacao;
import dados.entidades.Doacao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Uchiha
 */
public class SistemaDoacaoSangueController implements Initializable {

    @FXML
    public Label lblConexao, lblPorcentagem;
    @FXML
    public TextField txtIpServer, txtUserServer, txtPassword, txtPortaServer;
    @FXML
    public Button btnConectar, btnLimparCon, btnLimparDoador, btnGravarDoador, btnDeletarDoador;
    @FXML
    public Tab TabDoador, TabDoacao, TabEstatistica;
    @FXML
    public DatePicker DateNascimento;
    @FXML
    public PieChart GraficoPizza;
    @FXML
    public BarChart GraficoBarra;
    @FXML
    public LineChart GraficoLinha;
    @FXML
    public TableView<CandidatoDoacao> TblDoador;
    @FXML
    public TableColumn<CandidatoDoacao, Integer> colIdentificador;
    @FXML
    public TableColumn<CandidatoDoacao, String> colNascimento, colNome, colSexo, colRG, colNomePai, colMae, colEndereco;
    @FXML
    public RadioButton radioMasculino, radioFeminino;
    @FXML
    public TextField txtNome, txtBairro, txtCidade, txtEndereco, txtCEP, txtNomePai, txtNomeMae, txtRG,txtTelefone, txtIdentificadorBusca, txtNomeBusca;
    @FXML
    public TextField txtNomeBuscaDoacao, txtIdentificadorBuscaDoacao,txtNomeBuscaDoadorDoacao;
    @FXML
    public MenuButton MenuEstado, MenuTipoSangue;
    @FXML
    public TableView<Doacao> TblDoacao;
    @FXML
    public TableColumn<Doacao, Integer> colIdentificadorDoacao;
    @FXML
    public TableColumn<Doacao, String> colNomeDoacao, colDataDoacao;
    @FXML
    public TableView<CandidatoDoacao> TblDoadorDoacao;
    @FXML
    public TableColumn<CandidatoDoacao, Integer> colIdentificadorTabelaBusca;
    @FXML
    public TableColumn<CandidatoDoacao, String> colNomeTabelaBusca;
    @FXML
    public DatePicker DateDoacao;
    @FXML
    public Button btnGravarDoacao, btnDeletarDoacao;
    @FXML
    public RadioButton HepatiteBPositivo, HepatiteBNegativo;
    @FXML
    public RadioButton HepatiteCPositivo, HepatiteCNegativo, DoencaChagasPositivo, DoencaChagasNegativo;
    @FXML
    public RadioButton SifilisPositivo, SifilisNegativo, AidsPositivo, AidsNegativo, HTLVIPositivo, HTLVINegativo;
    @FXML
    public RadioButton HTLVIIPositivo, HTLVIINegativo, AnticorposPositivo, AnticorposNegativo, RHPositivo, RHNegativo;
    @FXML
    public RadioButton AnemiaPositivo, AnemiaNegativo, TriagemAprovado, TriagemReprovado;
    public CandidatoDoacao DoadorGravar;
    public Doacao DoacaoGravar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TabDoacao.setDisable(true);
        TabDoador.setDisable(true);
        TabEstatistica.setDisable(true);

        TelaDoador telaDoador = new TelaDoador(this);
        TelaDoacao telaDoacao = new TelaDoacao(this);
        Graficos graficos = new Graficos(this);
        
        telaDoador.propriedadesTelaDoador();
        telaDoador.selecionarRegistroDoador();

        telaDoacao.configuracaoTelaDoacao();
        telaDoacao.selecionarRegistroDoacao();
        telaDoador.selecionarRegistroDoador();

    }


    @FXML
    public void filtraListaDoador() {
        TelaDoador.getInstance().filtraListaDoador();
    }

    @FXML
    public void filtraListaDoadorDoacao() {
       TelaDoacao.getInstance().filtraListaDoadorDoacao();
    }

    @FXML
    private void filtrarListaDoacao() {
      TelaDoacao.getInstance().filtrarListaDoacao();
    }

    @FXML
    private void conectarBanco(ActionEvent event) {
        System.out.println(this.getClass() + " - method: conectarBanco");

        AcessoBanco acesso = AcessoBanco.getInstance();
        acesso.setProprieties(txtUserServer.getText(), txtPassword.getText(), txtIpServer.getText(), txtPortaServer.getText());
        BancoConexao.getInstance();

        CriacaoTabelas Tabelas = new CriacaoTabelas();
        Tabelas.criarTabelas();
        TabDoador.setDisable(false);
        TabDoacao.setDisable(false);
        TabEstatistica.setDisable(false);
        lblConexao.setVisible(true);
        ConfiguracoesTela c = new ConfiguracoesTela();
        c.atualizacaoDadosTabelasGraficos();

    }

    @FXML
    private void limparCamposCon(ActionEvent event) {
        System.out.println(this.getClass() + " - method: limparCampos");
        txtIpServer.setText("");
        txtUserServer.setText("");
        txtPassword.setText("");
        txtPortaServer.setText("");
    }

    @FXML
    private void limparCamposDoador(ActionEvent event) {
        TelaDoador.getInstance().limparDadosDoador();
    }

    @FXML
    private void btnGravarDoador(ActionEvent event) {
        TelaDoador.getInstance().gravarDoador();
    }

    @FXML
    private void btnDeletarDoador(ActionEvent event) {
      TelaDoador.getInstance().deletarDoador();
    }

    @FXML
    private void btnGravarDoacao(ActionEvent event) {
     TelaDoacao.getInstance().gravarDoacao();
    }

    @FXML
    private void btnDeletarDoacao(ActionEvent event) {
        TelaDoacao.getInstance().deletarDoacao();
    }

    @FXML
    private void btnLimparDoacao(ActionEvent event) {
        TelaDoacao.getInstance().limparDoacao();

    }

    @FXML
    private void btnLigarDoador(ActionEvent event) {
        TelaDoador.getInstance().ligarDoador();
    }

}
