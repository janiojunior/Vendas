package br.unitins.vendas.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.vendas.model.Marca;
import br.unitins.vendas.repository.MarcaRepository;

@FacesConverter(forClass = Marca.class)
public class MarcaConverter implements Converter<Marca> {

	@Override
	public Marca getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		MarcaRepository repo = new MarcaRepository();
		Marca marca = repo.findById(Integer.valueOf(value.trim()));
		return marca;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Marca value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
