package com.crud_read_and_create.entity;

public class Game {
	private String id;
	private String name;
	private String genre;
	private String platform;
	private Integer price;

	public Game(String id, String name, String genre, String platform, Integer price) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.platform = platform;
		this.price = price;

	}

	public Game() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}