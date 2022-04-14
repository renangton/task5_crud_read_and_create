package com.crud_read_and_create.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameServiceTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void idがnullで昇順が選択されている時_昇順でゲームを全件取得できること() {

	}

	@Test
	void idがnullで降順が選択されている時_降順でゲームを全件取得できること() {

	}

	@Test
	void idに数字が入力されていてレコードが存在しない時_NotFoundExceptionが発生すること() {

	}

	@Test
	void idに数字が入力されていてがレコードが存在する時_対象のゲームを取得できること() {

	}

	@Test
	void idに数字以外が入力されている時_PatternIntExceptionが発生すること() {

	}

	@Test
	void プラットフォームを全件取得できること() {

	}

	@Test
	void プラットフォームが１件のみ選択されている時_各引数が設定されたgameとgameplatformList１件が返却されること() {

	}

	@Test
	void プラットフォームが複数件選択されている時_各引数が設定されたgameとgameplatformList複数件が返却されること() {

	}

	@Test
	void 入力したプラットフォームがすでに登録されている時_DuplicateExceptionが発生すること() {

	}

	@Test
	void 入力したプラットフォームが未登録の時_platformが設定されたplatformListが返却されること() {

	}
}
