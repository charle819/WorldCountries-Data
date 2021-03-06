package com.foobar.WorldData.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.foobar.WorldData.model.City;
import com.foobar.WorldData.model.Country;
import com.foobar.WorldData.model.PopulationVO;
import com.foobar.WorldData.service.CityService;
import com.foobar.WorldData.service.CountryService;

@RestController
@RequestMapping(value = "/")
public class WorldDataController {

	private static final Logger LOGGER = Logger.getLogger(WorldDataController.class.getName());
	
	@Autowired
	CityService cityService;
	
	@Autowired
	CountryService countryService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> appInitializationMessage() {
		return new ResponseEntity<String>("World-Data is up and running !!!", HttpStatus.OK);
	}

	/********************************** Retrieve City by Name ***********************************/	
	
	@RequestMapping(value = "/city/{name}", method = RequestMethod.GET)
	public ResponseEntity<City> getCityByName(@PathVariable(value = "name") String name) {
		City city = cityService.getCityByName(name);
		if (city == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}
	
	/********************************** Add new City ***********************************/
	@RequestMapping(value="/city",method = RequestMethod.POST)
	public ResponseEntity<Void> addNewCity(@RequestBody City city, UriComponentsBuilder uriComponentsBuilder)
	{
		if(cityService.getCityByName(city.getCity_name()) != null)
		{
			LOGGER.warning("Cannot add city as it already exist");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		cityService.saveCity(city);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/city/{name}").buildAndExpand(city.getCity_name()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	/********************************** Update City ***********************************/
	@RequestMapping(value="/city/{name}",method = RequestMethod.PUT)
	public ResponseEntity<City> updateCity(@RequestBody City city,@PathVariable String name)
	{
		City updatedCity =  cityService.updateCity(city);
		if(updatedCity == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<City>(updatedCity, HttpStatus.OK);
	}
	
	/********************************** Delete City ***********************************/
	@RequestMapping(value="/city/{name}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCity(@PathVariable String name)
	{
		if( cityService.deleteCity(name) == false)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/********************************** Retrieve Cities by CountryCode ***********************************/
	
	@RequestMapping(value = "/city" ,method = RequestMethod.GET)
	public ResponseEntity<List<City>> getCitiesByCountryCode(@RequestParam(value="code",required=false) String countryCode) {
		List<City> cities = cityService.getCitiesByCountryCode(countryCode);

		if (cities == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	}

	/********************************** Get No of Cities ***********************************/
	
	@RequestMapping(value="/city/size",method=RequestMethod.GET)
	public ResponseEntity<Long> getNoOfCities()
	{
		Long noOfCities = cityService.getNoOFCities();
		return new ResponseEntity<Long>(noOfCities, HttpStatus.OK);
	}

	
	/********************************** Get all cities between a range ***********************************/
	@RequestMapping(value="/city/all",method=RequestMethod.GET)
	public ResponseEntity<List<City>> getAllCitiesByRange(@RequestParam(value="start",required=false,defaultValue="0") int startIndex ,@RequestParam(value="total",required=false, defaultValue="10") int noOfCities )
	{
		LOGGER.info("start : "+startIndex+"  end : "+noOfCities);
		List<City> cities =  cityService.getAllCities(startIndex, noOfCities);
		if(cities == null)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	}
	
	/********************************** Get city population ***********************************/
	@RequestMapping(value="city/popul",method=RequestMethod.GET)
	public ResponseEntity<List<PopulationVO>> getCityPopulation(@RequestParam(value="code",required=false,defaultValue="AFG") String countryCode)
	{
		List<PopulationVO> cityPopulation = cityService.getCityPopulationData(countryCode);
		if(cityPopulation == null)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}		
		return new ResponseEntity<List<PopulationVO>>(cityPopulation, HttpStatus.OK);
	}
	
	
	/********************************** Generate City Excel-Sheet ***********************************/
	@RequestMapping(value="/city/excel",method=RequestMethod.GET)
	public void getCityExcelSheet(HttpServletResponse response,@RequestParam(value="start",defaultValue="0",required=false) int startIndex,@RequestParam(value="total",defaultValue="10",required=false) int noOfCities)
	{
		LOGGER.info("Generating excel sheet for city from index : "+startIndex+" till index :"+noOfCities);
		cityService.generateCityExcelSheet(response, startIndex, noOfCities);
	}
	
	
	/**************************************************** Country Services *****************************************************/
	
	/********************************** Fetch City by Name ***********************************/	
	
	@RequestMapping(value="/country/{name}",method=RequestMethod.GET)
	public ResponseEntity<Country> getCountry(@PathVariable(value="name") String countryName)
	{
		Country country =  countryService.getCountyByName(countryName);
		if(country == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	
	}
	
	/********************************** Update Country ***********************************/
	@RequestMapping(value="country/{name}",method=RequestMethod.PUT)
	public ResponseEntity<Country> updateCountry(@RequestBody Country country,@PathVariable(value="name") String countryName)
	{
		Country updatedCountry = countryService.updateCountryDetails(country);
		if(updatedCountry == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Country>(updatedCountry, HttpStatus.OK);
	}
	
	/********************************** Get all countries between a range ***********************************/
	@RequestMapping(value="/country/all",method=RequestMethod.GET)
	public ResponseEntity<List<Country>> getCountries(@RequestParam(value="start", defaultValue="0",required=false) int startIndex,@RequestParam(value="total",defaultValue="10",required=false) int total)
	{
		List<Country> countries = countryService.getCoutries(startIndex, total);
		if(countries == null)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
	}
	
	/********************************** Get country population ***********************************/
	@RequestMapping(value="/country/popul",method=RequestMethod.GET)
	public ResponseEntity<List<PopulationVO>> getCountryPopulation()
	{
		List<PopulationVO> countryPopulation =  countryService.getPopulation();
		if(countryPopulation == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PopulationVO>>(countryPopulation, HttpStatus.OK);
	}
	
	
}
