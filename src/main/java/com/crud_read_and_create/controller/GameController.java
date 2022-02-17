package com.crud_read_and_create.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.form.GameForm;
import com.crud_read_and_create.service.GameService;

@Controller
public class GameController {

	@ModelAttribute
	GameForm setupForm() {
		return new GameForm();
	}

	@Autowired
	GameService gameService;

	@GetMapping("/search")
	public String getSearch() {

		return "search";
	}

	@GetMapping("/create")
	public String getCreate() {

		return "/create";
	}

	@PostMapping("/search/db")
	public String search(GameForm gameForm, Model model) {

		if (StringUtils.isEmpty(gameForm.getId())) {

			if (gameForm.getOrder().equals("asc")) {

				List<Game> gameListAsc = gameService.getGamesAsc();
				model.addAttribute("gameList", gameListAsc);

			} else if (gameForm.getOrder().equals("desc")) {

				List<Game> gameListDesc = gameService.getGamesDesc();
				model.addAttribute("gameList", gameListDesc);
			}

		} else {

			if (NumberUtils.isNumber(gameForm.getId())) {
				Game game = gameService.findById(gameForm.getId());
				model.addAttribute("game", game);

				if (game == null) {
					model.addAttribute("notFound", "レコードは存在しませんでした。");
				}

			} else {
				model.addAttribute("mojiretsu", "数字を入力して下さい。");
			}

		}

		return "search/db";
	}

	@PostMapping("/search")
<<<<<<< Updated upstream
	public String create(@ModelAttribute @Validated GameForm gameForm, BindingResult bindingResult, Model model) {
=======
	public String create(@Validated GameForm formValid, BindingResult bindingResult, @ModelAttribute GameForm gameForm,
			Model model) {
>>>>>>> Stashed changes

		if (bindingResult.hasErrors()) {
			model.addAttribute("createFailed", "登録に失敗しました。");
			return "/create";
		} else {
			gameService.create(gameForm);
			model.addAttribute("createSuccess", "登録に成功しました。");
		}

		return "/create";
	}
}