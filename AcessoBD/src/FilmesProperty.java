package com.softgraf.consultasql.fx;

import java.util.Date;

public class FilmesProperty {
	
	private String codigo, titulo, genero, produtora;
	private Date dataCompra;
	
	public FilmesProperty() {
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getProdutora() {
		return produtora;
	}

	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public FilmesProperty(String codigo, String titulo, String genero, String produtora, Date dataCompra) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.genero = genero;
		this.produtora = produtora;
		this.dataCompra = dataCompra;
	}
	
	
}
