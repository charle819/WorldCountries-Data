package com.foobar.WorldData.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.foobar.WorldData.model.Country;

@Repository
public class CountryDao extends RootDao<String, Country> {

	private static final Logger LOGGER = Logger.getLogger(CountryDao.class.getName());
	
	public Country getCountryByName(String countryName)
	{
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("name", countryName));
		Country country = (Country) criteria.uniqueResult();
		return country;
	}
	
	public void updateCountryData(Country country)
	{
		update(country);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCountryPopulation()
	{
		/** new update */
		//use pagination and also check if streaming is possible , also imply for city 
		
		Criteria criteria = getCriteria();
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("name"))
				.add(Projections.property("population")));
		return (List<Object[]>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Country> getCountryList(int startIndex, int total)
	{
		Criteria criteria = getCriteria();
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(total);
		return (List<Country>) criteria.list();
	}
}
