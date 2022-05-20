package com.crud_read_and_create.controller.view;

import java.util.Comparator;
import java.util.stream.Collectors;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.Platform;

public class GameView {
	private Integer id;
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

	public Integer getId() {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof GameView) {
			GameView otherGameView = (GameView) obj;
			if (this.id == otherGameView.id && this.name.equals(otherGameView.name)
					&& this.genre.equals(otherGameView.genre) && this.platforms.equals(otherGameView.platforms)
					&& this.price.equals(otherGameView.price)) {
				return true;
			}
		}
		return false;
	}
}
