package com.foobar.WorldData.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.foobar.WorldData.model.City;

@Repository
public class CityDao extends RootDao<Integer, City> {

	private static final Logger LOGGER = Logger.getLogger(CityDao.class.getName());

	public City getCityById(int id) {
		City city = getById(id);
		if (city == null) {
			LOGGER.warning("No city found with ID  : " + id);
			return null;
		}
		return city;
	}
	
	public City getCityByName(String name)
	{
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("Name", name));
		City city = (City) criteria.uniqueResult();
		return city;
	}
	
	@SuppressWarnings("unchecked")
	public List<City> getCitiesByCountryCode(String countryCode)
	{
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("city_countryCode", countryCode));
		return (List<City>) criteria.list();	
	}
}
