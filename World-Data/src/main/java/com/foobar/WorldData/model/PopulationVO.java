package com.foobar.WorldData.model;

public class PopulationVO {

	private String name;
	private int population;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public PopulationVO(String name, int population) {
		super();
		this.name = name;
		this.population = population;
	}

	@Override
	public String toString() {
		return "PopulationVO [name=" + name + ", population=" + population + "]";
	}

}
