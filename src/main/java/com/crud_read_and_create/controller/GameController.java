package com.crud_read_and_create.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud_read_and_create.controller.view.GameView;
import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.form.GameForm;
import com.crud_read_and_create.form.GamePlatformForm;
import com.crud_read_and_create.form.PlatformForm;
import com.crud_read_and_create.service.GameService;

@Controller
public class GameController {
	private final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
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
		List<Platform> platformList = gameService.getPlatform();
		model.addAttribute("platformList", platformList);
		return "create";
	}

	@GetMapping("/create-platform")
	public String getCreatePlatform(Model model) {
		List<Platform> platformList = gameService.getPlatform();
		model.addAttribute("platformList", platformList);
		return "createPlatform";
	}

	@PostMapping("/search/db")
	public String search(GameForm gameForm, Model model) {

		if (StringUtils.isEmpty(gameForm.getId())) {

			if (gameForm.getOrder().equals("asc")) {
				List<Game> gameListAsc = gameService.getGamesAsc();
				List<GameView> gameViewAsc = gameListAsc.stream().map(GameView::new).collect(Collectors.toList());
				model.addAttribute("gameList", gameViewAsc);

			} else if (gameForm.getOrder().equals("desc")) {
				List<Game> gameListDesc = gameService.getGamesDesc();
				List<GameView> gameViewDesc = gameListDesc.stream().map(GameView::new).collect(Collectors.toList());
				model.addAttribute("gameList", gameViewDesc);
			}

		} else {

			if (NumberUtils.isNumber(gameForm.getId())) {
				List<Game> gameId = gameService.getGamesId(gameForm.getId());
				if (gameId.size() == 0) {
					model.addAttribute("notFound", "レコードは存在しませんでした。");
				} else {
					List<GameView> gameViewId = gameId.stream().map(GameView::new).collect(Collectors.toList());
					model.addAttribute("gameList", gameViewId);
				}
			} else {
				model.addAttribute("mojiretsu", "数字を入力して下さい。");
			}
		}
		return "search/db";
	}

	@PostMapping("/create")
	public String create(@Validated GameForm gameForm, BindingResult bindingResult, GamePlatformForm gamePlatformForm,
			Model model, RedirectAttributes redirectAttributes) {

		List<Platform> platformList = gameService.getPlatform();
		if (bindingResult.hasErrors()) {
			model.addAttribute("createFailed", "登録に失敗しました。");
			model.addAttribute("platformList", platformList);
			return "create";
		} else {
			gameService.createGame(gameForm, gamePlatformForm);
			redirectAttributes.addFlashAttribute("createSuccess", "登録に成功しました。");
		}
		redirectAttributes.addFlashAttribute("platformList", platformList);
		return "redirect:create";
	}

	@PostMapping("/create-platform")
	public String createPlatform(@Validated PlatformForm platformForm, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {

		List<Platform> platformList = gameService.getPlatform();
		if (bindingResult.hasErrors()) {
			model.addAttribute("createFailed", "登録に失敗しました。");
			model.addAttribute("platformList", platformList);
			return "createPlatform";
		} else {
			boolean check = true;
			for (int i = 0; i < platformList.size(); i++) {
				if (platformForm.getPlatform().equals(platformList.get(i).getPlatform())) {
					check = false;
					model.addAttribute("createFailed", "登録に失敗しました。");
					model.addAttribute("duplicate", "プラットフォームが重複しています。");
					model.addAttribute("platformList", platformList);
					return "createPlatform";
				}
			}
			if (check) {
				gameService.createPlatform(platformForm);
				redirectAttributes.addFlashAttribute("createSuccess", "登録に成功しました。");
			}
		}
		redirectAttributes.addFlashAttribute("platformList", platformList);
		return "redirect:create-platform";
	}
}