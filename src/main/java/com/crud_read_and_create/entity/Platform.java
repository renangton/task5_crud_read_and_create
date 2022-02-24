package com.crud_read_and_create.entity;

public class Platform {

	private int id;
	private String platform;

	public Platform(int id, String platform) {
		this.id = id;
		this.platform = platform;
	}

	public Platform() {
	}

	public int getId() {
		return id;
	}

	public String getPlatform() {
		return platform;
	}
}
