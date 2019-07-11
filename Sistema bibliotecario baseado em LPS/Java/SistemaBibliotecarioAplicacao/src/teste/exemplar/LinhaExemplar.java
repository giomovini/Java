package teste.exemplar;

import gerenciador_exemplares.Exemplar;

public class LinhaExemplar {
	
	Exemplar Ex;
	String Titulo,Idioma,Edicao,anoLancamento,qtdeDisponivel,Categoria,classificacaoIdade,Localizacao,Observacoes,codBarras,autor;


	public Exemplar getEx() {
		return Ex;
	}

	public void setEx(Exemplar ex) {
		Ex = ex;
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

	public String getEdicao() {
		return Edicao;
	}

	public void setEdicao(String edicao) {
		Edicao = edicao;
	}

	public String getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getQtdeDisponivel() {
		return qtdeDisponivel;
	}

	public void setQtdeDisponivel(String qtdeDisponivel) {
		this.qtdeDisponivel = qtdeDisponivel;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}

	public String getClassificacaoIdade() {
		return classificacaoIdade;
	}

	public void setClassificacaoIdade(String classificacaoIdade) {
		this.classificacaoIdade = classificacaoIdade;
	}

	public String getLocalizacao() {
		return Localizacao;
	}

	public void setLocalizacao(String localizacao) {
		Localizacao = localizacao;
	}

	public String getObservacoes() {
		return Observacoes;
	}

	public void setObservacoes(String observacoes) {
		Observacoes = observacoes;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	
}
