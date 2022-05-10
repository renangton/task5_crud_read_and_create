package com.crud_read_and_create.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class PlatformForm {
	private Integer id;

	@Length(max = 20, message = "プラットフォームは20文字以内で入力して下さい。")
	@NotBlank(message = "プラットフォームが未入力です。")
	private String platform;

	public PlatformForm(Integer id,
			@Length(max = 20, message = "プラットフォームは20文字以内で入力して下さい。") @NotBlank(message = "プラットフォームが未入力です。") String platform) {
		this.id = id;
		this.platform = platform;
	}

	public PlatformForm() {
	}

	public Integer getId() {
		return id;
	}

	public String getPlatform() {
		return platform;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
