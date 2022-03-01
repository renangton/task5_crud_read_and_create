package com.crud_read_and_create.entity;

public class Platform {
	private String id;
	private String platform;

	public Platform(String id, String platform) {
		this.id = id;
		this.platform = platform;
	}

	public Platform() {

	}

	public String getId() {
		return id;
	}

	public String getPlatform() {
		return platform;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
