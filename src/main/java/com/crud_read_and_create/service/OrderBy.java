package com.crud_read_and_create.service;

import java.util.Arrays;

public enum OrderBy {
	ASC("ASC"), DESC("DESC");

	private String value;

	OrderBy(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static OrderBy from(String input) {
		return Arrays.stream(values())
				.filter(v -> v.name().equals(input))
				.findFirst().orElseThrow(RuntimeException::new);
	}

}
