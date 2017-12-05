package com.foobar.WorldData.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foobar.WorldData.dao.CityDao;
import com.foobar.WorldData.model.City;
import com.foobar.WorldData.utility.WorldDataExcelGenerator;

@Service
@Transactional
public class CityService {

	private static final Logger LOGGER = Logger.getLogger(CityService.class.getName());

	@Autowired
	CityDao cityDao;

	@Autowired
	WorldDataExcelGenerator excelGenerator;
	
	public City getCityByName(String name) {
		City city = cityDao.getCityByName(name);
		if (city == null) {
			LOGGER.warning("No city was found with name : " + city);
			return null;
		}
		return city;
	}

	public List<City> getCitiesByCountryCode(String countryCode) {
		List<City> cities = cityDao.getCitiesByCountryCode(countryCode);
		if (cities == null || cities.isEmpty()) {
			LOGGER.warning("No cities were found with the country code : " + countryCode);
			return null;
		}
		return cities;
	}
	
	public Long getNoOFCities()
	{
		List<Long> noOfCities = cityDao.getNoOfCities();
		if(noOfCities == null || noOfCities.isEmpty())
		{
			LOGGER.warning("No cities were found");
			return (long) 0;
		}
		return noOfCities.get(0);
	}
	
	public void saveCity(City city)
	{
		cityDao.addNewCity(city);
	}
	
	public City updateCity(City city)
	{
		City oldCity = getCityByName(city.getCity_name());
		if(oldCity == null)
		{
			LOGGER.warning("Update of city not possible as no city with name : "+city.getCity_name()+" was found");
			return null;
		}
		oldCity.setCity_countryCode(city.getCity_countryCode());
		oldCity.setCity_district(city.getCity_district());
		oldCity.setCity_name(city.getCity_name());
		oldCity.setCity_population(city.getCity_population());
		
		cityDao.updateCity(oldCity);
		
		return oldCity;
	}
	
	public boolean deleteCity(String name)
	{
		City city = getCityByName(name);
		if(city == null)
		{
			LOGGER.warning("Cannot delete city with name : "+name+" as it does not exist");
			return false;
		}
		
		cityDao.deleteCity(city);
		return true;
	}
	
	public List<City> getAllCities(int startIndex, int noOfRows)
	{
		if(startIndex != 0)
		{
			startIndex-=1;
		}
		List<City> cities =  cityDao.getAllCities(startIndex, noOfRows);
		if(cities == null || cities.isEmpty())
		{
			LOGGER.warning("No cities are present from index : "+startIndex+" till : "+noOfRows);
			return null;
		}
		return cities;
	}
	
	public void generateCityExcelSheet(HttpServletResponse response, int startIndex , int noOfRows)
	{
		List<City> cityList =  getAllCities(startIndex, noOfRows);
			
		String fileName = "CityData.xlsx";
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		Workbook workbook = excelGenerator.generateCityExcelSheet(cityList);
		
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
