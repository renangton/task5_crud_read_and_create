package com.crud.entity;

import java.util.List;

public class Game {
  private Integer id;
  private String name;
  private String genre;
  private Integer price;
  private List<Platform> platforms;

  public Game(Integer id, String name, String genre, Integer price, List<Platform> platforms) {
    this.id = id;
    this.name = name;
    this.genre = genre;
    this.price = price;
    this.platforms = platforms;
  }

  public Game(Integer id, String name, String genre, Integer price) {
    this.id = id;
    this.name = name;
    this.genre = genre;
    this.price = price;
  }

  public Game() {

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

  public Integer getPrice() {
    return price;
  }

  public List<Platform> getPlatforms() {
    return platforms;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Game) {
      Game otherGame = (Game) obj;
      if (this.id == otherGame.id && this.name.equals(otherGame.name) && this.genre.equals(otherGame.genre)
          && this.price.equals(otherGame.price)) {
        if (!(this.platforms == null)) {
          if (this.platforms.equals(otherGame.platforms)) {
            return true;
          }
        }
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
