package com.crud.controller;

import com.crud.entity.Platform;
import com.crud.form.GameForm;
import com.crud.form.GameSearchForm;
import com.crud.form.PlatformForm;
import com.crud.service.GameService;
import com.crud.service.PlatformService;
import com.crud.service.exception.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GameController {
  private final GameService gameService;
  private final PlatformService platformService;

  public GameController(GameService gameService, PlatformService platformService) {
    this.gameService = gameService;
    this.platformService = platformService;
  }

  @ModelAttribute
  GameForm setupForm() {
    return new GameForm();
  }

  @ModelAttribute
  PlatformForm setupFormPlatform() {
    return new PlatformForm();
  }

  @GetMapping("/search")
  public String getSearch() {
    return "search";
  }

  @GetMapping("/create")
  public String getCreate(Model model) {
    List<Platform> platformList = platformService.getPlatform();
    model.addAttribute("platformList", platformList);
    return "create";
  }

  @PostMapping("/search/db")
  public String search(@Validated GameSearchForm gameSearchForm, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("validationError", bindingResult.getFieldError().getDefaultMessage());
      return "search";
    } else {
      try {
        model.addAttribute("gameList", gameService.getGames(gameSearchForm.getId(), gameSearchForm.getOrder()));
      } catch (NotFoundException e) {
        model.addAttribute("notFound", "レコードは存在しませんでした。");
      }
    }
    return "search/db";
  }

  @PostMapping("/create")
  public String create(@Validated GameForm gameForm, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes) {

    List<Platform> platformList = platformService.getPlatform();
    if (bindingResult.hasErrors()) {
      model.addAttribute("createFailed", "登録に失敗しました。");
      model.addAttribute("platformList", platformList);
      return "create";
    } else {
      gameService.createGame(gameForm.getId(), gameForm.getName(), gameForm.getGenre(), gameForm.getPrice(),
          gameForm.getPlatformId());
      redirectAttributes.addFlashAttribute("createSuccess", "登録に成功しました。");
      redirectAttributes.addFlashAttribute("platformList", platformList);
    }
    return "redirect:/create";
  }

  @PostMapping(value = "/search/db", params = "delete")
  public String deleteGame(@RequestParam("delete") String strGameId, Model model) {
    Integer gameId = Integer.parseInt(strGameId);
    try {
      gameService.deleteGame(gameId);
      model.addAttribute("deleteSuccess", "削除に成功しました。");
    } catch (Exception e) {
      model.addAttribute("deleteFailed", "削除に失敗しました。");
    }
    try {
      model.addAttribute("gameList", gameService.getGames(null, "asc"));

    } catch (NotFoundException e) {
      model.addAttribute("notFound", "レコードは存在しませんでした。");
    }
    return "/search/db";
  }
}
