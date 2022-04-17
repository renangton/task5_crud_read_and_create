package com.crud_read_and_create.form;

import javax.validation.constraints.Pattern;

public class GameSearchForm {

	@Pattern(regexp = "|[1-9]|[1-9][0-9]{0,2}0?", message = "IDは1～1000の範囲の整数で入力してください。")
	private String id;

	private String order;

	public Integer getId() {
		Integer id = this.id.isEmpty() ? null : Integer.parseInt(this.id);
		return id;
	}

	public String getOrder() {
		return order;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
