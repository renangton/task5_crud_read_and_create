package com.crud_read_and_create.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String create() {

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
	public String create(GameForm gameForm, @ModelAttribute Game game) {

		gameService.create(game);

		return "redirect:/create";
	}
}