package com.foobar.WorldData.service;

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

	public City getCityById(int id)
	{
		City c = cityDao.getCityById(id);
		if(c == null)
		{
			LOGGER.warning("No City found !!!");
			// create a exception/ fault message in such situations
		}
		return c;
	}

}
