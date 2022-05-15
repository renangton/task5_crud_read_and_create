package com.crud_read_and_create.form;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.Assert.assertNull;

@SpringBootTest(classes = {ValidationAutoConfiguration.class})
class PlatformFormTest {

    @Autowired
    Validator validator;

    private PlatformForm platformForm = new PlatformForm();
    private BindingResult bindingResult = new BindException(platformForm, "platformForm");

    @Test
    void 正しい値を渡した時_バリデーションエラーとならないこと() {
        platformForm = new PlatformForm(1, "platform");
        validator.validate(platformForm, bindingResult);
        assertNull(bindingResult.getFieldError());
    }

    @Test
    void 必須項目に空文字を渡した時_バリデーションエラーとなること() {
        platformForm = new PlatformForm(1, "");
        validator.validate(platformForm, bindingResult);
        assertThat(bindingResult.getFieldErrors()).extracting(
                FieldError::getField,
                FieldError::getDefaultMessage
        ).containsExactlyInAnyOrder(
                tuple("platform", "プラットフォームが未入力です。")
        );
    }

    @Test
    void 必須項目にnullを渡した時_バリデーションエラーとなること() {
        platformForm = new PlatformForm(1, null);
        validator.validate(platformForm, bindingResult);
        assertThat(bindingResult.getFieldErrors()).extracting(
                FieldError::getField,
                FieldError::getDefaultMessage
        ).containsExactlyInAnyOrder(
                tuple("platform", "プラットフォームが未入力です。")
        );
    }

    @Test
    void platformに20文字以内の値を渡した時_バリデーションエラーとならないこと() {
        platformForm = new PlatformForm(1, "abcdefghijklmnopqrst");
        validator.validate(platformForm, bindingResult);
        assertNull(bindingResult.getFieldError());
    }

    @Test
    void platformに20文字を超える値を渡した時_バリデーションエラーとなること() {
        platformForm = new PlatformForm(1, "abcdefghijklmnopqrstu");
        validator.validate(platformForm, bindingResult);
        assertThat(bindingResult.getFieldErrors()).extracting(
                FieldError::getField,
                FieldError::getDefaultMessage
        ).containsExactlyInAnyOrder(
                tuple("platform", "プラットフォームは20文字以内で入力して下さい。")
        );
    }
}
