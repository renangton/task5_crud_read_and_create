package com.crud.entity;

public class GamePlatform {
  private Integer id;
  private Integer gameId;
  private String platformId;

  public GamePlatform(Integer id, Integer gameId, String platformId) {
    this.id = id;
    this.gameId = gameId;
    this.platformId = platformId;
  }

  public GamePlatform(Integer gameId, String platformId) {
    this.gameId = gameId;
    this.platformId = platformId;
  }

  public Integer getId() {
    return id;
  }

  public Integer getGameId() {
    return gameId;
  }

  public String getPlatformId() {
    return platformId;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof GamePlatform) {
      GamePlatform otherGamePlatform = (GamePlatform) obj;
      if (this.gameId.equals(otherGamePlatform.gameId) && this.platformId.equals(otherGamePlatform.platformId)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 7;
    result = 31 * result + id;
    result = 31 * result + gameId;
    result = 31 * result + ((platformId == null) ? 0 : platformId.hashCode());
    return result;
  }
}
