package com.crud_read_and_create.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class PlatformForm {
	private String id;

	@Length(max = 20, message = "プラットフォームは20文字以内で入力して下さい。")
	@NotBlank(message = "プラットフォームが未入力です。")
	private String platform;

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
