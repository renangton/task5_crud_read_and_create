package com.crud_read_and_create.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderByTest {

    @Test
    void ascを渡した時ASCが返されること() {
        assertThat(OrderBy.from("asc")).isEqualTo(OrderBy.ASC);
    }

    @Test
    void descを渡した時DESCが返されること() {
        assertThat(OrderBy.from("desc")).isEqualTo(OrderBy.DESC);
    }
}