package com.crud_read_and_create.controller;

import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.form.GameForm;
import com.crud_read_and_create.form.GameSearchForm;
import com.crud_read_and_create.form.PlatformForm;
import com.crud_read_and_create.service.GameService;
import com.crud_read_and_create.service.exception.DuplicateException;
import com.crud_read_and_create.service.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String search(@Validated GameSearchForm gameSearchForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("validationError", bindingResult.getFieldError().getDefaultMessage());
            return "search";
        } else {
            try {
                model.addAttribute("gameList", gameService.getGames(gameSearchForm.getId(), gameSearchForm.getOrder()));
            } catch (NotFoundException e) {
                model.addAttribute("notFound", "?????????????????????????????????????????????");
            }
        }
        return "search/db";
    }

    @PostMapping("/create")
    public String create(@Validated GameForm gameForm, BindingResult bindingResult, Model model,
                         RedirectAttributes redirectAttributes) {

        List<Platform> platformList = gameService.getPlatform();
        if (bindingResult.hasErrors()) {
            model.addAttribute("createFailed", "??????????????????????????????");
            model.addAttribute("platformList", platformList);
            return "create";
        } else {
            gameService.createGame(gameForm.getId(), gameForm.getName(), gameForm.getGenre(), gameForm.getPrice(),
                    gameForm.getPlatformId());
            redirectAttributes.addFlashAttribute("createSuccess", "??????????????????????????????");
            redirectAttributes.addFlashAttribute("platformList", platformList);
        }
        return "redirect:/create";
    }

    @PostMapping("/create-platform")
    public String createPlatform(@Validated PlatformForm platformForm, BindingResult bindingResult, Model model,
                                 RedirectAttributes redirectAttributes) {
        List<Platform> platformList = gameService.getPlatform();
        if (bindingResult.hasErrors()) {
            model.addAttribute("createFailed", "??????????????????????????????");
            model.addAttribute("platformList", platformList);
            return "createPlatform";
        } else {
            try {
                gameService.createPlatform(platformForm.getId(), platformForm.getPlatform());
                redirectAttributes.addFlashAttribute("createSuccess", "??????????????????????????????");
                redirectAttributes.addFlashAttribute("platformList", gameService.getPlatform());
            } catch (DuplicateException e) {
                model.addAttribute("createFailed", "??????????????????????????????");
                model.addAttribute("duplicate", "???????????????????????????????????????????????????");
                model.addAttribute("platformList", platformList);
                return "createPlatform";
            }
        }
        return "redirect:/create-platform";
    }

}
