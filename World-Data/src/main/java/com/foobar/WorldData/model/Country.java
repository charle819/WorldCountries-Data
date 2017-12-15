package com.foobar.WorldData.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Country")
@Entity
@Table(name = "country")
public class Country implements Serializable {

	@Id
	@Column(name = "Code", nullable = false, length = 3)
	private String code;
	@Column(name = "Name", nullable = false)
	private String name;
	/**
	 * Here we are using a JPA @Convert for transforming enum value as per requirement
	 */
	@Column(name = "Continent", nullable = false, columnDefinition = " default Africa")
	@Convert(converter = ContinentConverter.class)
	private Continent continent;
	@Column(name = "region", nullable = false)
	private String region;
	@Column(name = "SurfaceArea", nullable = false, precision = 10, scale = 2, columnDefinition = "default '0.00'")
	private float surfaceArea;
	@Column(name = "IndepYear",columnDefinition="int default 0")
	private int indepYear;
	@Column(name = "Population", nullable = false, columnDefinition = "int default 0")
	private int population;
	@Column(name = "LifeExpectancy", nullable = true, precision = 3, scale = 1)
	private float lifeExpectancy;
	@Column(name = "GNP", nullable = true, precision = 10, scale = 2)
	private float gnp;
	@Column(name = "GNPOld", nullable = true, precision = 10, scale = 2)
	private float gnpOld;
	@Column(name = "LocalName", nullable = false)
	private String localName;
	@Column(name = "GovernmentForm", nullable = false)
	private String govForm;
	@Column(name = "HeadOfState")
	private String headOfState;
	@Column(name = "Code2", nullable = false, length = 2)
	private String code2;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public float getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public int getIndepYear() {
		return indepYear;
	}

	public void setIndepYear(int indepYear) {
		if(Integer.valueOf(indepYear) == null)
		{
			this.indepYear = 0;
		}
		else
		{
			this.indepYear = indepYear;
		}
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public float getLifeExpectancy() {
		return lifeExpectancy;
	}

	public void setLifeExpectancy(float lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public float getGnp() {
		return gnp;
	}

	public void setGnp(float gnp) {
		this.gnp = gnp;
	}

	public float getGnpOld() {
		return gnpOld;
	}

	public void setGnpOld(float gnpOld) {
		this.gnpOld = gnpOld;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getGovForm() {
		return govForm;
	}

	public void setGovForm(String govForm) {
		this.govForm = govForm;
	}

	public String getHeadOfState() {
		return headOfState;
	}

	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

}
