package com.foobar.WorldData.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="City")
@Entity
@Table(name="city")
public class City implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID",nullable=false)
	private int city_id;
	
	@Column(name="Name",nullable=false)
	private String city_name;
	
	@Column(name="CountryCode",nullable=false,length=3)
	private String city_countryCode;
	
	@Column(name="District",nullable=false)
	private String city_district;
	
	@Column(name="Population",nullable=false,columnDefinition="int default 0")
	private int city_population;

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCity_countryCode() {
		return city_countryCode;
	}

	public void setCity_countryCode(String city_countryCode) {
		this.city_countryCode = city_countryCode;
	}

	public String getCity_district() {
		return city_district;
	}

	public void setCity_district(String city_district) {
		this.city_district = city_district;
	}

	public int getCity_population() {
		return city_population;
	}

	public void setCity_population(int city_population) {
		this.city_population = city_population;
	}
	
	
	
}
