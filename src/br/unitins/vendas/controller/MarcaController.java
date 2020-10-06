package br.unitins.vendas.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.vendas.controller.listing.MarcaListing;
import br.unitins.vendas.model.Marca;

@Named
@ViewScoped
public class MarcaController extends Controller<Marca> {

	private static final long serialVersionUID = 5370016791538163989L;

	@Override
	public Marca getEntity() {
		if (entity == null) {
			entity = new Marca();
		}
		return entity;
	}
	
	public void abrirMarcaListing() {
		MarcaListing listing = new MarcaListing();
		listing.open();
	}
	
	public void obterMarcaListing(SelectEvent<Marca> event) {
		setEntity(event.getObject());
	}

}
