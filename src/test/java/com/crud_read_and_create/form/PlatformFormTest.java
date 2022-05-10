package com.crud_read_and_create.form;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@SpringBootTest
class PlatformFormTest {

	@Autowired
	Validator validator;

	private PlatformForm platformForm = new PlatformForm();
	private BindingResult bindingResult = new BindException(platformForm, "platformForm");

	@Test
	void 正常系_NotBlank() {
		platformForm = new PlatformForm(1, "platform");
		validator.validate(platformForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 異常系_NotBlank_empty() {
		platformForm = new PlatformForm(1, "");
		validator.validate(platformForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}

	@Test
	void 異常系_NotBlank_null() {
		platformForm = new PlatformForm(1, null);
		validator.validate(platformForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}

	@Test
	void 正常系_Length_max() {
		platformForm = new PlatformForm(1, "abcdefghijklmnopqrst");
		validator.validate(platformForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 異常系_Length_max() {
		platformForm = new PlatformForm(1, "abcdefghijklmnopqrstu");
		validator.validate(platformForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}
}
