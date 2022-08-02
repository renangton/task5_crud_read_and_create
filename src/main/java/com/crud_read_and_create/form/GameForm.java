package com.crud_read_and_create.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GameForm {

  private Integer id;

  @Length(max = 20, message = "タイトルは20文字以内で入力して下さい。")
  @NotBlank(message = "タイトルが未入力です。")
  private String name;
	
  @Length(max = 20, message = "ジャンルは20文字以内で入力して下さい。")
  @NotBlank(message = "ジャンルが未入力です。")
  private String genre;

  @Range(min = 0, max = 100000, message = "価格は0~100000の範囲で入力して下さい。")
  @NotNull(message = "価格が未入力です。")
  private Integer price;

  @Size(min = 1, message = "プラットフォームが未選択です。")
  private String[] platformId;

  public GameForm(Integer id, String name, String genre, Integer price, String[] platformId) {
    this.id = id;
    this.name = name;
    this.genre = genre;
    this.price = price;
    this.platformId = platformId;
  }

  public GameForm() {
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

  public String[] getPlatformId() {
    return platformId;
  }

  public void setId(Integer id) {
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

  public void setPlatformId(String[] platformId) {
    this.platformId = platformId;
  }
}
