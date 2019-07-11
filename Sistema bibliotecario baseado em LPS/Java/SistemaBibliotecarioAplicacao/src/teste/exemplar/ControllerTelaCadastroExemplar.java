package teste.exemplar;


import gerenciador_exemplares.Autor;
import gerenciador_exemplares.Exemplar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import teste.ControllerTelaInicial;
import teste.Mascaras;
import teste.Tela;

public class ControllerTelaCadastroExemplar {

	@FXML
	TextField Titulo, Idioma, Edicao, QtdeDisp, Categoria, AnoLanc, Localizacao, Classificacao, CodBarras, Autor_txt,
			WebSite;

	@FXML
	Button Salvar, Voltar;

	@FXML
	public void initialize() {

		Voltar.setOnAction(e -> {
			Tela.getInstance().getPalcoSobreposto().close();
		});

		Mascaras.maxField(Edicao, 5);
		Mascaras.mascaraNumeroInteiro(Edicao);
		Mascaras.maxField(AnoLanc, 4);
		Mascaras.mascaraNumeroInteiro(AnoLanc);
		Mascaras.maxField(Classificacao, 4);
		Mascaras.mascaraNumeroInteiro(Classificacao);
		Mascaras.maxField(QtdeDisp, 5);
		Mascaras.mascaraNumeroInteiro(QtdeDisp);

		if (ControllerTelaInicial.editar) {
			atribuiDados(ControllerTelaInicial.Exemplar_);
		}

		Salvar.setOnAction(e -> {

			Exemplar Ex = new Exemplar();
			Ex.setAnoLancamento(AnoLanc.getText().equals("") ? 0 : Integer.parseInt(AnoLanc.getText()));
			Ex.setClassificacaoIdade(
					Classificacao.getText().equals("") ? 0 : Integer.parseInt(Classificacao.getText()));
			Ex.setEdicao(Edicao.getText().equals("") ? 0 : Integer.parseInt(Edicao.getText()));
			Ex.setQtdeDisponivel(QtdeDisp.getText().equals("") ? 0 : Integer.parseInt(QtdeDisp.getText()));
			Ex.setTitulo(Titulo.getText());
			Ex.setIdioma(Idioma.getText());
			Ex.setCategoria(Categoria.getText());
			Ex.setLocalizacao(Localizacao.getText());
			Ex.setCodBarras(CodBarras.getText());

			Autor autor = new Autor();
			autor.setNome(Autor_txt.getText());
			autor.setWebSite(WebSite.getText());

			Ex.setAutor(autor);
			boolean sucess = false;
			if (ControllerTelaInicial.editar) {
				Ex.setIdentificador(ControllerTelaInicial.Exemplar_.getIdentificador());
				Ex.getAutor().setId(ControllerTelaInicial.Exemplar_.getAutor().getId());
				sucess = Ex.editarExemplar(Ex);
			} else {
				sucess = Ex.cadastrarExemplar(Ex);
			}
			if (sucess) {
				ControllerTelaInicial.atualiza = true;
				Tela.getInstance().getPalcoSobreposto().close();
			}
		});

	}

	public void atribuiDados(Exemplar Ex) {
		Titulo.setText(Ex.getTitulo());
		Idioma.setText(Ex.getIdioma());
		Edicao.setText(String.valueOf(Ex.getEdicao()));
		QtdeDisp.setText(String.valueOf(Ex.getQtdeDisponivel()));
		Categoria.setText(Ex.getCategoria());
		Localizacao.setText(Ex.getLocalizacao());
		AnoLanc.setText(String.valueOf(Ex.getAnoLancamento()));
		Classificacao.setText(String.valueOf(Ex.getClassificacaoIdade()));
		CodBarras.setText(Ex.getCodBarras());
		Autor_txt.setText(Ex.getAutor().getNome());
		WebSite.setText(Ex.getAutor().getWebSite());
	}

}
