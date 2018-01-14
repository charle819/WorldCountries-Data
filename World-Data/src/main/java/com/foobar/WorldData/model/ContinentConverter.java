package com.foobar.WorldData.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Chinmay
 * 05-Dec-2017
 * 
 * The given Class is used for converting enum value as per required datatype
 * 
 * 
 */
@Converter(autoApply = true)
public class ContinentConverter implements AttributeConverter<Continent, String> {

	@Override
	public String convertToDatabaseColumn(Continent attribute) {
		
		return attribute.getContinent();
	}

	@Override
	public Continent convertToEntityAttribute(String dbData) {
	
		for(Continent continent : Continent.values() )
		{
			if(continent.getContinent().equals(dbData))
			{
				return continent;
			}
		}
		throw new IllegalArgumentException("Unknow Continent value : "+dbData);
		
	}

}
