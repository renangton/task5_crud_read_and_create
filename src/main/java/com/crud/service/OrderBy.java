package com.crud.service;

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
