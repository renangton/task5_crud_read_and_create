package com.crud_read_and_create.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.form.GameForm;
import com.crud_read_and_create.form.GamePlatformForm;
import com.crud_read_and_create.form.PlatformForm;
import com.crud_read_and_create.service.GameService;
import com.crud_read_and_create.service.exception.NotFoundException;
import com.crud_read_and_create.service.exception.PatternIntException;

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
		try {
			model.addAttribute("gameList", gameService.getGames(gameForm));
		} catch (NotFoundException e) {
			model.addAttribute("notFound", "レコードは存在しませんでした。");
		} catch (PatternIntException e) {
			model.addAttribute("mojiretsu", "数字を入力して下さい。");
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
			redirectAttributes.addFlashAttribute("platformList", platformList);
		}
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
				redirectAttributes.addFlashAttribute("platformList", platformList);
			}
		}
		return "redirect:create-platform";
	}
}