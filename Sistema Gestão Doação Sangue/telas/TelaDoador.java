/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dados.entidades.CandidatoDoacao;
import dados.entidades.Estado;
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
public class TelaDoador extends ConfiguracoesTela {

    private static TelaDoador Instancia;
    private final SistemaDoacaoSangueController Controlle;

    public static synchronized TelaDoador getInstance() {
        return Instancia;
    }

    public TelaDoador(SistemaDoacaoSangueController Controlle) {
        Instancia = this;
        this.Controlle = Controlle;

    }

    public void ligarDoador() {
        System.out.println(this.getClass() + " - method: btnLigarDoador ");
        if (Controlle.TblDoacao.getSelectionModel().getSelectedItem() != null) {
            CandidatoDoacao Doador = TabelaDoador.getInstance().buscarInformacoesDoador(Controlle.DoacaoGravar.getCodigoIdentificador());
            String endereco = Doador.getEndereco() + " " + Doador.getBairro() + " " + Doador.getCep() + " " + Doador.getCidade() + " " + Doador.getEstado();
            JOptionPane.showMessageDialog(null, "Doador: " + Controlle.DoacaoGravar.getNomeDoador() + "\nLigar para: " + Doador.getTelefone() + "\nEnviar Carta Registrada: " + endereco);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um registro da tabela!");
        }

    }

    public void limparDadosDoador() {
        Controlle.txtNome.setText("");
        Controlle.radioFeminino.setSelected(false);
        Controlle.radioMasculino.setSelected(false);
        Controlle.txtRG.setText("");
        Controlle.DateNascimento.setValue(null);
        Controlle.txtNomeMae.setText("");
        Controlle.txtNomePai.setText("");
        Controlle.txtCEP.setText("");
        Controlle.txtEndereco.setText("");
        Controlle.txtTelefone.setText("");
        Controlle.txtBairro.setText("");
        Controlle.txtCidade.setText("");
        Controlle.MenuEstado.setText("SELECIONE");
        Controlle.DoadorGravar = null;
    }

    public void propriedadesTelaDoador() {
        acaoRadioButton(Controlle.radioMasculino, Controlle.radioFeminino);
        Controlle.colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        Controlle.colSexo.setCellValueFactory(new PropertyValueFactory<>("Sexo"));
        Controlle.colRG.setCellValueFactory(new PropertyValueFactory<>("RG"));
        Controlle.colNomePai.setCellValueFactory(new PropertyValueFactory<>("NomePai"));
        Controlle.colEndereco.setCellValueFactory(new PropertyValueFactory<>("Endereco"));
        Controlle.colMae.setCellValueFactory(new PropertyValueFactory<>("NomeMae"));
        Controlle.colNascimento.setCellValueFactory(new PropertyValueFactory<>("Nascimento"));
        Controlle.colIdentificador.setCellValueFactory(new PropertyValueFactory<>("CodigoIdentificador"));

        Controlle.MenuEstado.getItems().forEach(e -> {
            e.setOnAction(w -> Controlle.MenuEstado.setText(e.getText()));
        });

        addTextLimiter(Controlle.txtCEP, 8);
        addTextLimiter(Controlle.txtTelefone, 11);
    }

    public void selecionarRegistroDoador() {
        Controlle.TblDoador.setOnMouseClicked(e -> {
            Controlle.DoadorGravar = Controlle.TblDoador.getSelectionModel().getSelectedItem();
            if (Controlle.DoadorGravar != null) {

                Controlle.txtNome.setText(Controlle.DoadorGravar.getNome());
                Controlle.radioFeminino.setSelected(Controlle.DoadorGravar.getSexo().equals("FEMININO"));
                Controlle.radioMasculino.setSelected(Controlle.DoadorGravar.getSexo().equals("MASCULINO"));
                Controlle.txtNomeMae.setText(Controlle.DoadorGravar.getNomeMae());
                Controlle.txtNomePai.setText(Controlle.DoadorGravar.getNomePai());
                Controlle.txtRG.setText(Controlle.DoadorGravar.getRG());
                Controlle.txtCEP.setText(Controlle.DoadorGravar.getCep());
                Controlle.txtTelefone.setText(Controlle.DoadorGravar.getTelefone());
                Controlle.txtEndereco.setText(Controlle.DoadorGravar.getEndereco().split("\n")[0]);
                Controlle.txtBairro.setText(Controlle.DoadorGravar.getBairro());
                Controlle.txtCidade.setText(Controlle.DoadorGravar.getCidade());
                String[] s = Controlle.DoadorGravar.getNascimento().split("/");
                Controlle.DateNascimento.setValue(LocalDate.parse(s[2] + "-" + s[1] + "-" + s[0]));
                Controlle.MenuEstado.setText(Estado.getInstance().nomeEstado(Controlle.DoadorGravar.getEstado()));
            }
        });
    }

    public boolean validacaoDoador(CandidatoDoacao Doador) {
        String erro = "";
        if (Doador.getNome().equals("")) {
            erro += "Nome";
        }

        if (Doador.getSexo() == null || (Doador.getSexo() != null && Doador.getSexo().equals(""))) {
            erro += ", Sexo";
        }

        if (Doador.getRG().equals("")) {
            erro += ", RG";
        }

        if (Doador.getNascimento() == null) {
            erro += ", Nascimento";
        }

        if (Doador.getNomePai().equals("")) {
            erro += ", Pai";
        }

        if (Doador.getNomeMae().equals("")) {
            erro += ", Mãe";
        }

        if (Doador.getCep().equals("")) {
            erro += ", CEP";
        }

        if (Doador.getEndereco().equals("")) {
            erro += ", Endereço";
        }

        if (Doador.getTelefone().equals("")) {
            erro += ", Telefone";
        }

        if (Doador.getBairro().equals("")) {
            erro += ", Bairro";
        }

        if (Doador.getCidade().equals("")) {
            erro += ", Cidade";
        }

        if (Doador.getEstado().equals("")) {
            erro += ", Estado";
        }

        if (erro.startsWith(",")) {
            erro = erro.substring(1);
        }

        if (erro.equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "O(s) campo(s) devem ser preenchidos: " + erro);
            return false;
        }
    }

    public void filtraListaDoador() {
        ObservableList<CandidatoDoacao> ListaDoador = FXCollections.observableArrayList();
        ArrayList<CandidatoDoacao> listaFiltro = TabelaDoador.getInstance().buscarDoador(Controlle.txtNomeBusca.getText(), Integer.parseInt(Controlle.txtIdentificadorBusca.getText().equals("") ? "0" : Controlle.txtIdentificadorBusca.getText()));
        listaFiltro.forEach((item) -> {
            String[] s = item.getNascimento().split("-");
            String novaData = s[2] + "/" + s[1] + "/" + s[0];
            item.setNascimento(novaData);
            item.setSexo(item.getSexo().equals("F") ? "FEMININO" : "MASCULINO");
            item.setEndereco(item.getEndereco() + "\n" + item.getBairro() + " " + item.getCep() + " " + item.getCidade() + " " + item.getEstado());
            ListaDoador.add(item);
        });
        Controlle.TblDoador.setItems(ListaDoador);

    }

    public void gravarDoador() {

        if (Controlle.DoadorGravar == null) {
            Controlle.DoadorGravar = new CandidatoDoacao();
        }

        Controlle.DoadorGravar.setNome(Controlle.txtNome.getText());
        if (Controlle.radioMasculino.isSelected()) {
            Controlle.DoadorGravar.setSexo("M");
        } else if (Controlle.radioFeminino.isSelected()) {
            Controlle.DoadorGravar.setSexo("F");
        }

        Controlle.DoadorGravar.setRG(Controlle.txtRG.getText());
        Controlle.DoadorGravar.setNomePai(Controlle.txtNomePai.getText());
        Controlle.DoadorGravar.setNomeMae(Controlle.txtNomeMae.getText());
        Controlle.DoadorGravar.setCep(Controlle.txtCEP.getText());
        Controlle.DoadorGravar.setEndereco(Controlle.txtEndereco.getText());
        Controlle.DoadorGravar.setTelefone(Controlle.txtTelefone.getText());
        Controlle.DoadorGravar.setBairro(Controlle.txtBairro.getText());
        Controlle.DoadorGravar.setCidade(Controlle.txtCidade.getText());
        Controlle.DoadorGravar.setEstado(Estado.getInstance().siglaEstado(Controlle.MenuEstado.getText()));

        if (Controlle.DateNascimento.getValue() != null) {
            Controlle.DoadorGravar.setNascimento(Controlle.DateNascimento.getValue().toString());
        } else {
            Controlle.DoadorGravar.setNascimento(null);
        }

        if (validacaoDoador(Controlle.DoadorGravar)) {
            TabelaDoador tab = TabelaDoador.getInstance();
            boolean Sucesso = true;
            if (Controlle.DoadorGravar.getCodigoIdentificador() == null || Controlle.DoadorGravar.getCodigoIdentificador() == 0) {
                Sucesso = tab.gravarDadosDoador(Controlle.DoadorGravar);
            } else {
                Sucesso = tab.editarDoador(Controlle.DoadorGravar);
            }
            JOptionPane.showMessageDialog(null, Sucesso ? "Dados gravados com sucesso!" : "Falha no processo!");

            atualizacaoDadosTabelasGraficos();
        }
    }

    public void deletarDoador() {
        if (Controlle.DoadorGravar == null) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            TabelaDoador.getInstance().deletarDoador(Controlle.DoadorGravar.getCodigoIdentificador());
            atualizacaoDadosTabelasGraficos();
        }
    }

}
