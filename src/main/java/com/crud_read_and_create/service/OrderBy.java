package com.crud_read_and_create.service;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

public enum OrderBy {
  ASC, DESC;

  public static OrderBy from(String input) {
    return Arrays.stream(values()).filter(v -> v.name().equals(StringUtils.upperCase(input)))
      .findFirst()
      .orElseThrow(RuntimeException::new);
  }
}
