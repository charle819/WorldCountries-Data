package com.foobar.WorldData.dao;

import java.util.logging.Logger;

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
}
