package br.unitins.vendas.model;

import javax.persistence.Entity;

@Entity
public class Marca extends DefaultEntity<Marca> {

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
