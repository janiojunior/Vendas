package br.unitins.vendas.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.vendas.model.Perfil;

@Converter(autoApply = true)
public class ConverterPerfil implements AttributeConverter<Perfil, Integer>{

	@Override
	public Integer convertToDatabaseColumn(Perfil attribute) {
		
		return attribute == null? null : attribute.getId();
	}

	@Override
	public Perfil convertToEntityAttribute(Integer dbData) {
		return Perfil.valueOf(dbData);
	}

}
