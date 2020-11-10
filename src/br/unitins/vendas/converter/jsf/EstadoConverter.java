package br.unitins.vendas.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.vendas.model.Estado;
import br.unitins.vendas.repository.EstadoRepository;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter<Estado> {

	@Override
	public Estado getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		EstadoRepository repo = new EstadoRepository();
		try {
			Estado estado = repo.findById(Integer.valueOf(value.trim()));
			return estado;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Estado value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
