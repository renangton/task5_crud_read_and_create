package com.crud_read_and_create.entity;

import java.util.List;

public class Game {
	private String id;
	private String name;
	private String genre;
	private Integer price;
	private List<Platform> platforms;

	public Game(String id, String name, String genre, Integer price, List<Platform> platforms) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.price = price;
		this.platforms = platforms;
	}

	public Game(String id, String name, String genre, Integer price) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.price = price;
	}

	public Game() {

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	public Integer getPrice() {
		return price;
	}

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

}