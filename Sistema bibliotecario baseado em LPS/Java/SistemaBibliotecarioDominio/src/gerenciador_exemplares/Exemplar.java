package gerenciador_exemplares;

import java.util.List;


import javax.swing.JOptionPane;

import teste.exemplar.TabelaExemplar;
import teste.locacao.TabelaLocacao;

public class Exemplar implements IManterExemplar {

	private String Titulo, Idioma, Categoria, Localizacao, codBarras;

	private int identificador, Edicao, anoLancamento, qtdeDisponivel, classificacaoIdade;

	private Autor autor;
	
	private ImprimirResultados impressao;

	@Override
	public boolean validarExemplar(Exemplar Ex) {
		if (Ex.getAutor().getNome().equals("")) {
			return false;
		}
		if (Ex.getAnoLancamento() == 0 || Ex.getEdicao() == 0 || Ex.getQtdeDisponivel() == 0
				|| Ex.getClassificacaoIdade() == 0) {
			return false;
		}
		if (Ex.getTitulo().equals("") || Ex.getIdioma().equals("") || Ex.getCategoria().equals("")
				|| Ex.getCodBarras().equals("")) {
			return false;
		}
		return true;
	}

	@Override
	public List<Exemplar> buscarExemplares(String Titulo, String Autor) {
		return TabelaExemplar.getInstance().buscarExemplar(Titulo, Autor);
	}

	@Override
	public boolean cadastrarExemplar(Exemplar Ex) {

		boolean sucess = Ex.validarExemplar(Ex);
		if (sucess) {
			sucess = TabelaExemplar.getInstance().salvarExemplar(Ex);
		}
		JOptionPane.showMessageDialog(null, sucess ? "Exemplar salvo com sucesso!"
				: "Não foi possível salvar os dados, confira as informações e tente novamente!");

		return sucess;
	}

	@Override
	public boolean editarExemplar(Exemplar Ex) {

		boolean sucess = Ex.validarExemplar(Ex);

		if (sucess) {
			sucess = TabelaExemplar.getInstance().editarExemplar(Ex);
		}

		JOptionPane.showMessageDialog(null, sucess ? "Exemplar salvo com sucesso!"
				: "Não foi possível salvar os dados, confira as informações e tente novamente!");

		return sucess;
	}

	@Override
	public boolean deletarExemplar(Exemplar Ex) {
		boolean sucess = true;
		if (TabelaLocacao.getInstance().buscarLocacoes("", Ex.getTitulo(), "T").size() > 0) {
			sucess = false;
		} else {
			sucess = TabelaExemplar.getInstance().deletarExemplar(Ex);
		}

		JOptionPane.showMessageDialog(null, sucess ? "Registro deletado com sucesso!"
				: "Não foi possivel realizar a deleção do exemplar\nVerifique a existência de uma locação associada com este exemplar!");
		return sucess;
	}

	@Override
	public List<Exemplar> buscarExemplares() {
		return TabelaExemplar.getInstance().buscarLocatarios();
	}

	@Override
	public boolean alterarQtdeDisponivel(Exemplar Ex, int qtde) {
		return TabelaExemplar.getInstance().editarQtdeExemplar(qtde, Ex.getIdentificador());
	}

	

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getIdioma() {
		return Idioma;
	}

	public void setIdioma(String idioma) {
		Idioma = idioma;
	}

	public int getEdicao() {
		return Edicao;
	}

	public void setEdicao(int edicao) {
		Edicao = edicao;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public int getQtdeDisponivel() {
		return qtdeDisponivel;
	}

	public void setQtdeDisponivel(int qtdeDisponivel) {
		this.qtdeDisponivel = qtdeDisponivel;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}

	public int getClassificacaoIdade() {
		return classificacaoIdade;
	}

	public void setClassificacaoIdade(int classificacaoIdade) {
		this.classificacaoIdade = classificacaoIdade;
	}

	public String getLocalizacao() {
		return Localizacao;
	}

	public void setLocalizacao(String localizacao) {
		Localizacao = localizacao;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public ImprimirResultados getImpressao() {
		return impressao;
	}

	public void setImpressao(ImprimirResultados impressao) {
		this.impressao = impressao;
	}

	
	

}
