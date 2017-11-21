package com.foobar.WorldData.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foobar.WorldData.model.City;
import com.foobar.WorldData.service.CityService;

@RestController
@RequestMapping(value = "/")
public class WorldDataController {

	@Autowired
	CityService cityService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> appInitializationMessage() {
		return new ResponseEntity<String>("World-Data is up and running !!!", HttpStatus.OK);
	}

	@RequestMapping(value = "/city/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> getCityByID(@PathVariable("id") int id) {
		City city = cityService.getCityById(id);

		if (city == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}

//	@RequestMapping(value="/city/{name}",method=RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<City> getCityByName(@PathVariable(value="name") String name)
//	{
//		City city = cityService.getCityByName(name);
//		if(city == null)
//		{
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<City>(city, HttpStatus.OK);
//	}
//	
	@RequestMapping(value = "/city", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getCitiesByCountryCode(@RequestParam(value = "code",required=false) String countryCode) {
		List<City> cities = cityService.getCitiesByCountryCode(countryCode);

		if (cities == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	}

}
