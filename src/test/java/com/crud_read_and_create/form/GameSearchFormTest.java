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
	void 正しい値を渡した時_バリデーションエラーとならないこと() {
		gameSearchForm = new GameSearchForm("", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void idに1を渡した時_バリデーションエラーとならないこと() {
		gameSearchForm = new GameSearchForm("1", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void idに555を渡した時_バリデーションエラーとならないこと() {
		gameSearchForm = new GameSearchForm("555", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void idに1000を渡した時_バリデーションエラーとならないこ() {
		gameSearchForm = new GameSearchForm("1000", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void idに0を渡した時_バリデーションエラーとなること() {
		gameSearchForm = new GameSearchForm("0", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}

	@Test
	void idに1001を渡した時_バリデーションエラーとなること() {
		gameSearchForm = new GameSearchForm("1001", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}

	@Test
	void idに文字列を渡した時_バリデーションエラーとなること() {
		gameSearchForm = new GameSearchForm("ID", "asc");
		validator.validate(gameSearchForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}
}
