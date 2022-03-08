package com.crud_read_and_create.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.crud_read_and_create.entity.Platform;

public class GameForm {
	private String id;
	private String order;

	@Length(max = 20, message = "タイトルは20文字以内で入力して下さい。")
	@NotBlank(message = "タイトルが未入力です。")
	private String name;

	@Length(max = 20, message = "ジャンルは20文字以内で入力して下さい。")
	@NotBlank(message = "ジャンルが未入力です。")
	private String genre;

	@Range(min = 0, max = 100000, message = "価格は0~100000の範囲で入力してください。")
	@NotNull(message = "価格が未入力です。")
	private Integer price;

//	@NotBlank(message = "プラットフォームが未選択です。")
	private Platform[] platformId;

	public String getId() {
		return id;
	}

	public String getOrder() {
		return order;
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

	public Platform[] getPlatformId() {
		return platformId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOrder(String order) {
		this.order = order;
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

	public void setPlatformId(Platform[] platformId) {
		this.platformId = platformId;
	}

}