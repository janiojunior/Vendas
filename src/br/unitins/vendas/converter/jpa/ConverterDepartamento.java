package br.unitins.vendas.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.vendas.controller.Departamento;

@Converter(autoApply = true)
public class ConverterDepartamento implements AttributeConverter<Departamento, Integer>{

	@Override
	public Integer convertToDatabaseColumn(Departamento attribute) {
		
		return attribute == null? null : attribute.getId();
	}

	@Override
	public Departamento convertToEntityAttribute(Integer dbData) {
		return Departamento.valueOf(dbData);
	}

}
