/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dados.entidades.CandidatoDoacao;
import dados.entidades.Doacao;
import dados.tabelas_banco.TabelaDoacao;
import dados.tabelas_banco.TabelaDoador;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author UCHIHA
 */
public class TelaDoacao extends ConfiguracoesTela {

    private static TelaDoacao Instancia;
    private final SistemaDoacaoSangueController Controlle;

    public static synchronized TelaDoacao getInstance() {
        return Instancia;
    }

    public TelaDoacao(SistemaDoacaoSangueController Controlle) {
        Instancia = this;
        this.Controlle = Controlle;

    }

    public void configuracaoTelaDoacao() {

        // tabela doacao
        Controlle.colIdentificadorDoacao.setCellValueFactory(new PropertyValueFactory<>("CodigoIdentificador"));
        Controlle.colNomeDoacao.setCellValueFactory(new PropertyValueFactory<>("nomeDoador"));
        Controlle.colDataDoacao.setCellValueFactory(new PropertyValueFactory<>("DataDoacao"));

        //tabela doadorDoacao
        Controlle.colNomeTabelaBusca.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        Controlle.colIdentificadorTabelaBusca.setCellValueFactory(new PropertyValueFactory<>("CodigoIdentificador"));

        acaoRadioButton(Controlle.HepatiteCPositivo, Controlle.HepatiteCNegativo);
        acaoRadioButton(Controlle.HepatiteBNegativo, Controlle.HepatiteBPositivo);
        acaoRadioButton(Controlle.DoencaChagasPositivo, Controlle.DoencaChagasNegativo);
        acaoRadioButton(Controlle.SifilisPositivo, Controlle.SifilisNegativo);
        acaoRadioButton(Controlle.AidsPositivo, Controlle.AidsNegativo);
        acaoRadioButton(Controlle.HTLVIPositivo, Controlle.HTLVINegativo);
        acaoRadioButton(Controlle.HTLVIIPositivo, Controlle.HTLVIINegativo);
        acaoRadioButton(Controlle.AnticorposPositivo, Controlle.AnticorposNegativo);
        acaoRadioButton(Controlle.RHPositivo, Controlle.RHNegativo);
        acaoRadioButton(Controlle.AnemiaPositivo, Controlle.AnemiaNegativo);
        acaoRadioButton(Controlle.TriagemAprovado, Controlle.TriagemReprovado);

        Controlle.MenuTipoSangue.getItems().forEach(e -> {
            e.setOnAction(w -> Controlle.MenuTipoSangue.setText(e.getText()));
        });

    }

    public void selecionarRegistroDoacao() {
        Controlle.TblDoacao.setOnMouseClicked(e -> {
            Controlle.DoacaoGravar = Controlle.TblDoacao.getSelectionModel().getSelectedItem();
            if (Controlle.DoacaoGravar != null) {
                Controlle.txtNomeBuscaDoadorDoacao.setText(Controlle.DoacaoGravar.getNomeDoador());
                filtraListaDoadorDoacao();
                Controlle.TblDoadorDoacao.getSelectionModel().select(0);
                String[] s = Controlle.DoacaoGravar.getDataDoacao().split("/");
                Controlle.DateDoacao.setValue(LocalDate.parse(s[2] + "-" + s[1] + "-" + s[0]));
                if (Controlle.DoacaoGravar.getTipoSangue() != null && !Controlle.DoacaoGravar.getTipoSangue().equals("")) {
                    Controlle.MenuTipoSangue.setText(Controlle.DoacaoGravar.getTipoSangue());
                } else {
                    Controlle.MenuTipoSangue.setText("SELECIONE");
                }

                selectRadioButton(Controlle.HepatiteBPositivo, Controlle.HepatiteBNegativo, Controlle.DoacaoGravar.getHepatiteB());
                selectRadioButton(Controlle.HepatiteCPositivo, Controlle.HepatiteCNegativo, Controlle.DoacaoGravar.getHepatiteC());
                selectRadioButton(Controlle.DoencaChagasPositivo, Controlle.DoencaChagasNegativo, Controlle.DoacaoGravar.getDoencaChagas());
                selectRadioButton(Controlle.SifilisPositivo, Controlle.SifilisNegativo, Controlle.DoacaoGravar.getSifilis());
                selectRadioButton(Controlle.AidsPositivo, Controlle.AidsNegativo, Controlle.DoacaoGravar.getAids());
                selectRadioButton(Controlle.HTLVIPositivo, Controlle.HTLVINegativo, Controlle.DoacaoGravar.getHTLVI());
                selectRadioButton(Controlle.HTLVIIPositivo, Controlle.HTLVIINegativo, Controlle.DoacaoGravar.getHTLVII());
                selectRadioButton(Controlle.AnticorposPositivo, Controlle.AnticorposNegativo, Controlle.DoacaoGravar.getAnticorposIrregulares());
                selectRadioButton(Controlle.RHPositivo, Controlle.RHNegativo, Controlle.DoacaoGravar.getRhSangue());
                selectRadioButton(Controlle.AnemiaPositivo, Controlle.AnemiaNegativo, Controlle.DoacaoGravar.getAnemia());
                selectRadioButton(Controlle.TriagemAprovado, Controlle.TriagemReprovado, Controlle.DoacaoGravar.getTriagemClinica());

            }

        });
    }

    public void filtrarListaDoacao() {
        System.out.println(this.getClass() + " - method: filtrarListaDoacao ");
        ObservableList<Doacao> lista = FXCollections.observableArrayList();
        ArrayList<Doacao> listaFiltro = TabelaDoacao.getInstance().buscarDoacao(Controlle.txtNomeBuscaDoacao.getText(), Integer.parseInt(Controlle.txtIdentificadorBuscaDoacao.getText().equals("") ? "0" : Controlle.txtIdentificadorBuscaDoacao.getText()));
        listaFiltro.forEach((item) -> {
            String[] s = item.getDataDoacao().split("-");
            String novaData = s[2] + "/" + s[1] + "/" + s[0];
            item.setDataDoacao(novaData);
            lista.add(item);
        });

        Controlle.TblDoacao.setItems(lista);
    }

    public void filtraListaDoadorDoacao() {
        ObservableList<CandidatoDoacao> ListaDoador = FXCollections.observableArrayList();
        ArrayList<CandidatoDoacao> listaFiltro = TabelaDoador.getInstance().buscarDoador(Controlle.txtNomeBuscaDoadorDoacao.getText(), 0);
        listaFiltro.forEach((item) -> {
            ListaDoador.add(item);
        });
        Controlle.TblDoadorDoacao.setItems(ListaDoador);

    }

    public void limparDoacao() {
        Controlle.txtNomeBuscaDoacao.setText("");
        Controlle.txtNomeBuscaDoadorDoacao.setText("");
        Controlle.txtIdentificadorBuscaDoacao.setText("");
        Controlle.DateDoacao.setValue(null);
        selectRadioButton(Controlle.HepatiteBPositivo, Controlle.HepatiteBNegativo, null);
        selectRadioButton(Controlle.HepatiteCPositivo, Controlle.HepatiteCNegativo, null);
        selectRadioButton(Controlle.DoencaChagasPositivo, Controlle.DoencaChagasNegativo, null);
        selectRadioButton(Controlle.SifilisPositivo, Controlle.SifilisNegativo, null);
        selectRadioButton(Controlle.AidsPositivo, Controlle.AidsNegativo, null);
        selectRadioButton(Controlle.HTLVIPositivo, Controlle.HTLVINegativo, null);
        selectRadioButton(Controlle.HTLVIIPositivo, Controlle.HTLVIINegativo, null);
        selectRadioButton(Controlle.AnticorposPositivo, Controlle.AnticorposNegativo, null);
        selectRadioButton(Controlle.RHPositivo, Controlle.RHNegativo, null);
        selectRadioButton(Controlle.AnemiaPositivo, Controlle.AnemiaNegativo, null);
        selectRadioButton(Controlle.TriagemAprovado, Controlle.TriagemReprovado, null);
        Controlle.MenuTipoSangue.setText("SELECIONE");
        filtraListaDoadorDoacao();
        filtrarListaDoacao();
        Controlle.DoacaoGravar = null;

    }

    public void deletarDoacao() {
        if (Controlle.DoacaoGravar == null) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            TabelaDoacao.getInstance().deletarDoacao(Controlle.DoacaoGravar.getCodigoIdentificador());
            atualizacaoDadosTabelasGraficos();
        }
    }

    public void gravarDoacao() {
        if (Controlle.DoacaoGravar == null) {
            if (Controlle.TblDoadorDoacao.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Selecione um Doador!");
                return;
            } else {
                Controlle.DoacaoGravar = new Doacao();
            }

        }
        if (Controlle.DateDoacao == null || Controlle.DateDoacao.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Preencha a data de doação!");
            return;
        } else {
            if (Controlle.DateDoacao.getValue().compareTo(LocalDate.now()) > 0) {
                JOptionPane.showMessageDialog(null, "A data de doação não pode ser no futuro!");
                return;
            }
        }

        Controlle.DoacaoGravar.setNomeDoador(Controlle.TblDoadorDoacao.getSelectionModel().getSelectedItem().getNome());
        Controlle.DoacaoGravar.setDoador(Controlle.TblDoadorDoacao.getSelectionModel().getSelectedItem());
        Controlle.DoacaoGravar.setDataDoacao(Controlle.DateDoacao.getValue().toString());
        Controlle.DoacaoGravar.setHepatiteB(radioGroupReturn(Controlle.HepatiteBPositivo, Controlle.HepatiteBNegativo));
        Controlle.DoacaoGravar.setHepatiteC(radioGroupReturn(Controlle.HepatiteCPositivo, Controlle.HepatiteCNegativo));
        Controlle.DoacaoGravar.setDoencaChagas(radioGroupReturn(Controlle.DoencaChagasPositivo, Controlle.DoencaChagasNegativo));
        Controlle.DoacaoGravar.setSifilis(radioGroupReturn(Controlle.SifilisPositivo, Controlle.SifilisNegativo));
        Controlle.DoacaoGravar.setAids(radioGroupReturn(Controlle.AidsPositivo, Controlle.AidsNegativo));
        Controlle.DoacaoGravar.setHTLVI(radioGroupReturn(Controlle.HTLVIPositivo, Controlle.HTLVINegativo));
        Controlle.DoacaoGravar.setHTLVII(radioGroupReturn(Controlle.HTLVIIPositivo, Controlle.HTLVIINegativo));
        Controlle.DoacaoGravar.setAnticorposIrregulares(radioGroupReturn(Controlle.AnticorposPositivo, Controlle.AnticorposNegativo));
        Controlle.DoacaoGravar.setRhSangue(radioGroupReturn(Controlle.RHPositivo, Controlle.RHNegativo));
        Controlle.DoacaoGravar.setAnemia(radioGroupReturn(Controlle.AnemiaPositivo, Controlle.AnemiaNegativo));
        Controlle.DoacaoGravar.setTriagemClinica(radioGroupReturn(Controlle.TriagemAprovado, Controlle.TriagemReprovado));
        Controlle.DoacaoGravar.setTipoSangue(Controlle.MenuTipoSangue.getText().equals("SELECIONE") ? "" : Controlle.MenuTipoSangue.getText());

        if (Controlle.DoacaoGravar.getCodigoIdentificador() > 0 || TabelaDoacao.getInstance().validacaoDoacao(Controlle.DoacaoGravar.getDoador().getCodigoIdentificador())) {
            boolean Sucesso = true;

            if (Controlle.DoacaoGravar.getCodigoIdentificador() == 0) {
                Sucesso = TabelaDoacao.getInstance().gravarDadosDoacao(Controlle.DoacaoGravar);
            } else {
                Sucesso = TabelaDoacao.getInstance().editarDoacao(Controlle.DoacaoGravar);
            }
            JOptionPane.showMessageDialog(null, Sucesso ? "Dados gravados com sucesso!" : "Falha no processo!");

        } else {
            JOptionPane.showMessageDialog(null, "O Candidato fez uma doação recentemente!\nNão foi possível cadastrar a doação!");
        }

        atualizacaoDadosTabelasGraficos();

    }
}
