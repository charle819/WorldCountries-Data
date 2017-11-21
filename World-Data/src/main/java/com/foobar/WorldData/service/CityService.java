package com.foobar.WorldData.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foobar.WorldData.dao.CityDao;
import com.foobar.WorldData.model.City;

@Service
@Transactional
public class CityService {

	private static final Logger LOGGER = Logger.getLogger(CityService.class.getName());
	
	@Autowired
	CityDao cityDao;

	/**
	 * For finding City on the basis of an Id
	 * @param id of the city
	 * @return city 
	 */
	public City getCityById(int id)
	{
		City c = cityDao.getCityById(id);
		if(c == null)
		{
			LOGGER.warning("No City found for the id  : "+id);
			return null;
			// create a exception/ fault message in such situations
		}
		return c;
	}
	
	public City getCityByName(String name)
	{
		City city = cityDao.getCityByName(name);
		if(city == null)
		{
			LOGGER.warning("No city was found with name : "+city);
			return null;
		}
		return city;
	}
	
	public List<City> getCitiesByCountryCode(String countryCode)
	{
		List<City> cities = cityDao.getCitiesByCountryCode(countryCode);
		if(cities == null || cities.isEmpty())
		{
			LOGGER.warning("No cities were found with the country code : "+countryCode);
			return null;
		}
		return cities;
	}
}
