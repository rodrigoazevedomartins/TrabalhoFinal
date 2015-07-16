package br.edu.ifnmg.tads.trabalhofinalII.DomainModel;

public class Livro {

	private int id;
	private int codigo;
	private String titulo;
	private String dataemprestimo;
	private String datadevolucao;
	private String situacao;
	private int status;
	
	public Livro() {
		id = 0;
		codigo = 0;
		titulo = "";
		dataemprestimo = "";
		datadevolucao = "";
		situacao = "";
		status = 1;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDataemprestimo() {
		return dataemprestimo;
	}


	public void setDataemprestimo(String dataemprestimo) {
		this.dataemprestimo = dataemprestimo;
	}

	public String getDatadevolucao() {
		return datadevolucao;
	}

	public void setDatadevolucao(String datadevolucao) {
		this.datadevolucao = datadevolucao;
	}

	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "\n" + "Código do Livro: " + codigo + "\n" + "\n"
				+ "Titulo do Livro: " + titulo + "\n" + "\n"
				+ "Data de Empréstimo: " + dataemprestimo + "\n" + "\n" 
				+ "Data de Devolução: " + datadevolucao + "\n" + "\n"
				+ "Situação do Livro: " + situacao + "\n" + "\n";
	}	

}
