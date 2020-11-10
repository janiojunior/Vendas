package br.unitins.vendas.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.vendas.model.Cidade;
import br.unitins.vendas.repository.CidadeRepository;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter<Cidade> {

	@Override
	public Cidade getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		CidadeRepository repo = new CidadeRepository();
		Cidade cidade = repo.findById(Integer.valueOf(value.trim()));
		return cidade;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Cidade value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
