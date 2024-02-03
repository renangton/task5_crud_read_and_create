package com.crud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void GETでsearchにアクセスする() throws Exception {
    mockMvc.perform(get("/search")).andExpect(status().isOk()).andExpect(view().name("search"));
  }

  @Test
  void GETでcreateにアクセスする() throws Exception {
    mockMvc.perform(get("/create")).andExpect(status().isOk()).andExpect(view().name("create"));
  }

  @Test
  void GETでcreate_platformにアクセスする() throws Exception {
    mockMvc.perform(get("/create-platform")).andExpect(status().isOk()).andExpect(view().name("createPlatform"));
  }
}
