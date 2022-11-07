package com.crud.controller;

import com.crud.entity.Platform;
import com.crud.form.PlatformForm;
import com.crud.service.PlatformService;
import com.crud.service.exception.DuplicateException;
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
public class PlatformController {
  private final PlatformService platformService;

  public PlatformController(PlatformService platformService) {
    this.platformService = platformService;
  }

  @ModelAttribute
  PlatformForm setupFormPlatform() {
    return new PlatformForm();
  }

  @GetMapping("/create-platform")
  public String getCreatePlatform(Model model) {
    List<Platform> platformList = platformService.getPlatform();
    model.addAttribute("platformList", platformList);
    return "createPlatform";
  }

  @PostMapping("/create-platform")
  public String createPlatform(@Validated PlatformForm platformForm, BindingResult bindingResult, Model model,
                               RedirectAttributes redirectAttributes) {
    List<Platform> platformList = platformService.getPlatform();
    if (bindingResult.hasErrors()) {
      model.addAttribute("createFailed", "登録に失敗しました。");
      model.addAttribute("platformList", platformList);
      return "createPlatform";
    } else {
      try {
        platformService.createPlatform(platformForm.getId(), platformForm.getPlatform());
        redirectAttributes.addFlashAttribute("createSuccess", "登録に成功しました。");
        redirectAttributes.addFlashAttribute("platformList", platformService.getPlatform());
      } catch (DuplicateException e) {
        model.addAttribute("createFailed", "登録に失敗しました。");
        model.addAttribute("duplicate", "プラットフォームが重複しています。");
        model.addAttribute("platformList", platformList);
        return "createPlatform";
      }
    }
    return "redirect:/create-platform";
  }

  @PostMapping(value = "/create-platform", params = "delete")
  public String deletePlatform(@RequestParam("delete") String strPlatformId, RedirectAttributes redirectAttributes) {
    Integer platformId = Integer.valueOf(strPlatformId);
    try {
      platformService.deletePlatform(platformId);
      redirectAttributes.addFlashAttribute("deleteSuccess", "削除に成功しました。");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("deleteFailed", "削除に失敗しました。");
    }
    redirectAttributes.addFlashAttribute("platformList", platformService.getPlatform());
    return "redirect:/create-platform";
  }
}
