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
class GameSearchFormTest {

	@Autowired
	Validator validator;

	private GameSearchForm gameSearchForm = new GameSearchForm();
	private BindingResult bindingResult = new BindException(gameSearchForm, "gameSearchForm");

	@Test
	void 正常系_Pattern_empty() {
		gameSearchForm = new GameSearchForm("", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 正常系_Pattern_整数_1() {
		gameSearchForm = new GameSearchForm("1", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 正常系_Pattern_整数_555() {
		gameSearchForm = new GameSearchForm("555", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 正常系_Pattern_整数_1000() {
		gameSearchForm = new GameSearchForm("1000", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 異常系_Pattern_整数_0() {
		gameSearchForm = new GameSearchForm("0", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}

	@Test
	void 異常系_Pattern_整数_1001() {
		gameSearchForm = new GameSearchForm("1001", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}

	@Test
	void 異常系_Pattern_文字() {
		gameSearchForm = new GameSearchForm("ID", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}
}
