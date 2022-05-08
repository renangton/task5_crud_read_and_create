package com.crud_read_and_create.entity;

public class Platform {
	private Integer id;
	private String platform;

	public Platform(Integer id, String platform) {
		this.id = id;
		this.platform = platform;
	}

	public Platform() {

	}

	public Integer getId() {
		return id;
	}

	public String getPlatform() {
		return platform;
	}

	@Override
	public String toString() {
		return "Platform [id=" + id + ", platform=" + platform + "]";
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Platform) {
			Platform otherPlatform = (Platform) obj;
			if (this.id.equals(otherPlatform.id) && this.platform.equals(otherPlatform.platform)) {
				return true;
			}
		}
		return false;
	}
}
