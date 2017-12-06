package com.foobar.WorldData.model;

public enum Continent {

	ASIA("Asia"),
	EUROPE("Europe"),
	NORTHAMERICA("North America"),
	AFRICA("Africa"),
	OCEANIA("Oceania"),
	ANTARTICA("ANTARTICA"),
	SOUTHAMERICA("South America");
	
	private String continent;
	
	Continent( String continent) {
		this.continent = continent;
	}
	
	public String getContinent()
	{
		return continent;
	}
	
	@Override
	public String toString()
	{
		return continent;
	}
	
}
