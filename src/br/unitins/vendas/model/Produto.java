package br.unitins.vendas.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import br.unitins.vendas.controller.Departamento;

@Entity
public class Produto extends DefaultEntity<Produto>{

	private String nome;
	private String descricao;
	private Departamento departamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Marca marca;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
}
