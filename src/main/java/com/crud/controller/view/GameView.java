package com.crud.controller.view;

import com.crud.entity.Game;
import com.crud.entity.Platform;
import java.util.Comparator;
import java.util.stream.Collectors;

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
      if (this.id.equals(otherGameView.id) && this.name.equals(otherGameView.name)
          && this.genre.equals(otherGameView.genre) && this.platforms.equals(otherGameView.platforms)
          && this.price.equals(otherGameView.price)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 7;
    result = 31 * result + id;
    result = 31 * result + ((name == null) ? 0 : name.hashCode());
    result = 31 * result + ((genre == null) ? 0 : genre.hashCode());
    result = 31 * result + ((platforms == null) ? 0 : platforms.hashCode());
    return 31 * result + ((price == null) ? 0 : price.hashCode());
  }
}
