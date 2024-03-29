package com.crud.form;

import java.util.Objects;
import javax.validation.constraints.Pattern;

public class GameSearchForm {

  @Pattern(regexp = "|[1-9][0-9]{0,2}|1000", message = "IDは1～1000の範囲の整数で入力してください。")
  private String id;

  private String order;

  public GameSearchForm(String id, String order) {
    this.id = id;
    this.order = order;
  }

  public GameSearchForm() {
  }

  public Integer getId() {
    return Objects.nonNull(this.id) && !this.id.isEmpty() ? Integer.valueOf(this.id) : null;
  }

  public String getOrder() {
    return order;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setOrder(String order) {
    this.order = order;
  }
}
