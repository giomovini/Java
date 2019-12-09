/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dados.entidades.DoadorEstado;
import dados.tabelas_banco.TabelaDoacao;
import dados.tabelas_banco.TabelaDoador;
import java.time.Year;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author UCHIHA
 */
public class Graficos {

    private static Graficos Instancia;
    private final SistemaDoacaoSangueController Controlle;

    public static synchronized Graficos getInstance() {
        return Instancia;
    }

    public Graficos(SistemaDoacaoSangueController Controlle) {
        Instancia = this;
        this.Controlle = Controlle;
    }

    public void atualizarGraficos() {
        atualizarGraficoPizza();
        atualizarGraficoBarra();
        atualizarGraficoLinha();
    }

    private void atualizarGraficoPizza() {
        System.out.println(this.getClass() + " - method: graficoPizza");

        Controlle.GraficoPizza.getData().removeAll(Controlle.GraficoPizza.getData());
        Controlle.GraficoPizza.setTitle("Relação de doadores por estado");

        double total = 0;
        ArrayList<DoadorEstado> listaBanco = TabelaDoador.getInstance().buscarDadosDoadoresEstado();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        listaBanco.forEach(e -> {
            pieChartData.add(new PieChart.Data(e.getUF(), e.getQuantidade()));
        });

        Controlle.GraficoPizza.setData(pieChartData);

        for (PieChart.Data data : pieChartData) {
            total += data.getPieValue();
        }

        for (PieChart.Data data : Controlle.GraficoPizza.getData()) {
            total = (total == 0) ? 1 : total;
            final double porcentagem = (data.getPieValue() / total) * 100;
            data.getNode().setOnMouseEntered(e -> {
                Controlle.lblPorcentagem.setText(data.getName() + ": " + String.valueOf(porcentagem) + "%");
            });

            data.getNode().setOnMouseExited(e -> {
                Controlle.lblPorcentagem.setText("");
            });
        }

    }

    private void atualizarGraficoLinha() {
        System.out.println(this.getClass() + " - method: graficoLinha");

        Controlle.GraficoLinha.getData().removeAll(Controlle.GraficoLinha.getData());
        Controlle.GraficoLinha.setTitle("Doações por mês");

        ArrayList<String> dados = TabelaDoacao.getInstance().buscarDadosDoacaoPorMes();

        XYChart.Series series = new XYChart.Series();
        series.setName(String.valueOf(Year.now().getValue()));

        dados.forEach((dado) -> {
            series.getData().add(new XYChart.Data(dado.split("#")[0], Integer.valueOf(dado.split("#")[1])));
        });

        Controlle.GraficoLinha.getData().add(series);
    }

    private void atualizarGraficoBarra() {
        System.out.println(this.getClass() + " - method: graficoBarra");

        Controlle.GraficoBarra.getData().removeAll(Controlle.GraficoBarra.getData());
        Controlle.GraficoBarra.setTitle("Relação de doadores por sexo");

        String dados = TabelaDoador.getInstance().buscarDadosSexoDoadores();
        String[] sexo = dados.split("#");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Masculino");
        series1.getData().add(new XYChart.Data("Masculino", Integer.valueOf(sexo[1])));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Feminino");
        series2.getData().add(new XYChart.Data("Feminino", Integer.valueOf(sexo[0])));

        Controlle.GraficoBarra.getData().addAll(series1, series2);
    }

}
