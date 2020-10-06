package br.unitins.vendas.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.model.Marca;
import br.unitins.vendas.repository.MarcaRepository;

@Named
@ViewScoped
public class MarcaListing extends Listing<Marca> {
	
	private static final long serialVersionUID = -9011762519094386462L;
	private String filtro;

	public MarcaListing() {
		super("marcalisting", new MarcaRepository());
	}
	
	public void pesquisar() {
		MarcaRepository repo = (MarcaRepository)getRepository();
		try {
			setList(repo.findByNome(filtro));
		} catch (RepositoryException e) {
			e.printStackTrace();
			setList(new ArrayList<Marca>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	
}
