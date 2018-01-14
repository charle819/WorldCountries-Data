package com.foobar.WorldData.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foobar.WorldData.dao.CountryDao;
import com.foobar.WorldData.model.Country;
import com.foobar.WorldData.model.PopulationVO;

@Service
@Transactional
public class CountryService {

	private static final Logger LOGGER = Logger.getLogger(CountryService.class.getName());
	
	@Autowired
	CountryDao countryDao;
	
	public Country getCountyByName(String countryName)
	{
		Country country =  countryDao.getCountryByName(countryName);
		if(country == null)
		{
			LOGGER.warning("Cannot fetch Country with name : "+countryName+" as it does not exist");
		}
		return country;
	}
	
	public Country updateCountryDetails(Country country)
	{
		Country oldCountry = getCountyByName(country.getName());
		if(oldCountry == null)
		{
			LOGGER.warning("Cannot update country as no coutry with name : "+country.getName()+" was found !");
			return null;
		}
		oldCountry.setContinent(country.getContinent());
		oldCountry.setRegion(country.getRegion());
		oldCountry.setSurfaceArea(country.getSurfaceArea());
		oldCountry.setIndepYear(country.getIndepYear());
		oldCountry.setPopulation(country.getPopulation());
		oldCountry.setLifeExpectancy(country.getLifeExpectancy());
		oldCountry.setGnp(country.getGnp());
		oldCountry.setGnpOld(country.getGnpOld());
		oldCountry.setLocalName(country.getLocalName());
		oldCountry.setGovForm(country.getGovForm());
		oldCountry.setHeadOfState(country.getHeadOfState());
		oldCountry.setCode2(country.getCode2());
		
		countryDao.updateCountryData(oldCountry);
		
		return oldCountry;
	}
	
	public List<PopulationVO> getPopulation()
	{
		List<PopulationVO> popluationVoList  = new ArrayList<>();
		List<Object[]> countryList =  countryDao.getCountryPopulation();

		for(Object[] c : countryList)
		{
			popluationVoList.add(new PopulationVO((String)c[0],(Integer) c[1]));
		}
		return popluationVoList;
	}
	
	
	public List<Country> getCoutries(int startIndex,int total)
	{
		List<Country> countryList = countryDao.getCountryList(startIndex, total);
		if(countryList == null)
		{
			LOGGER.warning("No countries were found from startIndex : "+startIndex+" and total : "+total);
			return null;
		}
		return countryList;
	}
}
