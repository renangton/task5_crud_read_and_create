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
	void 正しい値を渡した時_バリデーションエラーとならないこと() {
		gameForm = new GameForm(1, "name", "genre", 100, platforms);
		validator.validate(gameForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 必須項目に空文字を渡した時_バリデーションエラーとなること() {
		gameForm = new GameForm(1, "", "", 100, platforms);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(2);
	}

	@Test
	void 必須項目にnullを渡した時_バリデーションエラーとなること() {
		gameForm = new GameForm(1, null, null, null, platforms);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(3);
	}

	@Test
	void platformが１つも選択されていない時_バリデーションエラーとなること() {
		String[] emptyPlatform = {};
		gameForm = new GameForm(1, "name", "genre", 100, emptyPlatform);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}

	@Test
	void nameとpriceに20文字以内の値を渡した時_バリデーションエラーとならないこと() {
		gameForm = new GameForm(1, "abcdefghijklmnopqrst", "abcdefghijklmnopqrst", 100, platforms);
		validator.validate(gameForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void nameとpriceに20文字を超える値を渡した時_バリデーションエラーとなること() {
		gameForm = new GameForm(1, "abcdefghijklmnopqrstu", "abcdefghijklmnopqrstu", 100, platforms);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(2);
	}

	@Test
	void priceに0を渡した時_バリデーションエラーとならないこと() {
		gameForm = new GameForm(1, "name", "genre", 0, platforms);
		validator.validate(gameForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void priceに0未満の値を渡した時_バリデーションエラーとなること() {
		gameForm = new GameForm(1, "name", "genre", -1, platforms);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}

	@Test
	void priceに100000を渡した時_バリデーションエラーとならないこと() {
		gameForm = new GameForm(1, "name", "genre", 100000, platforms);
		validator.validate(gameForm, bindingResult);
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void priceに100000を超える値を渡した時_バリデーションエラーとなること() {
		gameForm = new GameForm(1, "name", "genre", 100001, platforms);
		validator.validate(gameForm, bindingResult);
		assertThat(bindingResult.getFieldErrorCount()).isEqualTo(1);
	}
}
