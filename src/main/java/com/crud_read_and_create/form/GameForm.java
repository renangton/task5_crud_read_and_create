package com.crud_read_and_create.form;

import javax.validation.constraints.NotNull;

public class GameForm {
	private String id;

	private String order;

	@NotNull
	private String name;

	@NotNull
	private String genru;

	@NotNull
	private String platform;

	@NotNull
	private int price;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
