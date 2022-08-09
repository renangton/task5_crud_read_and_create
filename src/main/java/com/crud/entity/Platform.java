package com.crud.entity;

public class Platform {
  private Integer id;
  private String platform;

  public Platform(Integer id, String platform) {
    this.id = id;
    this.platform = platform;
  }

  public Platform() {

  }

  public Integer getId() {
    return id;
  }

  public String getPlatform() {
    return platform;
  }

  @Override
  public String toString() {
    return "Platform [id=" + id + ", platform=" + platform + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Platform) {
      Platform otherPlatform = (Platform) obj;
      if (this.id.equals(otherPlatform.id) && this.platform.equals(otherPlatform.platform)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 7;
    result = 31 * result + id;
    result = 31 * result + ((platform == null) ? 0 : platform.hashCode());
    return result;
  }
}
