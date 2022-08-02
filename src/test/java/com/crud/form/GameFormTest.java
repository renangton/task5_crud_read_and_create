package com.crud.form;

import java.util.List;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.Assert.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

@SpringBootTest(classes = {ValidationAutoConfiguration.class})
class GameFormTest {

  @Autowired
  Validator validator;

  private GameForm gameForm = new GameForm();
  private String[] platforms = {"platform"};
  private BindingResult bindingResult = new BindException(gameForm, "gameForm");
  private Function<BindingResult, List<FieldError>> getFieldErrors;

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
    assertThat(bindingResult.getFieldErrors()).extracting(
      FieldError::getField,
      FieldError::getDefaultMessage
    ).containsExactlyInAnyOrder(
      tuple("name", "タイトルが未入力です。"),
      tuple("genre", "ジャンルが未入力です。")
    );
  }

  @Test
  void 必須項目にnullを渡した時_バリデーションエラーとなること() {
    gameForm = new GameForm(1, null, null, null, platforms);
    validator.validate(gameForm, bindingResult);
    assertThat(bindingResult.getFieldErrors()).extracting(
      FieldError::getField,
      FieldError::getDefaultMessage
    ).containsExactlyInAnyOrder(
      tuple("name", "タイトルが未入力です。"),
      tuple("genre", "ジャンルが未入力です。"),
      tuple("price", "価格が未入力です。")
    );
  }

  @Test
  void platformが１つも選択されていない時_バリデーションエラーとなること() {
    String[] emptyPlatform = {};
    gameForm = new GameForm(1, "name", "genre", 100, emptyPlatform);
    validator.validate(gameForm, bindingResult);
    assertThat(bindingResult.getFieldErrors()).extracting(
      FieldError::getField,
      FieldError::getDefaultMessage
    ).containsExactlyInAnyOrder(
      tuple("platformId", "プラットフォームが未選択です。")
    );
  }

  @Test
  void nameとgenreに20文字以内の値を渡した時_バリデーションエラーとならないこと() {
    gameForm = new GameForm(1, StringUtils.repeat("n", 20), StringUtils.repeat("g", 20), 100, platforms);
    validator.validate(gameForm, bindingResult);
    assertNull(bindingResult.getFieldError());
  }

  @Test
  void nameとgenreに20文字を超える値を渡した時_バリデーションエラーとなること() {
    gameForm = new GameForm(1, StringUtils.repeat("a", 21), StringUtils.repeat("b", 21), 100, platforms);
    validator.validate(gameForm, bindingResult);
    assertThat(bindingResult.getFieldErrors()).extracting(
      FieldError::getField,
      FieldError::getDefaultMessage
    ).containsExactlyInAnyOrder(
      tuple("name", "タイトルは20文字以内で入力して下さい。"),
      tuple("genre", "ジャンルは20文字以内で入力して下さい。")
    );
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
    assertThat(bindingResult.getFieldErrors()).extracting(
      FieldError::getField,
      FieldError::getDefaultMessage
    ).containsExactlyInAnyOrder(
      tuple("price", "価格は0~100000の範囲で入力して下さい。")
    );
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
    assertThat(bindingResult.getFieldErrors()).extracting(
      FieldError::getField,
      FieldError::getDefaultMessage
    ).containsExactlyInAnyOrder(
      tuple("price", "価格は0~100000の範囲で入力して下さい。")
    );
  }
}
