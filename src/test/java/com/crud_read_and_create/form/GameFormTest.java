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
class GameFormTest {

	@Autowired
	Validator validator;

	private GameForm gameForm = new GameForm();
	private String[] platforms = { "platform" };
	private BindingResult bindingResult = new BindException(gameForm, "gameForm");

	@Test
	void 正常系_NotBlank_NotNull_Size() {
		gameForm = new GameForm(1, "name", "genre", 100, platforms);
		validator.validate(gameForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 異常系_NotBlank_empty() {
		gameForm = new GameForm(1, "", "", 100, platforms);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(2);
	}

	@Test
	void 異常系_NotBlank_NotNull_null() {
		gameForm = new GameForm(1, null, null, null, platforms);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(3);
	}

	@Test
	void 異常系_Size() {
		String[] emptyPlatform = {};
		gameForm = new GameForm(1, "name", "genre", 100, emptyPlatform);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}

	@Test
	void 正常系_Length_max() {
		gameForm = new GameForm(1, "abcdefghijklmnopqrst", "abcdefghijklmnopqrst", 100, platforms);
		validator.validate(gameForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 異常系_Length_max() {
		gameForm = new GameForm(1, "abcdefghijklmnopqrstu", "abcdefghijklmnopqrstu", 100, platforms);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(2);
	}

	@Test
	void 正常系_Range_min() {
		gameForm = new GameForm(1, "name", "genre", 0, platforms);
		validator.validate(gameForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 異常系_Range_min() {
		gameForm = new GameForm(1, "name", "genre", -1, platforms);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}

	@Test
	void 正常系_Range_max() {
		gameForm = new GameForm(1, "name", "genre", 100000, platforms);
		validator.validate(gameForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 異常系_Range_max() {
		gameForm = new GameForm(1, "name", "genre", 100001, platforms);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}
}
