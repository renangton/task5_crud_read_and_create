package com.crud_read_and_create.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class GameForm {
	private String id;

	private String order;

	@Length(max = 20, message = "タイトルは20文字以内で入力して下さい。")
	@NotBlank(message = "タイトルが未入力です。")
	private String name;

	@Length(max = 20, message = "ジャンルは20文字以内で入力して下さい。")
	@NotBlank(message = "ジャンルが未入力です。")
	private String genre;

	@Length(max = 20, message = "プラットフォームは20文字以内で入力して下さい。")
	@NotBlank(message = "プラットフォームが未入力です。")
	private String platform;

	@Range(min = 0, max = 100000, message = "価格は0~100000の範囲で入力してください。")
	@NotNull(message = "価格が未入力です。")
	private Integer price;

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

	public String getgenre() {
		return genre;
	}

	public void setgenre(String genre) {
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

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
