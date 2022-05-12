package com.crud_read_and_create.form;

import java.util.Objects;

import javax.validation.constraints.Pattern;

public class GameSearchForm {

	@Pattern(regexp = "|[1-9]|[1-9][0-9]{0,2}0?", message = "IDは1～1000の範囲の整数で入力してください。")
	private String id;

	private String order;

	public GameSearchForm(String id, String order) {
		this.id = id;
		this.order = order;
	}

	public GameSearchForm() {
	}

	public Integer getId() {
		Integer id = Objects.nonNull(this.id) && !this.id.isEmpty() ? Integer.parseInt(this.id) : null;
		return id;
	}

	public String getOrder() {
		return order;
	}
}
