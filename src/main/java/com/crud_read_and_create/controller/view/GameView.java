package com.crud_read_and_create.controller.view;

import java.util.Comparator;
import java.util.stream.Collectors;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.Platform;

public class GameView {
	private String id;
	private String name;
	private String genre;
	private String platforms;
	private String price;

	public GameView(Game game) {
		this.id = game.getId();
		this.name = game.getName();
		this.genre = game.getGenre();
		this.platforms = game.getPlatforms().stream().sorted(Comparator.comparing(Platform::getPlatform))
				.map(Platform::getPlatform).collect(Collectors.joining("/"));
		this.price = String.format("%,d", game.getPrice());
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

	public String getPlatforms() {
		return platforms;
	}

	public String getPrice() {
		return price;
	}

}
