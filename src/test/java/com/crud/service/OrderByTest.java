package com.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.Test;

class OrderByTest {

  @Test
  void ascを渡した時_ASCが返されること() {
    assertThat(OrderBy.from("asc")).isEqualTo(OrderBy.ASC);
  }

  @Test
  void descを渡した時_DESCが返されること() {
    assertThat(OrderBy.from("desc")).isEqualTo(OrderBy.DESC);
  }

  @Test
  void ascとdesc以外の値を渡した時_RuntimeExceptionが発生すること() {
    assertThrows(RuntimeException.class, () -> OrderBy.from("exception"));
  }
}
