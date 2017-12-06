package com.foobar.WorldData.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.foobar.WorldData.model.City;

@Repository
public class CityDao extends RootDao<Integer, City> {

	private static final Logger LOGGER = Logger.getLogger(CityDao.class.getName());

	public City getCityByName(String name) {
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("city_name", name));
		City city = (City) criteria.uniqueResult();
		return city;
	}

	@SuppressWarnings("unchecked")
	public List<City> getCitiesByCountryCode(String countryCode) {
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("city_countryCode", countryCode));
		return (List<City>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getNoOfCities()
	{
		Criteria criteria = getCriteria();
		criteria.setProjection(Projections.rowCount());
		return (List<Long>) criteria.list();
	}
	
	public void addNewCity(City city)
	{
		persist(city);
	}
	
	public void updateCity(City city)
	{
		update(city);
	}
	
	public void deleteCity(City city)
	{
		delete(city);
	}
	
	@SuppressWarnings("unchecked")
	public List<City> getAllCities(int startIndex, int noOfRows)
	{
		Criteria criteria = getCriteria();
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(noOfRows);
		return (List<City>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPopulation(String countryCode)
	{
		Criteria criteria = getCriteria();
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("city_name"));
		pList.add(Projections.property("city_population"));
		criteria.setProjection(pList);
		criteria.add(Restrictions.eq("city_countryCode", countryCode));
		
		return (List<Object[]>) criteria.list();
	}
}
