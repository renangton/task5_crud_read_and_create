package com.crud_read_and_create.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GameForm {
	private String id;

	private String order;

	@NotBlank(message = "タイトルが未入力です。")
	private String name;

	@NotBlank(message = "ジャンルが未入力です。")
	private String genru;

	@NotBlank(message = "プラットフォームが未入力です。")
	private String platform;

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

	public String getGenru() {
		return genru;
	}

	public void setGenru(String genru) {
		this.genru = genru;
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
