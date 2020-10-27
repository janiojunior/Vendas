package br.unitins.vendas.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Telefone extends DefaultEntity<Telefone> {

	private String codigoArea;
	private String numero;
	
	@ManyToOne
	private Usuario usuario;

	public String getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
